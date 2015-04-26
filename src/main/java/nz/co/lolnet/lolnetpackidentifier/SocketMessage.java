package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class SocketMessage {
    
    public boolean messageSent = false;
    public boolean replyReceived = false;
    public long startTime;

	public static void sendMessage(String ip, int port,String playerName,UUID playerUUID,String modPackName,List<String> modlist) {
        startTime = System.currentTimeMillis();
		Socket clientSocket = null;
		PrintWriter out = null;
        BufferedReader in;
		try {
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(playerUUID + "~~~" + playerName + "~~~" + modPackName+"~~~" + modlist.toString());
            messageSent = true;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String fromServer = in.readLine(); //This is on the same thread and will remain until a reply is received.
            replyReceived = true;
		} catch (Exception ex) {
			Logger.getLogger(SocketMessage.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (out != null) out.close();
				if (clientSocket != null) clientSocket.close();
			} catch (IOException ex) {
				Logger.getLogger(SocketMessage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private static SocketThread thread = null;

	public static void sendMessage()
	{
		String name =  Minecraft.getMinecraft().getSession().func_148256_e().getName();
		UUID id = Minecraft.getMinecraft().getSession().func_148256_e().getId();
		List<String> mods = new ArrayList<String>();
		for (ModContainer c : Loader.instance().getActiveModList()) mods.add(c.getModId());

		if (thread == null || thread.isFinished())
		{
			thread = new SocketThread(LolnetPackIdentifier.LOLNET_IP, ConfigHandler.port, name, id, ConfigHandler.packName, mods);
			thread.start();
		}
	}

	private static class SocketThread extends Thread
	{
		private String ip;
		private int port;
		private String playername;
		private UUID playerUUID;
		private String modpackName;
		private List<String> modlist;
		public boolean finished = false;

		public SocketThread(String ip, int port, String playername, UUID playerUUID, String modpackName, List<String> modlist)
		{
			this.ip = ip;
			this.port = port;
			this.playername = playername;
			this.playerUUID = playerUUID;
			this.modpackName = modpackName;
			this.modlist = modlist;
		}

		@Override
		public void run() {
			SocketMessage.sendMessage(ip, port, playername, playerUUID, modpackName, modlist);
			finished = true;
		}

		public boolean isFinished() {
			return finished;
		}
	}
}

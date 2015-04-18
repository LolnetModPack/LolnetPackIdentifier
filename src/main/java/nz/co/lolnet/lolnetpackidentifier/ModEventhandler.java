package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Brandon on 4/18/2015.
 */
public class ModEventhandler {

	@SubscribeEvent
	public void guiEvent(GuiScreenEvent.InitGuiEvent event)
	{

		if (event.gui instanceof GuiMultiplayer)
		{
			String name =  Minecraft.getMinecraft().getSession().func_148256_e().getName();
			UUID id = Minecraft.getMinecraft().getSession().func_148256_e().getId();
			List<String> mods = new ArrayList<String>();
			for (ModContainer c : Loader.instance().getActiveModList()) mods.add(c.getModId());

			SocketThread thread = new SocketThread("lolnet.co.nz", ConfigHandler.port, name, id, ConfigHandler.packName, mods);
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
		}
	}


}

package nz.co.lolnet.lolnetpackidentifier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class SocketMessage {

	public static void sendMessage(String ip, int port,String playerName,UUID playerUUID,String modPackName,List<String> modlist) {
		System.out.println("Send Message");
		Socket clientSocket = null;
		PrintWriter out = null;
		try {
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(playerUUID + "~~~" + playerName + "~~~" + modPackName+"~~~" + modlist.toString());
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
		System.out.println("Message Sent");
	}
}

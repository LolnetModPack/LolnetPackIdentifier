package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;

/**
 * Created by Brandon on 4/19/2015.
 */
public class GuiMPCustom extends GuiMultiplayer {

	public GuiMPCustom(GuiScreen guiScreen) {
		super(guiScreen);
	}

	@Override
	public void func_146791_a(ServerData serverData)
	{
		try
		{
			if (serverData.serverIP.toLowerCase().contains(LolnetPackIdentifier.LOLNET_IP))
			{
				SocketMessage.sendMessage();
				Thread.sleep(1000L);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		FMLClientHandler.instance().connectToServer(this, serverData);
	}
}

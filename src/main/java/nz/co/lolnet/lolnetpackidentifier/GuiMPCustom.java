package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerList;

import java.lang.reflect.Field;

/**
 * Created by Brandon on 4/19/2015.
 */
public class GuiMPCustom extends GuiMultiplayer {

	public GuiMPCustom(GuiScreen guiScreen) {
		super(guiScreen);
	}

	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);

		try
		{
			Field selectionList = ReflectionHelper.findField(GuiMultiplayer.class, "field_146803_h");
			selectionList.setAccessible(true);

			ServerSelectionList selection = (ServerSelectionList)selectionList.get(this);

			Field serverList = ReflectionHelper.findField(GuiMultiplayer.class, "field_146804_i");
			serverList.setAccessible(true);

			ServerList list = (ServerList)serverList.get(this);

			if (selection.func_148193_k() < 0) return;

			String ip = list.getServerData(selection.func_148193_k()).serverIP;

			if (ip.equals(LolnetPackIdentifier.LOLNET_IP)) SocketMessage.sendMessage();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}

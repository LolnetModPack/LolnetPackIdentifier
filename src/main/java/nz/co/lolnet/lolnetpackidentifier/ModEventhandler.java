package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.lang.reflect.Field;

/**
 * Created by Brandon on 4/18/2015.
 */
public class ModEventhandler {

	@SubscribeEvent
	public void guiOpen(GuiOpenEvent event)
	{
		if (event.gui instanceof GuiMultiplayer)
		{
			GuiMultiplayer gui = (GuiMultiplayer) event.gui;

			try
			{
				Field parentGui = ReflectionHelper.findField(GuiMultiplayer.class, "field_146798_g");
				parentGui.setAccessible(true);

				Object parent = parentGui.get(gui);
				event.gui = new GuiMPCustom((GuiScreen)parent);
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public void guiEvent2(GuiScreenEvent.ActionPerformedEvent event)
	{
		if (event.gui instanceof GuiScreenServerList && event.button.id == 0)
		{
			try
			{
				Field selectionList = ReflectionHelper.findField(GuiScreenServerList.class, "field_146302_g");
				selectionList.setAccessible(true);

				GuiTextField selection = (GuiTextField)selectionList.get(event.gui);

				if (selection.getText().equals(LolnetPackIdentifier.LOLNET_IP)) SocketMessage.sendMessage();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}

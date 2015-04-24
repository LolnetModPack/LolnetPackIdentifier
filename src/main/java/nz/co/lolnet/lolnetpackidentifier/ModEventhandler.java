package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;

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
}

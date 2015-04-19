package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = LolnetPackIdentifier.MODID, version = LolnetPackIdentifier.VERSION, name = "Lolnet Pack Identifier")
public class LolnetPackIdentifier
{
    public static final String MODID = "LolnetPackIdentifier";
    public static final String VERSION = "1.0-build_3";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.init(event);
		MinecraftForge.EVENT_BUS.register(new ModEventhandler());
	}
}

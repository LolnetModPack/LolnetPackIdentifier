package nz.co.lolnet.lolnetpackidentifier;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Brandon on 7/04/2015.
 */
public class ConfigHandler
{
	public static Configuration config;

	public static int port;
	public static String packName;

	public static void init(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
			port = config.get(Configuration.CATEGORY_GENERAL, "Port", 10009, "", 0, Integer.MAX_VALUE).getInt(10009);
			packName = config.get(Configuration.CATEGORY_GENERAL, "Pack Name", "ArnsForgotSomethingHere", "").getString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (config.hasChanged()) config.save();
		}
	}
}

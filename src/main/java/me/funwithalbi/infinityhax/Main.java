package me.funwithalbi.infinityhax;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import me.funwithalbi.infinityhax.util.Settings;
import org.apache.logging.log4j.Logger;

@Mod(modid = Settings.MODID, name = Settings.NAME, version = Settings.VERSION)
public class Main
{
    private static Logger logger;
	
	@Instance
	public Main instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Loading preInit for mod Infinity-Hax");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// verify loading
        logger.info("Loaded Infinity Hax Infinity incursion on top!");
		
    }
	
	@EventHandler
	public void PostInit (FMLInitializationEvent event) {
	
	}
}

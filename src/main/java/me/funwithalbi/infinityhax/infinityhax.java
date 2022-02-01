package me.funwithalbi.infinityhax;

import me.funwithalbi.infinityhax.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = INFINITYHAX.MODID, name = INFINITYHAX.NAME, version = INFINITYHAX.VERSION)
public class Main {
    public static final String MODID = "infhx";
    public static final String NAME = "Infinity-Hax";
    public static final String VERSION = "0.12-2"
    private static Logger logger;
    public static ModuleManager moduleManager;
	
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

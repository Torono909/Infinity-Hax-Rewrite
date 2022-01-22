package me.funwithalbi.infinity-hax;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "infhx";
    public static final String NAME = "Infinity-Hax";
    public static final String VERSION = "0.12-2";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
	  // credits: funwithalbi, google_was_my_idea
         // verify loading
        logger.info("Loaded Infinity Hax. Infinity incursion remastered on top!")
		
        // some obsidian logger test code
        logger.info("OBBY >> {}", Blocks.OBSIDIAN.getRegistryName());
    }
}

package com.clantags;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = com.clantags.ClanTags.MODID, name = com.clantags.ClanTags.NAME, version = com.clantags.ClanTags.VERSION)
public class ClanTags
{
    public static final String MODID = "clantags";
    public static final String NAME = "2Clan2Tags";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Initialising 2clan2tags...");
    }
}

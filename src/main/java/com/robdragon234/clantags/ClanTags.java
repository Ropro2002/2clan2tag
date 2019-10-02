package com.robdragon234.clantags;

import com.robdragon234.clantags.impl.ClanTagsImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = ClanTags.MOD_ID,
        name = ClanTags.MOD_NAME,
        version = ClanTags.VERSION
)
public class ClanTags
{
    public static final String MOD_ID = "clantags";
    public static final String MOD_NAME = "Clantags";
    public static final String VERSION = "2019.2-1.3.1";

    public static ClanTagsImpl clanTagsImpl;
    public static Logger logger;

    @Mod.Instance(MOD_ID)
    public static ClanTags INSTANCE;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Initialising 2clan2tags...");
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        clanTagsImpl = new ClanTagsImpl();
        clanTagsImpl.fetchUsers();
    }
}

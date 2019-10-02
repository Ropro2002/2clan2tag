package com.robdragon234.clantags.impl.config;

import com.robdragon234.clantags.ClanTags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@Config(modid = ClanTags.MOD_ID)
@Config.LangKey("clantags.config.title")
public class Configuration {

    @Config.Comment({"Mute users with no group", "This will not show chat messages of anyone that is not registered in any of your loaded databases"})
    public static boolean  muteNoRank = false;

    @Config.Comment({"Hide users with no group", "This will not show the username of anyone that is not registered in any of your loaded databases on the tablist"})
    public static boolean  hideNoRank = false;

    @Mod.EventBusSubscriber(modid = ClanTags.MOD_ID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ClanTags.MOD_ID)) {
                ConfigManager.sync(ClanTags.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}

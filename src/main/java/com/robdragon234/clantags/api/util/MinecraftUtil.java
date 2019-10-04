package com.robdragon234.clantags.api.util;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.factions.Faction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class MinecraftUtil {

    public static void setNameTag(EntityPlayer player) {
        Faction faction = ClanTags.INSTANCE.getDatabaseManager().getFactionOfPlayer(player);
        if (faction != null) {
            player.addPrefix(new TextComponentString("[" + faction.tag + "]"));
        }
    }

}

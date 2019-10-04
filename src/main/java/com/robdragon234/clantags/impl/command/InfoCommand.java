package com.robdragon234.clantags.impl.command;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.api.command.Command;
import com.robdragon234.clantags.api.util.ChatUtil;
import com.robdragon234.clantags.impl.factions.Faction;

public class InfoCommand extends Command {

    public InfoCommand() { super("Info", "Display the info of a group", "[group]");}

    @Override
    public void call(String[] args) {
        if(args.length == 0) {
            ChatUtil.error("No arguments found supposed to have 1");
            return;
        }
        Faction faction = ClanTags.INSTANCE.getDatabaseManager().getFactionById(args[0]);
        if(faction != null) ChatUtil.message(faction.name + "\n" + faction.desc + "\n" + "Discord: https://discord.gg/" + faction.discord);

    }
}

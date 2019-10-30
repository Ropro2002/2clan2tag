package com.robdragon234.clantags.impl.command;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.api.command.Command;
import com.robdragon234.clantags.api.util.ChatUtil;
import com.robdragon234.clantags.impl.factions.Faction;

public class InfoCommand extends Command {
	
	public InfoCommand() {
		super("Info", "Display the info of a group", "[group]");
	}
	
	@Override
	public void call(String[] args) {
		if (args.length < 1) {
			ChatUtil.error("Invalid usage. Expected " + ClanTags.INSTANCE.getCommandManager().getPrefix() + "info [faction id]");
			return;
		}
		
		StringBuilder factionName = new StringBuilder();
		
		for(String arg : args){
			factionName.append(arg).append(" ");
		}
		
		Faction faction = ClanTags.INSTANCE.getDatabaseManager().getFactionById(factionName.toString().trim());
		if (faction != null)
			ChatUtil.message(faction.getName() + "\n" + faction.getDesc() + "\n" + faction.getDiscord());
		
	}
}

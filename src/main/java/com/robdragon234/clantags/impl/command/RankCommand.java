package com.robdragon234.clantags.impl.command;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.api.command.Command;
import com.robdragon234.clantags.api.util.ChatUtil;
import com.robdragon234.clantags.impl.managers.CommandManager;
import com.robdragon234.clantags.impl.members.AdvancedMember;
import com.robdragon234.clantags.impl.members.Member;

public class RankCommand extends Command {
	
	public RankCommand() {
		super("rank", "Get the rank of a person", "[username]");
	}
	
	@Override
	public void call(String[] args) {
		if (args.length < 1) {
			ChatUtil.error("Invalid usage. Expected " + ClanTags.INSTANCE.getCommandManager().getPrefix() + "rank [name]");
			return;
		}
		Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(args[0]);
		if (member instanceof AdvancedMember) {
			ChatUtil.message(member.name + ": " + ((AdvancedMember) member).rank);
		} else {
			ChatUtil.message("That faction does not have ranks registered!");
		}
		
	}
}

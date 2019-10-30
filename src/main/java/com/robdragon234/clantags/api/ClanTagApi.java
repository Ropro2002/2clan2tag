package com.robdragon234.clantags.api;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.Member;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

public class ClanTagApi {
	
	public static ITextComponent getPlayerNameTag(EntityPlayer player){
		Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(player.getName());
		
		if(member != null){
			return player.getDisplayName().appendText("[" + member.faction.getTag() + "]");
		}
		return player.getDisplayName();
	}
}

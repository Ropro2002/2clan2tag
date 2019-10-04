package com.robdragon234.clantags.impl.members;

import com.robdragon234.clantags.impl.factions.Faction;

import java.util.List;

public class AdvancedMember extends Member
{
	public String rank;
	
	public AdvancedMember(String name, String rank, List<String> aliases, Faction faction) {
		super(name, faction, aliases);
		this.rank = rank;
	}
}

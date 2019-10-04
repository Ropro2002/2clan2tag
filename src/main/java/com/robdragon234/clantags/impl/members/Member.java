package com.robdragon234.clantags.impl.members;

import com.robdragon234.clantags.impl.factions.Faction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member
{
	public String name;
	public Faction faction;
	public List<String> aliases;

	public Member(String username, Faction faction)
	{
		this.name = username;
		this.faction = faction;
		this.aliases = new ArrayList<>();
	}

	public Member(String username, Faction faction, List<String> aliases)
	{
		this.name = username;
		this.faction = faction;
		this.aliases = aliases;
	}
}

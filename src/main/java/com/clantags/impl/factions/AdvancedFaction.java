package com.clantags.impl.factions;

import com.clantags.impl.members.Member;

import java.util.List;

public class AdvancedFaction extends Faction
{
	public AdvancedFaction(
		String id,
		String name,
		String tag,
		String desc,
		String discord,
		String wiki,
		List<Member> members
	)
	{
		super(id, name, tag, desc, discord, wiki, members);
	}
}

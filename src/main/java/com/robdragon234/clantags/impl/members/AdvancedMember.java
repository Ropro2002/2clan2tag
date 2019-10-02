package com.robdragon234.clantags.impl.members;

import java.util.List;

public class AdvancedMember extends Member
{
	public String rank;
	
	public AdvancedMember(
		String name,
		String rank,
		List<String> aliases
	)
	{
		this.name = name;
		this.rank = rank;
		this.aliases = aliases;
	}
}

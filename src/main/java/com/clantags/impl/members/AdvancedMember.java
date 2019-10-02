package com.clantags.impl.members;

public class AdvancedMember extends Member
{
	public String rank;
	
	public AdvancedMember(
		String name,
		String rank,
		String[] aliases
	)
	{
		this.name = name;
		this.rank = rank;
		this.aliases = aliases;
	}
}

package com.clantags.impl.members;

public class Member
{
	public String[] aliases;
	
	public Member(String username)
	{
		this.aliases = new String[]{username};
	}
}

package com.robdragon234.clantags.impl.members;

public class Member
{
	public String name;
	public String[] aliases;
	
	// Default constructor only available to derived classes
	Member(){}
	
	public Member(String username)
	{
		this.name = username;
		this.aliases = new String[]{username};
	}
}

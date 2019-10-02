package com.robdragon234.clantags.impl.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member
{
	public String name;
	public List<String> aliases;
	
	// Default constructor only available to derived classes
	Member(){}
	
	public Member(String username)
	{
		this.name = username;
		this.aliases = Collections.singletonList(username); // <-- IMMUTABLE LIST! Will throw exception if added to
	}
}

package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonObject;
import com.robdragon234.clantags.impl.database.Database;

public class SimpleDatabaseParser extends DatabaseParser
{
	SimpleDatabaseParser(JsonObject jsonObject)
	{
		super(jsonObject);
	}
	
	@Override
	public Database parse()
	{
		return null;
	}
}

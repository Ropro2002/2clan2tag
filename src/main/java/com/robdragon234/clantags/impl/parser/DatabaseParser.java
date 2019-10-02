package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.robdragon234.clantags.ClanTags;

public abstract class DatabaseParser
{
	public static DatabaseParser parse(String rawData) throws JsonSyntaxException, InvalidDatabaseException
	{
		JsonObject jsonObject = new JsonParser().parse(rawData).getAsJsonObject();
		
		String dbFormat = jsonObject.get("dbFormat").getAsString();
		
		if(dbFormat.equals("s1"))
		{
			return new SimpleDatabaseParser();
		}
		else if(dbFormat.equals("a1"))
		{
			return new AdvancedDatabaseParser();
		}
		
		throw new InvalidDatabaseException();
	}
}

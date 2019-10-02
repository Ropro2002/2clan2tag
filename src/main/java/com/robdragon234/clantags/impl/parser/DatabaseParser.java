package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public abstract class DatabaseParser
{
	private JsonObject jsonObject;
	
	DatabaseParser(JsonObject jsonObject)
	{
		this.jsonObject = jsonObject;
	}
	
	public static final DatabaseParser parse(String rawData) throws JsonSyntaxException, InvalidDatabaseException
	{
		JsonObject jsonObject = new JsonParser().parse(rawData).getAsJsonObject();
		
		String dbFormat = jsonObject.get("dbFormat").getAsString();
		
		if(dbFormat.equals("s1"))
		{
			return new SimpleDatabaseParser(jsonObject);
		}
		else if(dbFormat.equals("a1"))
		{
			return new AdvancedDatabaseParser(jsonObject);
		}
		
		throw new InvalidDatabaseException();
	}
}

package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;

import java.util.ArrayList;
import java.util.List;

public class AdvancedDatabaseParser extends DatabaseParser
{
	AdvancedDatabaseParser(JsonObject jsonObject)
	{
		super(jsonObject);
	}
	
	@Override
	public Database parse()
	{
		String name = getAsString(jsonObject, "name");
		String dbFormat = getAsString(jsonObject, "dbFormat");
		String author = getAsString(jsonObject, "author");
		long lastUpdated = getAsLong(jsonObject, "lastUpdated");
		
		List<Faction> factions = new ArrayList<>();
		
		JsonArray factionsJson = jsonObject.get("factions").getAsJsonArray();
		
		for(JsonElement factionElement : factionsJson)
		{
			JsonObject factionObj = factionElement.getAsJsonObject();
			
			String id = getAsString(factionObj, "id");
			String factionName = getAsString(factionObj, "name");
			String tag = getAsString(factionObj, "tag");
			String description = getAsString(factionObj, "description");
		}
	}
}

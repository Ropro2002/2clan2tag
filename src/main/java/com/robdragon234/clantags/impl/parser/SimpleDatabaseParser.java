package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

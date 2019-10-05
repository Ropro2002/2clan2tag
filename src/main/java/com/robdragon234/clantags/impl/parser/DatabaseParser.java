package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.throwables.InvalidDatabaseException;

import java.util.Objects;

public abstract class DatabaseParser {
	protected JsonObject jsonObject;
	
	DatabaseParser(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public static final DatabaseParser getParser(String rawData) throws JsonSyntaxException, InvalidDatabaseException {
		JsonObject jsonObject = new JsonParser().parse(rawData).getAsJsonObject();
		
		String dbFormat;
		try {
			dbFormat = Objects.requireNonNull(jsonObject.get("dbFormat").getAsString());
		} catch (Exception e) {
			throw new InvalidDatabaseException("Error while retrieving database format", e);
		}
		
		if (dbFormat.equals("s1")) {
			return new SimpleDatabaseParser(jsonObject);
		} else if (dbFormat.equals("a1")) {
			return new AdvancedDatabaseParser(jsonObject);
		}
		
		throw new InvalidDatabaseException("Database format '" + dbFormat + "' not recognised");
	}
	
	protected String getAsString(JsonObject obj, String key) {
		try {
			return Objects.requireNonNull(obj.get(key)).getAsString();
		} catch (Exception e) {
			ClanTags.logger.error("Error while getting key '" + key + "' as string");
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected Long getAsLong(JsonObject obj, String key) {
		try {
			return Objects.requireNonNull(obj.get(key)).getAsLong();
		} catch (Exception e) {
			ClanTags.logger.error("Error while getting key '" + key + "' as long");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public abstract Database parse();
}

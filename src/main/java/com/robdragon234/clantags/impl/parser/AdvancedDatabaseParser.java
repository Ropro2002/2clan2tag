package com.robdragon234.clantags.impl.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.AdvancedMember;
import com.robdragon234.clantags.impl.members.Member;

import java.util.ArrayList;
import java.util.List;

public class AdvancedDatabaseParser extends DatabaseParser {
	AdvancedDatabaseParser(JsonObject jsonObject) {
		super(jsonObject);
	}
	
	@Override
	public Database parse() {
		String name = getAsString(jsonObject, "name");
		
		ClanTags.logger.info("Parsing database '" + name + "'");
		
		String dbFormat = getAsString(jsonObject, "dbFormat");
		String author = getAsString(jsonObject, "author");
		long lastUpdated = getAsLong(jsonObject, "lastUpdated");
		
		List<Faction> factions = new ArrayList<>();
		
		JsonArray factionsJson = jsonObject.get("factions").getAsJsonArray();
		
		for (JsonElement factionElement : factionsJson) {
			JsonObject factionObj = factionElement.getAsJsonObject();
			
			String id = getAsString(factionObj, "id");
			String factionName = getAsString(factionObj, "name");
			String tag = getAsString(factionObj, "tag");
			String description = getAsString(factionObj, "description");
			String discord = getAsString(factionObj, "discord");
			String wiki = getAsString(factionObj, "wiki");
			
			Faction faction = new Faction(id, factionName, tag, description, discord, wiki);
			List<Member> members = new ArrayList<>();
			
			JsonArray membersJson = factionObj.get("members").getAsJsonArray();
			
			for (JsonElement memberElement : membersJson) {
				JsonObject memberObj = memberElement.getAsJsonObject();
				
				String memberName = getAsString(memberObj, "name");
				String rank = getAsString(memberObj, "rank");
				
				List<String> aliases = new ArrayList<>();
				
				JsonArray aliasesJson = memberObj.get("aliases").getAsJsonArray();
				
				for (JsonElement aliasElement : aliasesJson) {
					aliases.add(aliasElement.getAsString());
				}
				
				faction.addMember(new AdvancedMember(memberName, rank, aliases, faction));
			}
			
			factions.add(faction);
		}
		
		return new Database(name, dbFormat, author, lastUpdated, factions);
	}
}

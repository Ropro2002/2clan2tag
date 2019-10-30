package com.robdragon234.clantags.impl.managers;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.Member;
import com.robdragon234.clantags.impl.parser.DatabaseParser;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DatabaseManager {
	
	private List<Database> databases = new ArrayList<>();
	private HashMap<String, Member> memberCache = new HashMap<>();
	
	public DatabaseManager(List<URL> dbLocs) throws IOException {
		fetchDatabases(dbLocs);
	}
	
	public void fetchDatabases(List<URL> dbLocs) throws IOException {
		for (URL dbLoc : dbLocs) {
			ClanTags.logger.info("Fetching users from database '" + dbLoc + "'");
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							dbLoc.openStream()
					)
			);
			
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			databases.add(DatabaseParser.getParser(sb.toString()).parse());
			
			in.close();
		}
		
		debugDatabases();
	}
	
	/**
	 * Returns the {@link Member} that an {@link String} player belongs to
	 *
	 * @param name of the player
	 * @return the faction the player belongs to
	 */
	@Nullable
	public Member getMember(String name) {
		// Try retrieving from cache first
		try {
			return Objects.requireNonNull(memberCache.get(name));
		} catch (Exception ignored) {
		}
		for (Database database : databases) {
			for (Faction faction : database.factions) {
				for (Member member : faction.getMembers()) {
					if (member.name.equalsIgnoreCase(name)) return member;
					for (String alias : member.aliases) {
						if (alias.equalsIgnoreCase(name)) return member;
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Returns the {@link Faction} that an {@link String} belongs to
	 *
	 * @param name of the faction
	 * @return the faction the name belongs to
	 */
	@Nullable
	public Faction getFactionById(String name) {
		for (Database database : databases) {
			for (Faction faction : database.factions) {
				if (faction.getId().equalsIgnoreCase(name)) return faction;
			}
		}
		return null;
	}
	
	private void debugDatabases() {
		for (Database database : databases) {
			System.out.println("---------------");
			System.out.println(database.dbName);
			System.out.println(database.dbFormat);
			for (Faction faction : database.factions) {
				System.out.println(faction.getId());
				System.out.println(faction.getName());
				System.out.println(faction.getDesc());
				System.out.println(faction.getDiscord());
				System.out.println(faction.getTag());
				System.out.println(faction.getWiki());
				for (Member member : faction.getMembers()) {
					System.out.println(member.name);
					System.out.println(member.aliases);
				}
			}
		}
	}
}

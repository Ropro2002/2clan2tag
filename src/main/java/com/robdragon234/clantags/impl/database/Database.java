package com.robdragon234.clantags.impl.database;

import com.robdragon234.clantags.impl.factions.Faction;

import java.util.HashMap;
import java.util.List;

public class Database {
	public String dbName;
	public String dbFormat;
	public String author;
	public Long lastUpdated;
	public List<Faction> factions;
	
	public Database(
			String dbName,
			String dbFormat,
			List<Faction> factions
	) {
		this(
				dbName,
				dbFormat,
				null,
				null,
				factions
		);
	}
	
	public Database(
			String dbName,
			String dbFormat,
			String author,
			Long lastUpdated,
			List<Faction> factions
	) {
		this.dbName = dbName;
		this.dbFormat = dbFormat;
		this.author = author;
		this.lastUpdated = lastUpdated;
		this.factions = factions;
		
		this.playerCache = new HashMap<>();
	}
	
	private HashMap<String, Faction> playerCache;
}

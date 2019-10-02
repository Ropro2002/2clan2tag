package com.robdragon234.clantags.impl.database;

import com.clantags.impl.factions.Faction;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.List;

public class AdvancedDatabase extends Database
{
	public String author;
	public long lastUpdated;
	
	public AdvancedDatabase(
		String dbName,
		String dbFormat,
		String author,
		long lastUpdated,
		List<Faction> factions
	)
	{
		this.dbName = dbName;
		this.dbFormat = dbFormat;
		this.author = author;
		this.lastUpdated = lastUpdated;
		this.factions = factions;
		
		this.playerCache = new HashMap<>();
	}
	
	@Override
	public Faction getPlayerClan(EntityPlayer player)
	{
		return null;
	}
}

package com.robdragon234.clantags.impl.database;

import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.Member;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Database
{
	public String dbName;
	public String dbFormat;
	public String author;
	public Long lastUpdated;
	public List<Faction> factions;
	
	public Database(
		String dbName,
		String dbFormat,
		List<Faction> factions
	)
	{
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
	)
	{
		this.dbName = dbName;
		this.dbFormat = dbFormat;
		this.author = author;
		this.lastUpdated = lastUpdated;
		this.factions = factions;
		
		this.playerCache = new HashMap<>();
	}
	
	private HashMap<String, Faction> playerCache;
	
	public Faction getPlayerClan(EntityPlayer player)
	{
		// If this player is in the cache then return that
		try
		{
			return Objects.requireNonNull(playerCache.get(player.getName()));
		} catch(Exception ignored){}
		
		for(Faction faction : factions)
		{
			for(Member member : faction.members)
			{
				for(String alias : member.aliases)
				{
					if(alias.equalsIgnoreCase(player.getName()))
					{
						// The player is a member of this clan
						
						// Add them to the cache
						playerCache.put(alias, faction);
						
						return faction;
					}
				}
			}
		}
		
		// No faction was found
		return null;
	}
}

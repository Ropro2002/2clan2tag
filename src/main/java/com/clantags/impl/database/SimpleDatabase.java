package com.clantags.impl.database;

import com.clantags.impl.factions.Faction;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SimpleDatabase extends Database
{
	public SimpleDatabase(String dbName, String dbFormat, List<Faction> factions)
	{
		this.dbName = dbName;
		this.dbFormat = dbFormat;
		this.factions = factions;
		
		this.playerCache = new HashMap<>();
	}
	
	@Override
	public Faction getPlayerClan(EntityPlayer player)
	{
		// If this player is in the cache then return that
		try
		{
			return Objects.requireNonNull(playerCache.get(player.getName()));
		} catch(Exception ignored){}
		
		for(Faction faction : factions)
		{
			for(String member : faction.members)
			{
				if(member.equalsIgnoreCase(player.getName()))
				{
					// The player is a member of this clan
					
					// Add them to the cache
					playerCache.put(member, faction);
					
					return faction;
				}
			}
		}
		
		// No faction was found
		return null;
	}
}

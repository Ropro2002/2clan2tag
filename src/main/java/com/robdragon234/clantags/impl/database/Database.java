package com.robdragon234.clantags.impl.database;

import com.robdragon234.clantags.impl.factions.Faction;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.List;

public abstract class Database
{
	public String dbName;
	public String dbFormat;
	public List<Faction> factions;
	
	protected HashMap<String, Faction> playerCache;
	
	public abstract Faction getPlayerClan(EntityPlayer player);
}

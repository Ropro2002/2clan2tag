package com.clantags.impl.database;

import com.clantags.impl.Clan;
import net.minecraft.entity.player.EntityPlayer;

public abstract class Database
{
	public String dbName;
	public String dbFormat;
	
	public abstract Clan getPlayerClan(EntityPlayer player);
}

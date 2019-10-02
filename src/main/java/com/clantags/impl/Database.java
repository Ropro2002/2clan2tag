package com.clantags.impl;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Database
{
	public String dbName;
	public String dbV;
	
	public Database()
	{
	}
	
	public abstract Clan getPlayerClan(EntityPlayer player);
}

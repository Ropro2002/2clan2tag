package com.clantags.impl.database;

import com.clantags.impl.Clan;
import net.minecraft.entity.player.EntityPlayer;

public class SimpleDatabase extends Database
{
	public SimpleDatabase(String dbName, String dbFormat)
	{
	
	}
	
	@Override
	public Clan getPlayerClan(EntityPlayer player)
	{
		return null;
	}
}

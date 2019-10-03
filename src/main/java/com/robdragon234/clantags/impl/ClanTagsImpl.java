package com.robdragon234.clantags.impl;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.Member;
import com.robdragon234.clantags.impl.parser.DatabaseParser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLLog;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ClanTagsImpl
{
	public ClanTagsImpl()
	{
	}
	
	public List<Database> databases = new ArrayList<>();
	public HashMap<EntityPlayer, Faction> factionCache = new HashMap<>();
	
	/**
	 * Fetches users from the database
	 * @throws IOException If an error occurs while loading
	 */
	public void fetchDatabases(List<URL> dbLocs) throws IOException
	{
		for(URL dbLoc : dbLocs)
		{
			ClanTags.logger.info("Fetching users from database '" + dbLoc + "'");
			
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					dbLoc.openStream()
				)
			);
			
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = in.readLine()) != null)
			{
				sb.append(line).append("\n");
			}
			
			databases.add(DatabaseParser.getParser(sb.toString()).parse());
			
			in.close();
		}
		
		debugDatabases();
	}
	
	public void setNameTag(EntityPlayer player)
	{
		Faction faction = getFaction(player);
		if(faction != null)
		{
			player.addPrefix(new TextComponentString("[" + faction.tag + "]"));
		}
	}
	
	/**
	 * Returns the {@link Faction} that an {@link EntityPlayer} belongs to
	 * @param player the player
	 * @return the faction the player belongs to
	 */
	@Nullable
	public Faction getFaction(EntityPlayer player)
	{
		// Try retrieving from cache first
		try { return Objects.requireNonNull(factionCache.get(player)); } catch(Exception ignored){}
		
		for(Database database : databases)
		{
			for(Faction faction : database.factions)
			{
				for(Member member : faction.members)
				{
					for(String alias : member.aliases)
					{
						if(alias.equalsIgnoreCase(player.getName()))
						{
							return faction;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private void debugDatabases()
	{
		for(Database database : databases)
		{
			System.out.println("---------------");
			System.out.println(database.dbName);
			System.out.println(database.dbFormat);
			for(Faction faction : database.factions)
			{
				System.out.println(faction.id);
				System.out.println(faction.name);
				System.out.println(faction.desc);
				System.out.println(faction.discord);
				System.out.println(faction.tag);
				System.out.println(faction.wiki);
				for(Member member : faction.members)
				{
					System.out.println(member.name);
					System.out.println(member.aliases);
				}
			}
		}
	}
}

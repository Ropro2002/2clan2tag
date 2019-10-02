package com.robdragon234.clantags.impl;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.database.Database;
import com.robdragon234.clantags.impl.factions.Faction;
import com.robdragon234.clantags.impl.members.Member;
import com.robdragon234.clantags.impl.parser.DatabaseParser;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ClanTagsImpl
{
	public ClanTagsImpl()
	{
	}
	
	public List<Database> databases = new ArrayList<>();;
	
	/**
	 * Fetches users from the database
	 * @throws IOException If an error occurs while loading
	 */
	public void fetchDatabases(URL[] dbLocs) throws IOException
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
	
	void debugDatabases()
	{
		for(Database database : databases)
		{
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

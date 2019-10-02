package com.robdragon234.clantags.impl;

import com.robdragon234.clantags.ClanTags;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ClanTagsImpl
{
	public ClanTagsImpl()
	{
	}
	
	private HashMap<String, String> factionMembers = null;
	/**
	 * Fetches users from the database
	 * @throws IOException If an error occurs while loading
	 */
	public void fetchUsers(URL[] databases) throws IOException
	{
		factionMembers = new HashMap<>();
		
		for(URL dbLoc : databases)
		{
			ClanTags.logger.info("Fetching users from database '" + dbLoc + "'");
			
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					dbLoc.openStream()
				)
			);
			
			String line;
			while((line = in.readLine()) != null)
			{
				String[] splitLine = line.split(Pattern.quote(":"));
				if(splitLine.length == 2)
				{
					factionMembers.put(splitLine[0], splitLine[1]);
				}
			}
			
			ClanTags.logger.info("Found " + factionMembers.size() + " faction members");
			
			in.close();
		}
	}
}

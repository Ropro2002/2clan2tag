package com.clantags.impl;

import com.clantags.ClanTags;
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
	 * @throws MalformedURLException (should not occur in production)
	 * @throws IOException If an error occurs while loading
	 */
	public void fetchUsers() throws MalformedURLException, IOException
	{
		// TODO: Remove token before release, needed so that we can access the raw content in a private repo
		final URL dbLoc = new URL("https://raw.githubusercontent.com/Ropro2002/2clan2tag/master/api/users.txt?token=AM26DG3FCBNKGK5YWS5TYKC5TTSBK");
		
		BufferedReader in = new BufferedReader(
			new InputStreamReader(
				dbLoc.openStream()
			)
		);
		
		factionMembers = new HashMap<>();
		
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

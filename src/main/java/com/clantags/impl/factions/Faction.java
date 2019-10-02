package com.clantags.impl.factions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class Faction
{
	public String id;
	public String name;
	public String tag;
	public String desc;
	public String discord;
	public String wiki;
	public List<String> members;
	
	public Faction(String id, String name, String tag, String desc, String discord, String wiki, List<String> members)
	{
		this.id = id;
		this.name = name;
		this.tag = tag;
		this.desc = desc;
		this.discord = discord;
		this.wiki = wiki;
		this.members = members;
	}
	
	public ITextComponent getFormattedPlayerName(EntityPlayer player)
	{
		return player.getDisplayName().appendText(" [" + tag + "]");
	}
}

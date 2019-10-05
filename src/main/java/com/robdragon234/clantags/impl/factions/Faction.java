package com.robdragon234.clantags.impl.factions;

import com.robdragon234.clantags.impl.members.Member;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class Faction {
	private String id;
	private String name;
	private String tag;
	private String desc;
	private String discord;
	private String wiki;
	private List<Member> members;
	
	public Faction(
			String id,
			String name,
			String tag,
			String desc,
			String discord,
			String wiki
	) {
		this.id = id;
		this.name = name;
		this.tag = tag;
		this.desc = desc;
		this.discord = discord;
		this.wiki = wiki;
		this.members = new ArrayList<>();
	}
	
	public void addMember(Member member) {
		members.add(member);
	}
	
	public ITextComponent getFormattedPlayerName(EntityPlayer player) {
		return player.getDisplayName().appendText(" [" + tag + "]");
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getDiscord() {
		return discord;
	}
	
	public String getWiki() {
		return wiki;
	}
	
	public List<Member> getMembers() {
		return members;
	}
}

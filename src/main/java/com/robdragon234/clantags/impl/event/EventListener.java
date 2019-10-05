package com.robdragon234.clantags.impl.event;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.config.Configuration;
import com.robdragon234.clantags.impl.members.Member;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {
	
	public EventListener() {
	}
	
	@SubscribeEvent
	public void onEntityAddedToWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(event.getEntity().getName());
			if (event.getEntity() == Minecraft.getMinecraft().player) ClanTags.INSTANCE.setPlayer(member);
			if (member != null) {
				((EntityPlayer) event.getEntity()).addPrefix(new TextComponentString("[" + member.faction.getTag() + "]"));
			}
		}
	}
	
	@SubscribeEvent
	public void onSendChatMessage(ClientChatEvent event) {
		if (event.getMessage().startsWith(ClanTags.INSTANCE.getCommandManager().getPrefix())) {
			ClanTags.INSTANCE.getCommandManager().executeCommand(event.getMessage().substring(1));
			event.setCanceled(true);
		} else {
			System.out.println(event.getMessage());
			Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(Minecraft.getMinecraft().player.getName());
			if (member != null) {
				Minecraft.getMinecraft().player.addPrefix(new TextComponentString(member.faction.getTag()));
			}
		}
	}
	
	@SubscribeEvent
	public void onChatMessageReceive(ServerChatEvent event) {
		System.out.println("t");
		Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(event.getPlayer().getName());
		if (member != null) {
			if (Configuration.muteOtherGroups && !ClanTags.INSTANCE.getPlayer().faction.equals(member.faction)) {
				event.setCanceled(true);
			} else {
				event.getPlayer().addPrefix(new TextComponentString(member.faction.tag));
			}
		} else {
			if (Configuration.muteNoRank) event.setCanceled(true);
		}
	}
	
	
}

package com.robdragon234.clantags.impl.event;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.impl.members.Member;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {

    public EventListener() {}

    @SubscribeEvent
    public void onEntityAddedToWorld(EntityJoinWorldEvent event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            Member member = ClanTags.INSTANCE.getDatabaseManager().getMember(((EntityPlayer) event.getEntity()).getName());
            if (member != null) {
                ((EntityPlayer) event.getEntity()).addPrefix(new TextComponentString("[" + member.faction.tag + "]"));
            }
        }
    }

    @SubscribeEvent
    public void onSendChatMessage(ClientChatEvent event) {
        if(event.getMessage().startsWith(ClanTags.INSTANCE.getCommandManager().getPrefix())) {
            ClanTags.INSTANCE.getCommandManager().executeCommand(event.getMessage().substring(1));
            event.setCanceled(true);
        }
    }


}

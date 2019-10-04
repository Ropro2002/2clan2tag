package com.robdragon234.clantags.impl.event;

import com.robdragon234.clantags.ClanTags;
import com.robdragon234.clantags.api.util.MinecraftUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {

    public EventListener() {}

    @SubscribeEvent
    public void onEntityAddedToWorld(EntityJoinWorldEvent event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            MinecraftUtil.setNameTag((EntityPlayer)event.getEntity());
        }
    }

    @SubscribeEvent
    public void onSendChatMessage(ClientChatEvent event) {
        System.out.println("test");
        if(event.getMessage().startsWith(ClanTags.INSTANCE.getCommandManager().getPrefix())) {
            System.out.println("test");
            ClanTags.INSTANCE.getCommandManager().executeCommand(event.getMessage().substring(1));
            event.setCanceled(true);
        }

    }


}

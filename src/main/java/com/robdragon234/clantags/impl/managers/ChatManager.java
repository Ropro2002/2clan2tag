package com.robdragon234.clantags.impl.managers;

import net.minecraftforge.common.MinecraftForge;

public class ChatManager {

    public ChatManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}

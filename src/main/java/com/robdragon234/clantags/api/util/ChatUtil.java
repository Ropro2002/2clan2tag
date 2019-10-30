package com.robdragon234.clantags.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {
	
	public static void message(String message) {
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
	}
	
	public static void warning(String message) {
		message("\u00a7c[\u00a76\u00a7lWARNING\u00a7c]\u00a7r " + message);
	}
	
	public static void error(String message) {
		message("\u00a7c[\u00a74ERROR\u00a7c]\u00a7r " + message);
	}
	
	
}

package com.robdragon234.clantags.api.command;

import net.minecraft.client.Minecraft;

public abstract class Command {

    private String name;
    private String description;
    private String usage;

    protected static final Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String description, String usage) {
        this.name = name;
        this.description = description;
        this.usage = usage;
    }

    public abstract void call(String[] args);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUsage() { return this.usage;}

}

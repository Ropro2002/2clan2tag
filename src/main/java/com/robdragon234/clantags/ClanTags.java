package com.robdragon234.clantags;

import com.google.common.base.Throwables;
import com.robdragon234.clantags.impl.config.Configuration;
import com.robdragon234.clantags.impl.event.EventListener;
import com.robdragon234.clantags.impl.managers.CommandManager;
import com.robdragon234.clantags.impl.managers.DatabaseManager;
import com.robdragon234.clantags.impl.members.Member;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mod(
	modid = ClanTags.MOD_ID,
	name = ClanTags.MOD_NAME,
	version = ClanTags.VERSION
)
public class ClanTags
{
	public static final String MOD_ID = "clantags";
	public static final String MOD_NAME = "Clantags";
	public static final String VERSION = "0.1-SNAPSHOT";
	
	public static Logger logger;
	
	@Mod.Instance(MOD_ID)
	public static ClanTags INSTANCE;

	private CommandManager commandManager;
	private DatabaseManager databaseManager;
	private Member player;
	
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		logger.info("Initialising 2clan2tags...");
	}
	
	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventListener());
		try {
			// Array of string to a list of URLs
			List<URL> databases = Arrays.stream(Configuration.databases)
				.map(
					str ->
					{
						try
						{
							return new URL(str);
						} catch(Exception ignored){}
						return null;
					}
				)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

			databaseManager = new DatabaseManager(databases);
		}
		catch(Exception e) {
			displayErrorMessage(e);
			e.printStackTrace();
		}
		commandManager = new CommandManager();

	}
	
	/*@SubscribeEvent
	public void onEntityAddedToWorld(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			clanTagsImpl.setNameTag((EntityPlayer)event.getEntity());
		}
	}*/

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public Member getPlayer() {
		return player;
	}

	public void setPlayer(Member player) {
		this.player = player;
	}

	/**
	 * Displays an error message in a popup box
	 * @param t the error encountered
	 */
	public static void displayErrorMessage(Throwable t)
	{
		Frame frame = new Frame();
		
		frame.setAlwaysOnTop(true);
		frame.setState(Frame.ICONIFIED);
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea comp = new JTextArea(Throwables.getStackTraceAsString(t));
		comp.setEditable(false);
		JScrollPane scroll = new JScrollPane(comp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		StackTraceElement trace;
		if(t.getStackTrace().length > 0)
			trace = t.getStackTrace()[0];
		else
			trace = new StackTraceElement("", "", "", -1);
		
		JOptionPane.showMessageDialog(
			frame,
			panel,
			"ERROR encountered at " + trace.toString(),
			JOptionPane.ERROR_MESSAGE
		);
		
		frame.requestFocus();
	}
}

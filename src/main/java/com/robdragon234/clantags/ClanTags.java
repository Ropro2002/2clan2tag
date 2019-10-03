package com.robdragon234.clantags;

import com.google.common.base.Throwables;
import com.robdragon234.clantags.impl.ClanTagsImpl;
import com.robdragon234.clantags.impl.config.Configuration;
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
	public static final String VERSION = "2019.2-1.3.1";
	
	public static Logger logger;
	protected static ClanTagsImpl clanTagsImpl;
	
	@Mod.Instance(MOD_ID)
	public static ClanTags INSTANCE;
	
	
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
	public void postinit(FMLPostInitializationEvent event)
	{
		try
		{
			clanTagsImpl = new ClanTagsImpl();
			
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
				.collect(Collectors.toList());
			
			clanTagsImpl.fetchDatabases(databases);
		}
		catch(Exception e)
		{
			displayErrorMessage(e);
			e.printStackTrace();
		}
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

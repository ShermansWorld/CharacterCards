package me.ShermansWorld.CharacterCards;

import org.bukkit.ChatColor;


public class Helper {
	// convert a string to one that returns color from color codes
	public static String color(String message) { 
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
}

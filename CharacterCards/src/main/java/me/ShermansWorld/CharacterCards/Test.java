package me.ShermansWorld.CharacterCards;

import java.util.Map.Entry;

import org.bukkit.Bukkit;

import me.ShermansWorld.CharacterCards.config.Config;

public class Test {
	public static void checkCustomFieldsMap() {
		for (Entry<String, String> entry : Config.customFieldsMap.entrySet()) {
		    String fieldLabel = entry.getKey();
		    String fieldPlaceholder = entry.getValue();
		    Bukkit.broadcastMessage(fieldLabel);
		    Bukkit.broadcastMessage(fieldPlaceholder);
		}
	}
}

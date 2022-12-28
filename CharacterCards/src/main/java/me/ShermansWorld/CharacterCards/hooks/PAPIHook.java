package me.ShermansWorld.CharacterCards.hooks;

import java.util.Map.Entry;

import org.bukkit.entity.Player;

import me.ShermansWorld.CharacterCards.Helper;
import me.ShermansWorld.CharacterCards.config.Config;
import me.clip.placeholderapi.PlaceholderAPI;

public class PAPIHook {
	public static void displayCustomFields(Player player) {
		if (Config.usingCustomFields) {
			for (Entry<String, String> entry : Config.customFieldsMap.entrySet()) {
				String fieldLabel = entry.getKey();
				String fieldPlaceholder = PlaceholderAPI.setPlaceholders(player, entry.getValue());
				player.sendMessage(Helper.color(fieldLabel + " &8- " + fieldPlaceholder));
			}
		}
	}
	
	public static void displayCustomFields(Player player, Player target) {
		if (Config.usingCustomFields) {
			for (Entry<String, String> entry : Config.customFieldsMap.entrySet()) {
				String fieldLabel = entry.getKey();
				String fieldPlaceholder = PlaceholderAPI.setPlaceholders(target, entry.getValue());
				player.sendMessage(Helper.color(fieldLabel + " &8- " + fieldPlaceholder));
			}
		}
	}
}

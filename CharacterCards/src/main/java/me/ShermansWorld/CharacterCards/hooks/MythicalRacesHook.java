package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.ShermansWorld.CharacterCards.Helper;
import me.korbsti.mythicalraces.MythicalRaces;
import me.korbsti.mythicalraces.race.Race;

public class MythicalRacesHook {
	public static void displayMythicalRacesInfo(Player player) {
		MythicalRaces mythicRaces = JavaPlugin.getPlugin(MythicalRaces.class);
		Race race = mythicRaces.playersRace.get(player.getName());
		player.sendMessage(Helper.color("&3Race&8 - &b" + race.raceName));
	}

	public static void displayMythicalRacesInfo(Player player, OfflinePlayer target) {
		try {
			MythicalRaces mythicRaces = JavaPlugin.getPlugin(MythicalRaces.class);
			Race race = mythicRaces.playersRace.get(target.getName());
			player.sendMessage(Helper.color("&3Race&8 - &b" + race.raceName));
		} catch (NullPointerException e2) {
		}
	}
}

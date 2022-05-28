package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import konquest.Konquest;
import me.ShermansWorld.CharacterCards.commands.CharacterCommands;

public class KonquestHook {
	
	public static void displayKonquestInfo(Player player) {
		try {
			player.sendMessage(CharacterCommands.mess("&3Kingdom&8 - &b" + Konquest.getInstance().getPlayerManager()
					.getOfflinePlayer(Bukkit.getOfflinePlayer(player.getUniqueId())).getKingdom().getName()));
		} catch (NullPointerException e) {
		}
	}
	
	public static void displayKonquestInfo(Player player, String playerName) {
		try {
			player.sendMessage(
					CharacterCommands.mess("&3Kingdom&8 - &b" + Konquest.getInstance().getPlayerManager()
							.getOfflinePlayer(Bukkit.getOfflinePlayer(Bukkit.getPlayer(playerName).getUniqueId()))
							.getKingdom().getName()));
		} catch (NullPointerException e) {
		}
	}
	
	public static void displayKonquestInfo(Player player, Player target) {
		try {
			player.sendMessage(
					CharacterCommands.mess("&3Kingdom&8 - &b" + Konquest.getInstance().getPlayerManager()
							.getOfflinePlayer(Bukkit.getOfflinePlayer(target.getUniqueId()))
							.getKingdom().getName()));
		} catch (NullPointerException e2) {
		}
	}
}

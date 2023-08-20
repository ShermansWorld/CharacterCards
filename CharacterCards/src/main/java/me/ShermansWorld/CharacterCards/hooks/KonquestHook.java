package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.github.rumsfield.konquest.Konquest;

import me.ShermansWorld.CharacterCards.Helper;

public class KonquestHook {

	public static void displayKonquestInfo(Player player) {
		try {
			player.sendMessage(Helper.color("&3Kingdom&8 - &b" + Konquest.getInstance().getPlayerManager()
					.getOfflinePlayer(Bukkit.getOfflinePlayer(player.getUniqueId())).getKingdom().getName()));
		} catch (NullPointerException e) {
		}
	}

	public static void displayKonquestInfo(Player player, OfflinePlayer target) {
		try {
			player.sendMessage(Helper.color("&3Kingdom&8 - &b" + Konquest.getInstance().getPlayerManager()
					.getOfflinePlayer(target).getKingdom().getName()));
		} catch (NullPointerException e2) {
		}
	}
}

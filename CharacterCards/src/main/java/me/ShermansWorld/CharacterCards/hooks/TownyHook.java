package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;

import me.ShermansWorld.CharacterCards.Helper;

public class TownyHook {
	public static void displayTownyInfo(Player player) {
		try {
			player.sendMessage(
					Helper.color("&3Town&8 - &b" + TownyAPI.getInstance().getResident(player).getTown().getName()));
		} catch (NotRegisteredException e) {
		}
		try {
			player.sendMessage(
					Helper.color("&3Nation&8 - &b" + TownyAPI.getInstance().getResident(player).getNation().getName()));
		} catch (TownyException e) {
		}
	}

	public static void displayTownyInfo(Player player, OfflinePlayer target) {
		try {
			player.sendMessage(
					Helper.color("&3Town&8 - &b" + TownyAPI.getInstance().getResident(target.getUniqueId()).getTown().getName()));
		} catch (NotRegisteredException e2) {
		}
		try {
			player.sendMessage(
					Helper.color("&3Nation&8 - &b" + TownyAPI.getInstance().getResident(target.getUniqueId()).getNation().getName()));
		} catch (TownyException e2) {
		}
	}
}

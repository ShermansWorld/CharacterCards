package me.ShermansWorld.CharacterCards.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.CharacterCards.Main;
import me.ShermansWorld.CharacterCards.config.ConfigVals;

public class CharacterCardsCommands implements CommandExecutor {
	String version = Bukkit.getPluginManager().getPlugin("CharacterCards").getDescription().getVersion();

	public CharacterCardsCommands(Main plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(
					mess("&8&l&m--------------&7[&3CharacterCards &7v&b" + this.version + "&7]&8&l&m--------------\n"));
			player.sendMessage(mess("&7/&3CharacterCards reload &8- &3Reloads the config.yml"));
			player.sendMessage(mess("&8&l&m---------------------------------------------"));
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				if (args.length == 0) {
					player.sendMessage(mess("&8&l&m--------------&7[&3CharacterCards &7v&b" + this.version
							+ "&7]&8&l&m--------------\n"));
					player.sendMessage(mess("&7/&3CharacterCards reload &8- &3Reloads the config.yml"));
					player.sendMessage(mess("&8&l&m---------------------------------------------"));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (!player.hasPermission("CharacterCards.reload")) {
					player.sendMessage(mess("&7[&3CharacterCards&7] &cYou do not have permission to do this"));
					return false;
				}
				Main.getInstance().reloadConfig();
				Main.getInstance().saveDefaultConfig();
				ConfigVals.initConfigVals();
				player.sendMessage(mess("&7[&3CharacterCards&7] &econfig.yml reloaded"));
				return true;
			}
		}
		return false;
	}

	public static String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

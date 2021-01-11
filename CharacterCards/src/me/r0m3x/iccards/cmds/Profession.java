package me.r0m3x.iccards.cmds;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;

public class Profession implements CommandExecutor {

	public Profession(Main plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			player.sendMessage(mess(
					"&7[&c*&7] &7You must enter a &eProfession&7! &7(&eBaker&7,&eFarmer&7,&eMiner&7,&eSoldier&7)"));
			return true;
		}
		if (args.length == 1) {
			if (API.getProfession(player).isEmpty()) {
				if (args[0].equalsIgnoreCase("baker")) {
					player.sendMessage(mess("&7[&a*&7] &7You chose the &eBaker &7profession!"));
					File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator
							+ player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Profession", "Baker");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"lp user " + player.getName() + " group remove soldier");
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("farmer")) {
					player.sendMessage(mess("&7[&a*&7] &7You chose the &eFarmer &7profession!"));
					File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator
							+ player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Profession", "Farmer");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"lp user " + player.getName() + " group remove soldier");
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("miner")) {
					player.sendMessage(mess("&7[&a*&7] &7You chose the &eMiner &7profession!"));
					File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator
							+ player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Profession", "Miner");
					PotionEffect haste1 = new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0);
					player.addPotionEffect(haste1);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"lp user " + player.getName() + " group remove soldier");
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("soldier")) {
					player.sendMessage(mess("&7[&a*&7] &7You chose the &eSoldier &7profession!"));
					File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator
							+ player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Profession", "Soldier");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"lp user " + player.getName() + " group add soldier");
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
			} else {
		          player.sendMessage(mess("&7[&c*&7] &7You already chose a profession&e!&7 Your current profession: &e" + API.getProfession(player)));
		          return false;
			} 
			return false;
		}
		return false;
	}

	private String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

package me.ShermansWorld.CharacterCards.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.ShermansWorld.CharacterCards.Main;
import me.ShermansWorld.CharacterCards.config.ConfigVals;
import me.ShermansWorld.CharacterCards.hooks.KonquestHook;
import me.ShermansWorld.CharacterCards.hooks.MythicalRacesHook;
import me.ShermansWorld.CharacterCards.hooks.TownyHook;
import me.ShermansWorld.CharacterCards.lang.Languages;

public class CharacterCommands implements CommandExecutor {
	String version = Bukkit.getPluginManager().getPlugin("CharacterCards").getDescription().getVersion();
	YamlConfiguration lang = Languages.getLang();

	public CharacterCommands(Main plugin) {
	}

	public boolean checkcolorperms(Player player, String argument) {
		String str = argument;
		while (str.contains("&")) {
			int andIndex = str.indexOf('&');
			if (andIndex == str.length() - 1)
				return false;
			String colour = str.substring(andIndex + 1, andIndex + 2);
			if (!player.hasPermission("CharacterCards.color." + colour))
				return false;
			str = str.substring(andIndex + 1);
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("character") || cmd.getName().equalsIgnoreCase("char")) {
			if (args == null || args.length == 0) {
				player.sendMessage(mess(
						"&8&l&m--------------&7[&3CharacterCards &7v&b" + this.version + "&7]&8&l&m--------------\n"));
				player.sendMessage(mess("&7/&3Char Name " + lang.getString("CharacterCommands.NameHelp")));
				player.sendMessage(mess("&7/&3Char Gender " + lang.getString("CharacterCommands.GenderHelp")));
				player.sendMessage(mess("&7/&3Char Age &7<&b" + String.valueOf(ConfigVals.minAge) + "&7-&b"
						+ String.valueOf(ConfigVals.maxAge) + "&7> &8- "
						+ lang.getString("CharacterCommands.AgeHelp")));
				player.sendMessage(mess("&7/&3Char Desc " + lang.getString("CharacterCommands.DescHelp")));
				player.sendMessage(mess("&7/&3Char View " + lang.getString("CharacterCommands.ViewHelp")));
				player.sendMessage(mess("&8&l&m---------------------------------------------"));
				return true;
			}
			if (args[0].equalsIgnoreCase("help") && args.length == 1) {
				player.sendMessage(mess(
						"&8&l&m--------------&7[&3CharacterCards &7v&b" + this.version + "&7]&8&l&m--------------\n"));
				player.sendMessage(mess("&7/&3Char Name " + lang.getString("CharacterCommands.NameHelp")));
				player.sendMessage(mess("&7/&3Char Gender " + lang.getString("CharacterCommands.GenderHelp")));
				player.sendMessage(mess("&7/&3Char Age &7<&b" + String.valueOf(ConfigVals.minAge) + "&7-&b"
						+ String.valueOf(ConfigVals.maxAge) + "&7> &8- "
						+ lang.getString("CharacterCommands.AgeHelp")));
				player.sendMessage(mess("&7/&3Char Desc " + lang.getString("CharacterCommands.DescHelp")));
				player.sendMessage(mess("&7/&3Char View " + lang.getString("CharacterCommands.ViewHelp")));
				player.sendMessage(mess("&8&l&m---------------------------------------------"));
				return true;
			}
			if (args[0].equalsIgnoreCase("name") && args.length == 1) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.NameError2")));
				return false;
			}
			if (args[0].equalsIgnoreCase("name") && args.length > 4) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.NameError1")));
				return false;
			}
			if (args[0].equalsIgnoreCase("name") && args.length >= 2) {
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + player.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				String first_name = args[1].toString();
				String last_name = "null";
				String title = "null";
				if (args.length == 3) {
					last_name = args[2].toString();
				} else if (args.length == 4) {
					last_name = args[2].toString();
					title = args[3].toString();
				}
				if (last_name.equalsIgnoreCase("null")) {
					last_name = "";
				}
				if (title.equalsIgnoreCase("null")) {
					title = "";
				}
				if (checkcolorperms(player, first_name) && checkcolorperms(player, last_name)
						&& checkcolorperms(player, title)) {
					yamlConfiguration.set("Name", first_name + " " + last_name + " " + title);
					player.sendMessage(mess("&7[&a*&7] " + lang.getString("CharacterCommands.NameSuccess") + ": &e"
							+ yamlConfiguration.get("Name")));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("NoColors")));
				return false;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length == 1) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.GenderError1")));
				return false;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length > 2) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.GenderError2")));
				return false;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length == 2) {
				String gender = args[1].toString();
				if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("boy") || gender.equalsIgnoreCase("m")
						|| gender.equalsIgnoreCase("man")) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Gender", "Male");
					player.sendMessage(mess("&7[&a*&7] " + lang.getString("CharacterCommands.GenderSuccess1")));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("girl") || gender.equalsIgnoreCase("f")
						|| gender.equalsIgnoreCase("woman")) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Gender", "Female");
					player.sendMessage(mess("&7[&a*&7] " + lang.getString("CharacterCommands.GenderSuccess2")));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				if (gender.equalsIgnoreCase("other") || gender.equalsIgnoreCase("o")
						|| gender.equalsIgnoreCase("none")) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Gender", "Other");
					player.sendMessage(mess("&7[&a*&7] " + lang.getString("CharacterCommands.GenderSuccess3")));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.GenderError3")));
				return true;
			}
			if (args[0].equalsIgnoreCase("delete") && args.length == 1
					&& player.hasPermission("CharacterCards.admin.delete")) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.DeleteError3")));
				return false;
			}
			if (args[0].equalsIgnoreCase("delete") && args.length == 2
					&& player.hasPermission("CharacterCards.admin.delete")) {
				Player target = Bukkit.getPlayerExact(args[1]);
				if (target != null) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + target.getPlayer().getUniqueId() + ".yml");
					@SuppressWarnings("unused")
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					if (f.exists()) {
						f.delete();
						String msg = "&7[&a*&7] " + lang.getString("CharacterCommands.DeleteSuccess");
						if (msg.contains("@PLAYER")) {
							msg = msg.replaceAll("@PLAYER", target.getName());
						}
						player.sendMessage(mess(msg));
						return true;
					} else {
						String msg = "&7[&c*&7] &7" + lang.getString("CharacterCommands.DeleteError1");
						if (msg.contains("@PLAYER")) {
							msg = msg.replaceAll("@PLAYER", target.getName());
						}
						player.sendMessage(mess(msg));
					}
					return false;
				}
				player.sendMessage(mess("&7[&c*&7] &7" + args[1] + lang.getString("CharacterCommands.DeleteError2")));
				return false;
			}
			if (args[0].equalsIgnoreCase("age") && args.length == 1) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.AgeError1") + " &7"
						+ String.valueOf(ConfigVals.minAge) + " &7- " + String.valueOf(ConfigVals.maxAge)));
				return false;
			}
			if (args[0].equalsIgnoreCase("age") && args.length > 2) {
				player.sendMessage(mess("&7[&c*&7] &c" + lang.getString("CharacterCommands.AgeError2") + " &7"
						+ String.valueOf(ConfigVals.minAge) + " &7- " + String.valueOf(ConfigVals.maxAge)));
				return false;
			}
			if (args[0].equalsIgnoreCase("age") && args.length == 2) {
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + player.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				int age = 0;
				try {
					age = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					player.sendMessage(mess("&7[&3CharacterCards&7] " + lang.getString("CharacterCommands.Invalid")));
					return false;
				}
				if (age <= ConfigVals.maxAge && age >= ConfigVals.minAge) {
					yamlConfiguration.set("Age", Integer.valueOf(age));
					player.sendMessage(
							mess("&7[&a*&7] " + lang.getString("CharacterCommands.AgeSuccess") + ":&e " + age));
					try {
						yamlConfiguration.save(f);
						return true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.AgeError3") + " "
							+ String.valueOf(ConfigVals.minAge) + " &7-" + String.valueOf(ConfigVals.maxAge)));
					return false;
				}
			}
			if (args[0].equalsIgnoreCase("desc") && args.length == 1) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.DescError1")));
				return false;
			}
			if (args[0].equalsIgnoreCase("description") && args.length == 1) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.DescError1")));
				return false;
			}
			if (args[0].equalsIgnoreCase("desc") || args[0].equalsIgnoreCase("description")) {
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + player.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				if (args[1].equalsIgnoreCase("clear")) {
					String desc = "";
					yamlConfiguration.set("Physical Description", desc);
					try {
						player.sendMessage(mess("&7[&a*&7] " + lang.getString("CharacterCommands.DescCleared")));
						yamlConfiguration.save(f);
					} catch (IOException e) {
						return false;
					}
					return true;
				}
				if (args[1].equalsIgnoreCase("add")) {
					String desc = (String) yamlConfiguration.get("Physical Description");
					if (desc == null) {
						desc = "";
					}
					for (int i = 2; i < args.length; i++) {
						desc = desc + args[i] + " ";
					}
					if (desc.length() > ConfigVals.descCharLimit) {
						player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.DescError2")));
						return false;
					}
					yamlConfiguration.set("Physical Description", desc);
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						return false;
					}
					player.sendMessage(
							mess("&7[&a*&7] " + lang.getString("CharacterCommands.DescSuccess") + ": &7" + desc));
					return true;
				}
				String desc = "";
				for (int i = 1; i < args.length; i++)
					desc = String.valueOf(desc) + args[i] + " ";
				if (checkcolorperms(player, desc)) {
					yamlConfiguration.set("Physical Description", desc);
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						return false;
					}
				} else {
					player.sendMessage(mess("&7[&c*&7] " + lang.getString("NoColors")));
					return false;
				}
				player.sendMessage(
						mess("&7[&a*&7] " + lang.getString("CharacterCommands.DescSuccess") + ": &7" + desc));
				return true;
			}
			if (args[0].equalsIgnoreCase("view") && args.length > 2) {
				player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.ViewError1")));
				return false;
			}
			if (args[0].equalsIgnoreCase("view") && args.length == 1) {
				try {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					String msg = lang.getString("Cards.Card");
					if (msg.contains("@PLAYER")) {
						msg = msg.replaceAll("@PLAYER", player.getName());
					}
					player.sendMessage(mess("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
					player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
					if (!yamlConfiguration.getString("Name").isEmpty()) {
						player.sendMessage(
								mess("&3" + lang.getString("Cards.Name") + " &8- &b" + yamlConfiguration.get("Name")));
					} else {
						player.sendMessage(
								mess("&3" + lang.getString("Cards.Name") + " &8- &b" + lang.getString("EmptyField")));
					}
					if (!yamlConfiguration.getString("Gender").isEmpty()) {
						player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
								+ Languages.translateGender(yamlConfiguration, lang)));
					} else {
						player.sendMessage(
								mess("&3" + lang.getString("Cards.Gender") + " &8- &b" + lang.getString("EmptyField")));
					}
					if (!yamlConfiguration.getString("Age").isEmpty()) {
						player.sendMessage(
								mess("&3" + lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
					} else {
						player.sendMessage(
								mess("&3" + lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
					}
					if (Main.usingMythicalRaces && ConfigVals.integrateMythicalRaces) {
						MythicalRacesHook.displayMythicalRacesInfo(player);
					}
					if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation if
																		// they are in one
						TownyHook.displayTownyInfo(player);
					}
					if (Main.usingKonquest && ConfigVals.integrateKonquest) {
						KonquestHook.displayKonquestInfo(player);
					}
					String msg2 = lang.getString("Cards.Desc");
					if (msg2.contains("@PLAYER")) {
						msg2 = msg2.replaceAll("@PLAYER", player.getName());
					}
					player.sendMessage(mess("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
					if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
						player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
					} else {
						player.sendMessage(mess("&b" + lang.getString("EmptyField")));
					}
					player.sendMessage(mess("&8&l&m-----------------------------------"));
					return true;
				} catch (NumberFormatException | NullPointerException e) {
					player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.ViewError2")));
					return false;
				}
			}
			if (args[0].equalsIgnoreCase("view") && args.length == 2) {
				Player target = Bukkit.getPlayerExact(args[1]);
				try {
					if (target != null) {
						File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
								+ File.separator + target.getPlayer().getUniqueId() + ".yml");
						YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
						String msg = lang.getString("Cards.Card");
						if (msg.contains("@PLAYER")) {
							msg = msg.replaceAll("@PLAYER", target.getPlayer().getName());
						}
						player.sendMessage(mess("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
						player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
						if (!yamlConfiguration.getString("Name").isEmpty()) {
							player.sendMessage(mess(
									"&3" + lang.getString("Cards.Name") + " &8- &b" + yamlConfiguration.get("Name")));
						} else {
							player.sendMessage(mess(
									"&3" + lang.getString("Cards.Name") + " &8- &b" + lang.getString("EmptyField")));
						}
						if (!yamlConfiguration.getString("Gender").isEmpty()) {
							player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
									+ Languages.translateGender(yamlConfiguration, lang)));
						} else {
							player.sendMessage(mess(
									"&3" + lang.getString("Cards.Gender") + " &8- &b" + lang.getString("EmptyField")));
						}
						if (!yamlConfiguration.getString("Age").isEmpty()) {
							player.sendMessage(mess(
									"&3" + lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
						} else {
							player.sendMessage(mess(
									"&3" + lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
						}
						if (Main.usingMythicalRaces && ConfigVals.integrateMythicalRaces) {
							MythicalRacesHook.displayMythicalRacesInfo(player, args[1]);
						}
						if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation
																			// if they are in one
							TownyHook.displayTownyInfo(player, args[1]);
						}
						if (Main.usingKonquest && ConfigVals.integrateKonquest) {
							KonquestHook.displayKonquestInfo(player, args[1]);
						}
						String msg2 = lang.getString("Cards.Desc");
						if (msg2.contains("@PLAYER")) {
							msg2 = msg2.replaceAll("@PLAYER", target.getPlayer().getName());
						}
						player.sendMessage(mess("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
						if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
							player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
						} else {
							player.sendMessage(mess("&b" + lang.getString("EmptyField")));
						}
						player.sendMessage(mess("&8&l&m-----------------------------------"));
						return true;
					}
					player.sendMessage(mess(
							"&7[&3CharacterCards&7] &3" + args[1] + lang.getString("CharacterCommands.ViewError3")));
					return false;
				} catch (NumberFormatException | NullPointerException e) {
					player.sendMessage(mess("&7[&c*&7] " + lang.getString("CharacterCommands.ViewError2")));
					return false;
				}
			}
			player.sendMessage(mess("&7[&3CharacterCards&7] " + lang.getString("CharacterCommands.Invalid")));
			return false;
		}
		return false;
	}

	public static String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

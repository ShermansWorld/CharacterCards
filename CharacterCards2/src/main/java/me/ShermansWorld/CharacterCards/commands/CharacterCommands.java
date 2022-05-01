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

public class CharacterCommands implements CommandExecutor {
	String version = Bukkit.getPluginManager().getPlugin("CharacterCards").getDescription().getVersion();

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
				player.sendMessage(mess("&7/&3Char Name &7<&bFirst&7> &7{&bLast&7} &8- &3Set your character's name."));
				player.sendMessage(
						mess("&7/&3Char Gender &7<&bMale&7/&bFemale/&bOther&7> &8- &3Set your character's gender."));
				player.sendMessage(mess("&7/&3Char Age &7<&b" + String.valueOf(ConfigVals.minAge) + "&7-&b"
						+ String.valueOf(ConfigVals.maxAge) + "&7> &8- &3Set your character's age."));
				player.sendMessage(mess("&7/&3Char Desc &7<&bDescription&7> &8- &3Set your character's description."));
				player.sendMessage(mess("&7/&3Char View &7{&bPlayer&7} &8- &3View your card, or another player's!"));
				player.sendMessage(mess("&8&l&m---------------------------------------------"));
				return true;
			}
			if (args[0].equalsIgnoreCase("help") && args.length == 1) {
				player.sendMessage(mess(
						"&8&l&m--------------&7[&3CharacterCards &7v&b" + this.version + "&7]&8&l&m--------------\n"));
				player.sendMessage(mess("&7/&3Char Name &7<&bFirst&7> &7{&bLast&7} &8- &3Set your character's name."));
				player.sendMessage(
						mess("&7/&3Char Gender &7<&bMale&7/&bFemale/&bOther&7> &8- &3Set your character's gender."));
				player.sendMessage(mess("&7/&3Char Age &7<&b" + String.valueOf(ConfigVals.minAge) + "&7-&b"
						+ String.valueOf(ConfigVals.maxAge) + "&7> &8- &3Set your character's age."));
				player.sendMessage(mess("&7/&3Char Desc &7<&bDescription&7> &8- &3Set your character's description."));
				player.sendMessage(mess("&7/&3Char View &7{&bPlayer&7} &8- &3View your card, or another player's!"));
				player.sendMessage(mess("&8&l&m---------------------------------------------"));
				return true;
			}
			if (args[0].equalsIgnoreCase("name") && args.length > 3) {
				player.sendMessage(
						mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Name &7<&cFirst&7> &7{&cLast&7}"));
				return true;
			}
			if (args[0].equalsIgnoreCase("name") && args.length >= 2) {
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + player.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				String first_name = args[1].toString();
				String last_name = "null";
				if (args.length == 3) {
					last_name = args[2].toString();
				}
				if (last_name.equalsIgnoreCase("null"))
					last_name = "";
				if (checkcolorperms(player, first_name) && checkcolorperms(player, last_name)) {
					yamlConfiguration.set("Name", first_name + " " + last_name);
					player.sendMessage(mess("&7[&a*&7] &3Name set as: &e" + yamlConfiguration.get("Name")));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				player.sendMessage(mess("&7[&c*&7] &cYou cannot use colors!"));
				return true;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length == 1) {
				player.sendMessage(
						mess("&7[&c*&7] &7You must enter a gender&c! &7(&cMale &7or &cFemale &7or &cOther)"));
				return true;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length > 2) {
				player.sendMessage(mess(
						"&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Gender &7(&cMale &7or &cFemale &7or &cOther)"));
				return true;
			}
			if (args[0].equalsIgnoreCase("gender") && args.length == 2) {
				String gender = args[1].toString();
				if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("boy") || gender.equalsIgnoreCase("m")
						|| gender.equalsIgnoreCase("man")) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					yamlConfiguration.set("Gender", "Male");
					player.sendMessage(mess("&7[&a*&7] &3Gender set as: &eMale"));
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
					player.sendMessage(mess("&7[&a*&7] &3Gender set as: &eFemale"));
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
					player.sendMessage(mess("&7[&a*&7] &3Gender set as: &eOther"));
					try {
						yamlConfiguration.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				player.sendMessage(mess("&7[&c*&7] &cYou must be Male&7/&cFemale&7/&cOther"));
				return true;
			}
			if (args[0].equalsIgnoreCase("delete") && args.length == 1
					&& player.hasPermission("CharacterCards.admin.delete")) {
				player.sendMessage(mess("&7[&c*&7] &7You must enter a &eplayer's name&7!"));
				return true;
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
						player.sendMessage(
								mess("&7[&a*&7] &aDeleted &7" + target.getName() + "'s &aconfiguration file!"));
					} else {
						player.sendMessage(
								mess("&7[&c*&7] &7" + target.getName() + "'s &cconfiguration file doesn't exist!"));
					}
					return true;
				}
				player.sendMessage(mess("&7[&c*&7] &7" + args[1] + " &cisn't online!"));
				return true;
			} else {
				if (args[0].equalsIgnoreCase("age") && args.length == 1) {
					player.sendMessage(mess("&7[&c*&7] &cYou must enter a age! &eUsage&7: &7/&cChar Age &c "
							+ String.valueOf(ConfigVals.minAge) + " &7-&c " + String.valueOf(ConfigVals.maxAge)));
					return true;
				}
				if (args[0].equalsIgnoreCase("age") && args.length > 2) {
					player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Age &c "
							+ String.valueOf(ConfigVals.minAge) + " &7-&c " + String.valueOf(ConfigVals.maxAge)));
					return true;
				}
				if (args[0].equalsIgnoreCase("age") && args.length == 2) {
					File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
							+ File.separator + player.getPlayer().getUniqueId() + ".yml");
					YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
					int age = 0;
					age = Integer.parseInt(args[1]);
					if (age <= ConfigVals.maxAge && age >= ConfigVals.minAge) {
						yamlConfiguration.set("Age", Integer.valueOf(age));
						player.sendMessage(mess("&7[&a*&7] &3Age set as:&e " + age));
						try {
							yamlConfiguration.save(f);
							return true;
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						player.sendMessage(mess("&7[&c*&7] &7Age must be &c " + String.valueOf(ConfigVals.minAge)
								+ " &7-&c " + String.valueOf(ConfigVals.maxAge)));
						return true;
					}
				} else {
					if (args[0].equalsIgnoreCase("desc") && args.length == 1) {
						player.sendMessage(mess("&7[&c*&7] &7You must set a description&c!"));
						return true;
					}
					if (args[0].equalsIgnoreCase("description") && args.length == 1) {
						player.sendMessage(mess("&7[&c*&7] &7You must set a description&c!"));
						return true;
					}
					if (args[0].equalsIgnoreCase("desc") || args[0].equalsIgnoreCase("description")) {
						File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
								+ File.separator + player.getPlayer().getUniqueId() + ".yml");
						YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
						String desc = "";
						for (int i = 1; i < args.length; i++)
							desc = String.valueOf(desc) + args[i] + " ";
						if (checkcolorperms(player, desc)) {
							yamlConfiguration.set("Physical Description", desc);
							try {
								yamlConfiguration.save(f);
							} catch (IOException e) {
								e.printStackTrace();
								return true;
							}
						} else {
							player.sendMessage(mess("&7[&c*&7] &cYou cannot use colors!"));
							return true;
						}
						player.sendMessage(mess("&7[&a*&7] &3Description as: &7" + desc));
						return true;
					}
					if (args[0].equalsIgnoreCase("view") && args.length > 2) {
						player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar View &8<&cPlayer&8>"));
						return true;
					}
					if (args[0].equalsIgnoreCase("view") && args.length == 1) {
						try {
							File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
									+ File.separator + player.getPlayer().getUniqueId() + ".yml");
							YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
							player.sendMessage(
									mess("&8&l&m-----------&7[&3" + player.getName() + "'s Card&7]&8&l&m-----------"));
							player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
							if (!yamlConfiguration.getString("Name").isEmpty()) {
								player.sendMessage(mess("&3Name &8- &b" + yamlConfiguration.get("Name")));
							} else {
								player.sendMessage(mess("&3Name &8- &bEmpty"));
							}
							if (!yamlConfiguration.getString("Gender").isEmpty()) {
								player.sendMessage(mess("&3Gender &8- &b" + yamlConfiguration.get("Gender")));
							} else {
								player.sendMessage(mess("&3Gender &8- &bEmpty"));
							}
							if (!yamlConfiguration.getString("Age").isEmpty()) {
								player.sendMessage(mess("&3Age &8- &b" + yamlConfiguration.get("Age")));
							} else {
								player.sendMessage(mess("&3Age &8- &bEmpty"));
							}
							player.sendMessage(
									mess("&8&l&m-----------&7[&3" + player.getName() + "'s Desc&7]&8&l&m-----------"));
							if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
								player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
							} else {
								player.sendMessage(mess("&bEmpty"));
							}
							player.sendMessage(mess("&8&l&m-----------------------------------"));
							return true;
						} catch (NumberFormatException | NullPointerException e) {
							player.sendMessage(mess("&7[&c*&7] &cThis card does not exist! Was it recently deleted?"));
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
								player.sendMessage(mess("&8&l&m-----------&7[&3" + target.getPlayer().getName()
										+ "'s Card&7]&8&l&m-----------"));
								player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
								if (!yamlConfiguration.getString("Name").isEmpty()) {
									player.sendMessage(mess("&3Name &8- &b" + yamlConfiguration.get("Name")));
								} else {
									player.sendMessage(mess("&3Name &8- &bEmpty"));
								}
								if (!yamlConfiguration.getString("Gender").isEmpty()) {
									player.sendMessage(mess("&3Gender &8- &b" + yamlConfiguration.get("Gender")));
								} else {
									player.sendMessage(mess("&3Gender &8- &bEmpty"));
								}
								if (!yamlConfiguration.getString("Age").isEmpty()) {
									player.sendMessage(mess("&3Age &8- &b" + yamlConfiguration.get("Age")));
								} else {
									player.sendMessage(mess("&3Age &8- &bEmpty"));
								}
								player.sendMessage(mess("&8&l&m-----------&7[&3" + target.getPlayer().getName()
										+ "'s Desc&7]&8&l&m-----------"));
								if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
									player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
								} else {
									player.sendMessage(mess("&bEmpty"));
								}
								player.sendMessage(mess("&8&l&m-----------------------------------"));
								return true;
							}
							player.sendMessage(mess(
									"&7[&3CharacterCards&7] &3" + args[1] + " &7is not online/has never joined before&3."));
							return true;
						} catch (NumberFormatException | NullPointerException e) {
							player.sendMessage(mess("&7[&c*&7] &cThis card does not exist! Was it recently deleted?"));
							return false;
						}
					}
					player.sendMessage(mess("&7[&3CharacterCards&7] &3Invalid Command"));
					return true;
				}
			}
		}
		return false;
	}

	public static String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

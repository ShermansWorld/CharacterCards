package me.ShermansWorld.CharacterCards;

import java.io.File;
import me.ShermansWorld.CharacterCards.config.Config;
import me.ShermansWorld.CharacterCards.hooks.KonquestHook;
import me.ShermansWorld.CharacterCards.hooks.MagicHook;
import me.ShermansWorld.CharacterCards.hooks.MythicalRacesHook;
import me.ShermansWorld.CharacterCards.hooks.PAPIHook;
import me.ShermansWorld.CharacterCards.hooks.TownyHook;
import me.ShermansWorld.CharacterCards.lang.Languages;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Cards {
	private static YamlConfiguration lang = Languages.getLang();

	public static boolean displayCard(Player player) {
		try {
			File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users" + File.separator
					+ player.getUniqueId() + ".yml");
			YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
			String msg = lang.getString("Cards.Card");
			if (msg.contains("@PLAYER"))
				msg = msg.replaceAll("@PLAYER", player.getName());
			int titleLen = msg.length() + 22;
			player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
			player.sendMessage(Helper.color("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
			if (!yamlConfiguration.getString("Name").isEmpty()) {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Name") + " &8- &b" + yamlConfiguration.get("Name")));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Name") + " &8- &b" + lang.getString("EmptyField")));
			}
			if (!yamlConfiguration.getString("Gender").isEmpty()) {
				player.sendMessage(Helper.color("&3" + lang.getString("Cards.Gender") + " &8- &b"
						+ Languages.translateGender(yamlConfiguration, lang)));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Gender") + " &8- &b" + lang.getString("EmptyField")));
			}
			if (!yamlConfiguration.getString("Age").isEmpty()) {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
			}
			displayHooks(player);
			String msg2 = lang.getString("Cards.Desc");
			if (msg2.contains("@PLAYER"))
				msg2 = msg2.replaceAll("@PLAYER", player.getName());
			player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
			if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
				player.sendMessage(Helper.color("&b" + yamlConfiguration.get("Physical Description")));
			} else {
				player.sendMessage(Helper.color("&b" + lang.getString("EmptyField")));
			}
			String linebreak = "&8&l&m";
			for (int i = 0; i < titleLen - 2; i++)
				linebreak = String.valueOf(linebreak) + "-";
			player.sendMessage(Helper.color(linebreak));
			return true;
		} catch (NumberFormatException | NullPointerException e) {
			player.sendMessage(Helper.color("&7[&c*&7] " + lang.getString("CharacterCommands.ViewError2")));
			return false;
		}
	}

	public static boolean displayCard(Player player, OfflinePlayer target, String targetArg) {
		try {
			if (target != null) {
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + target.getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				String msg = lang.getString("Cards.Card");
				if (msg.contains("@PLAYER"))
					msg = msg.replaceAll("@PLAYER", target.getName());
				int titleLen = msg.length() + 22;
				player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
				player.sendMessage(Helper.color("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
				if (!yamlConfiguration.getString("Name").isEmpty()) {
					player.sendMessage(Helper
							.color("&3" + lang.getString("Cards.Name") + " &8- &b" + yamlConfiguration.get("Name")));
				} else {
					player.sendMessage(Helper
							.color("&3" + lang.getString("Cards.Name") + " &8- &b" + lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Gender").isEmpty()) {
					player.sendMessage(Helper.color("&3" + lang.getString("Cards.Gender") + " &8- &b"
							+ Languages.translateGender(yamlConfiguration, lang)));
				} else {
					player.sendMessage(Helper
							.color("&3" + lang.getString("Cards.Gender") + " &8- &b" + lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Age").isEmpty()) {
					player.sendMessage(Helper
							.color("&3" + lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
				} else {
					player.sendMessage(Helper
							.color("&3" + lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
				}
				displayHooks(player, target);
				String msg2 = lang.getString("Cards.Desc");
				if (msg2.contains("@PLAYER"))
					msg2 = msg2.replaceAll("@PLAYER", target.getName());
				player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
				if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
					player.sendMessage(Helper.color("&b" + yamlConfiguration.get("Physical Description")));
				} else {
					player.sendMessage(Helper.color("&b" + lang.getString("EmptyField")));
				}
				String linebreak = "&8&l&m";
				for (int i = 0; i < titleLen - 2; i++)
					linebreak = String.valueOf(linebreak) + "-";
				player.sendMessage(Helper.color(linebreak));
				return true;
			}
			player.sendMessage(
					Helper.color("&7[&3CharacterCards&7] &3" + targetArg + lang.getString("CharacterCommands.ViewError3")));
			return false;
		} catch (NumberFormatException | NullPointerException e) {
			player.sendMessage(Helper.color("&7[&c*&7] " + lang.getString("CharacterCommands.ViewError2")));
			e.printStackTrace();
			return false;
		}
	}

	public static void displayCardFromInteract(Player player, Player target) {
		try {
			File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users" + File.separator
					+ target.getUniqueId() + ".yml");
			YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
			String msg = lang.getString("Cards.Card");
			if (msg.contains("@PLAYER"))
				msg = msg.replaceAll("@PLAYER", target.getName());
			int titleLen = msg.length() + 22;
			player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
			player.sendMessage(Helper.color("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
			if (!yamlConfiguration.getString("Name").isEmpty()) {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Name") + " &8- &b" + yamlConfiguration.get("Name")));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Name") + " &8- &b" + lang.getString("EmptyField")));
			}
			if (!yamlConfiguration.getString("Gender").isEmpty()) {
				player.sendMessage(Helper.color("&3" + lang.getString("Cards.Gender") + " &8- &b"
						+ Languages.translateGender(yamlConfiguration, lang)));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Gender") + " &8- &b" + lang.getString("EmptyField")));
			}
			if (!yamlConfiguration.getString("Age").isEmpty()) {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
			} else {
				player.sendMessage(
						Helper.color("&3" + lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
			}
			displayHooks(player, target);
			String msg2 = lang.getString("Cards.Desc");
			if (msg2.contains("@PLAYER"))
				msg2 = msg2.replaceAll("@PLAYER", target.getName());
			player.sendMessage(Helper.color("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
			if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
				player.sendMessage(Helper.color("&b" + yamlConfiguration.get("Physical Description")));
			} else {
				player.sendMessage(Helper.color("&b" + lang.getString("EmptyField")));
			}
			String linebreak = "&8&l&m";
			for (int i = 0; i < titleLen - 2; i++)
				linebreak = String.valueOf(linebreak) + "-";
			player.sendMessage(Helper.color(linebreak));
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing, they do not have a character card registered
		}
	}

	private static void displayHooks(Player player) {
		if (CharacterCards.usingMythicalRaces && Config.integrateMythicalRaces)
			MythicalRacesHook.displayMythicalRacesInfo(player);
		if (CharacterCards.usingTowny && Config.integrateTowny)
			TownyHook.displayTownyInfo(player);
		if (CharacterCards.usingKonquest && Config.integrateKonquest)
			KonquestHook.displayKonquestInfo(player);
		if (CharacterCards.usingMagic && Config.integrateMagic)
			MagicHook.getMagicRank(player);
		if (CharacterCards.usingPAPI && Config.integratePAPI)
			PAPIHook.displayCustomFields(player);
	}
	
	private static void displayHooks(Player player, OfflinePlayer target) {
		if (CharacterCards.usingMythicalRaces && Config.integrateMythicalRaces)
			MythicalRacesHook.displayMythicalRacesInfo(player, target);
		if (CharacterCards.usingTowny && Config.integrateTowny)
			TownyHook.displayTownyInfo(player, target);
		if (CharacterCards.usingKonquest && Config.integrateKonquest)
			KonquestHook.displayKonquestInfo(player, target);
		if (CharacterCards.usingMagic && Config.integrateMagic)
			MagicHook.getMagicRank(player, target);
		if (CharacterCards.usingPAPI && Config.integratePAPI)
			PAPIHook.displayCustomFields(player, target);
	}
}

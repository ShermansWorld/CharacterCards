package me.ShermansWorld.CharacterCards.listeners;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.ShermansWorld.CharacterCards.Main;
import me.ShermansWorld.CharacterCards.config.ConfigVals;
import me.ShermansWorld.CharacterCards.hooks.KonquestHook;
import me.ShermansWorld.CharacterCards.hooks.MythicalRacesHook;
import me.ShermansWorld.CharacterCards.hooks.TownyHook;
import me.ShermansWorld.CharacterCards.lang.Languages;

public class PlayerInteractEntity implements Listener {

	YamlConfiguration lang = Languages.getLang();

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player player = e.getPlayer();
			Player target = (Player) e.getRightClicked();

			if ((player.getInventory().getItemInMainHand().getType() == Material.PAPER
					|| player.getInventory().getItemInOffHand().getType() == Material.PAPER)
					&& e.getHand().equals(EquipmentSlot.HAND)) {
				if (!ConfigVals.paperRightClick) {
					return;
				}
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + target.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				String msg = lang.getString("Cards.Card");
				if (msg.contains("@PLAYER")) {
					msg = msg.replaceAll("@PLAYER", target.getName());
				}
				player.sendMessage(mess("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
				player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
				if (!yamlConfiguration.getString("Name").isEmpty()) {
					player.sendMessage(mess("&3" + lang.getString("Cards.Name") + " &8- &b"
							+ yamlConfiguration.get("Name")));
				} else {
					player.sendMessage(mess("&3" + lang.getString("Cards.Name") + " &8- &b"
							+ lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Gender").isEmpty()) {
					player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
							+ Languages.translateGender(yamlConfiguration, lang)));
				} else {
					player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
							+ lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Age").isEmpty()) {
					player.sendMessage(mess( "&3" +
							lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
				} else {
					player.sendMessage(mess( "&3" +
							lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
				}
				if (Main.usingMythicalRaces && ConfigVals.integrateMythicalRaces) {
					MythicalRacesHook.displayMythicalRacesInfo(player, target);
				}
				if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation if they are in
																	// one
					TownyHook.displayTownyInfo(player, target);
				}
				if (Main.usingKonquest && ConfigVals.integrateKonquest) {
					KonquestHook.displayKonquestInfo(player, target);
				}
				String msg2 = lang.getString("Cards.Desc");
				if (msg2.contains("@PLAYER")) {
					msg2 = msg2.replaceAll("@PLAYER", target.getName());
				}
				player.sendMessage(mess("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
				if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
					player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
				} else {
					player.sendMessage(mess("&b" + lang.getString("EmptyField")));
				}
				player.sendMessage(mess("&8&l&m-----------------------------------"));
				return;
			}
			if (e.getRightClicked() instanceof Player && player.isSneaking() && e.getHand().equals(EquipmentSlot.HAND)
					&& player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
				if (!ConfigVals.shiftRightClick) {
					return;
				}
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + target.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				String msg = lang.getString("Cards.Card");
				if (msg.contains("@PLAYER")) {
					msg = msg.replaceAll("@PLAYER", target.getName());
				}
				player.sendMessage(mess("&8&l&m-----------&7[&3" + msg + "&7]&8&l&m-----------"));
				player.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
				if (!yamlConfiguration.getString("Name").isEmpty()) {
					player.sendMessage(mess("&3" + lang.getString("Cards.Name") + " &8- &b"
							+ yamlConfiguration.get("Name")));
				} else {
					player.sendMessage(mess("&3" + lang.getString("Cards.Name") + " &8- &b"
							+ lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Gender").isEmpty()) {
					player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
							+ Languages.translateGender(yamlConfiguration, lang)));
				} else {
					player.sendMessage(mess("&3" + lang.getString("Cards.Gender") + " &8- &b"
							+ lang.getString("EmptyField")));
				}
				if (!yamlConfiguration.getString("Age").isEmpty()) {
					player.sendMessage(mess( "&3" +
							lang.getString("Cards.Age") + " &8- &b" + yamlConfiguration.get("Age")));
				} else {
					player.sendMessage(mess( "&3" +
							lang.getString("Cards.Age") + " &8- &b" + lang.getString("EmptyField")));
				}
				if (Main.usingMythicalRaces && ConfigVals.integrateMythicalRaces) {
					MythicalRacesHook.displayMythicalRacesInfo(player, target);
				}
				if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation if they are in
																	// one
					TownyHook.displayTownyInfo(player, target);
				}
				if (Main.usingKonquest && ConfigVals.integrateKonquest) {
					KonquestHook.displayKonquestInfo(player, target);
				}
				String msg2 = lang.getString("Cards.Desc");
				if (msg2.contains("@PLAYER")) {
					msg2 = msg2.replaceAll("@PLAYER", target.getName());
				}
				player.sendMessage(mess("&8&l&m-----------&7[&3" + msg2 + "&7]&8&l&m-----------"));
				if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
					player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
				} else {
					player.sendMessage(mess("&b" + lang.getString("EmptyField")));
				}
				player.sendMessage(mess("&8&l&m-----------------------------------"));
				return;
			}
		}
	}

	private String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

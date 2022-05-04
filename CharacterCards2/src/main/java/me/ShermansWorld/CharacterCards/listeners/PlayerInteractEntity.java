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

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;

import me.ShermansWorld.CharacterCards.Main;
import me.ShermansWorld.CharacterCards.config.ConfigVals;

public class PlayerInteractEntity implements Listener {
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player player = e.getPlayer();
			Player target = (Player) e.getRightClicked();

			if ((player.getInventory().getItemInMainHand().getType() == Material.PAPER || player.getInventory().getItemInOffHand().getType() == Material.PAPER) && e.getHand().equals(EquipmentSlot.HAND)) {
				if (!ConfigVals.paperRightClick) {
					return;
				}
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + target.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				player.sendMessage(mess(
						"&8&l&m------------&7[&3" + target.getPlayer().getName() + "'s Card&7]&8&l&m------------"));
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
				if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation if they are in one
					try {
						player.sendMessage(mess("&3Town&8 - &b" + TownyAPI.getInstance().getResident(player).getTown().getName()));
					} catch (NotRegisteredException e2) {
					}
					try {
						player.sendMessage(mess("&3Nation&8 - &b" + TownyAPI.getInstance().getResident(player).getNation().getName()));
					} catch (TownyException e2) {
					}
				}
				player.sendMessage(
						mess("&8&l&m----------&7[&3" + target.getPlayer().getName() + "'s Desc&7]&8&l&m----------"));
				if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
					player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
				} else {
					player.sendMessage(mess("&bEmpty"));
				}
				player.sendMessage(mess("&8&l&m-----------------------------------"));
				return;
			}
			if (e.getRightClicked() instanceof Player && player.isSneaking()
					&& e.getHand().equals(EquipmentSlot.HAND) && player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
				if (!ConfigVals.shiftRightClick) {
					return;
				}
				File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users"
						+ File.separator + target.getPlayer().getUniqueId() + ".yml");
				YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
				player.sendMessage(mess(
						"&8&l&m------------&7[&3" + target.getPlayer().getName() + "'s Card&7]&8&l&m------------"));
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
				if (Main.usingTowny && ConfigVals.integrateTowny) { // display the player's town/nation if they are in one
					try {
						player.sendMessage(mess("&3Town&8 - &b" + TownyAPI.getInstance().getResident(player).getTown().getName()));
					} catch (NotRegisteredException e2) {
					}
					try {
						player.sendMessage(mess("&3Nation&8 - &b" + TownyAPI.getInstance().getResident(player).getNation().getName()));
					} catch (TownyException e2) {
					}
				}
				player.sendMessage(
						mess("&8&l&m----------&7[&3" + target.getPlayer().getName() + "'s Desc&7]&8&l&m----------"));
				if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
					player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
				} else {
					player.sendMessage(mess("&bEmpty"));
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

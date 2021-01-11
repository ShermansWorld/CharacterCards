package me.r0m3x.iccards.listeners;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntity implements Listener {
  @EventHandler
  public void onEntityClick(PlayerInteractEntityEvent e) {
    if (e.getRightClicked() instanceof Player) {
      Player player = e.getPlayer();
      Player target = (Player)e.getRightClicked();
      if (player.getItemInHand().getType() == Material.PAPER)
        return; 
      if (e.getRightClicked() instanceof Player && player.isSneaking() && e.getHand().equals(EquipmentSlot.HAND)) {
        File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + target.getPlayer().getUniqueId() + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
        player.sendMessage(mess("&8&l&m------------&7[&3" + target.getPlayer().getName() + "'s Card&7]&8&l&m------------"));
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
        if (!yamlConfiguration.getString("Profession").isEmpty()) {
          player.sendMessage(mess("&3Profession &8- &b" + yamlConfiguration.get("Profession")));
        } else {
          player.sendMessage(mess("&3Profession &8- &bEmpty"));
        } 
        player.sendMessage(mess("&8&l&m----------&7[&3" + target.getPlayer().getName() + "'s Desc&7]&8&l&m----------"));
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

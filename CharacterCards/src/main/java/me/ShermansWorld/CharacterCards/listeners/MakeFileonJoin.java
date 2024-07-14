package me.ShermansWorld.CharacterCards.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class MakeFileonJoin implements Listener {
  @EventHandler
  public void onJoin(PlayerLoginEvent e) {
    File f = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users" + File.separator + e.getPlayer().getUniqueId() + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
    
    if (!f.exists())
      try {
        f.createNewFile();
        yamlConfiguration.set("Name", "");
        yamlConfiguration.set("Gender", "");
        yamlConfiguration.set("Age", "");
        yamlConfiguration.set("Physical Description", "");
        yamlConfiguration.save(f);
      } catch (IOException e1) {
        e1.printStackTrace();
      }  
  }
}
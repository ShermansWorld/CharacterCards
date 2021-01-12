package me.r0m3x.iccards.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class MakeFileonJoin implements Listener {
  @EventHandler
  public void onJoin(PlayerLoginEvent e) {
    File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + e.getPlayer().getUniqueId() + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
    if (!f.exists())
      try {
        f.createNewFile();
        yamlConfiguration.set("IGN", e.getPlayer().getName());
        yamlConfiguration.set("Name", "");
        yamlConfiguration.set("Gender", "");
        yamlConfiguration.set("Age", "");
        yamlConfiguration.set("Physical Description", "");
        yamlConfiguration.set("Profession", "");
        yamlConfiguration.save(f);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + e.getPlayer().getName() + " group remove soldier");
      } catch (IOException e1) {
        e1.printStackTrace();
      }  
  }
}
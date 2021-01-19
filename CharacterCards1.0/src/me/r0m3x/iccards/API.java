package me.r0m3x.iccards;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class API {
  public static String getProfession(Player p) {
    File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + p.getUniqueId() + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
    return yamlConfiguration.getString("Profession");
  }
  
  public static String getProfessionChanges(Player p) {
	    File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + p.getUniqueId() + ".yml");
	    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
	    return yamlConfiguration.getString("Profession-Changes");
  }
  
  public static boolean hasDing(Player p) {
    File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + p.getUniqueId() + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
    return yamlConfiguration.getBoolean("PlayExDing");
  }
}
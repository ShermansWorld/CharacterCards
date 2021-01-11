package me.r0m3x.iccards.cmds;

import java.io.File;
import me.r0m3x.iccards.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Card implements CommandExecutor {
  public Card(Main plugin) {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("card")) {
      if (args == null || args.length == 0) {
        File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + p.getPlayer().getUniqueId() + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
        return true;
      } 
      if (args.length > 2) {
        p.sendMessage(mess("&bToo many args&8/&3Card &8<&3player&8>"));
        return true;
      } 
      if (args.length == 1) {
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target != null) {
          File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + target.getPlayer().getUniqueId() + ".yml");
          YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
          p.sendMessage(mess("&8&l&m------------&7[&3" + target.getPlayer().getName() + "'s Card&7]&8&l&m------------"));
          p.sendMessage(mess("&3IGN &8- &b" + yamlConfiguration.get("IGN")));
          p.sendMessage(mess("&3Name &8- &b" + yamlConfiguration.get("Name")));
          p.sendMessage(mess("&3Gender &8- &b" + yamlConfiguration.get("Gender")));
          p.sendMessage(mess("&3Age &8- &b" + yamlConfiguration.get("Age")));
          p.sendMessage(mess("&3Religion &8- &b" + yamlConfiguration.get("Religion")));
          p.sendMessage(mess("&8&l&m----------&7[&3" + target.getPlayer().getName() + "'s Desc&7]&8&l&m----------"));
          p.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
          p.sendMessage(mess("&8&l&m-----------------------------------"));
          p.sendMessage(mess("&bTo view another player's card: &8/&3card &8<&3player&8>"));
          p.sendMessage(mess("&8&l&m-----------------------------------"));
          return true;
        } 
        p.sendMessage(mess("&7[&3ICCards&7] &3" + args[0] + " &7is not online/has never joined before&3."));
      } 
    } 
    return false;
  }
  
  private String mess(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
}

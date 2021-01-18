package me.r0m3x.iccards.cmds;

import java.io.File;
import java.io.IOException;
import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CharacterCMD implements CommandExecutor {
  String version = Bukkit.getPluginManager().getPlugin("CharacterCards").getDescription().getVersion();
  
  public CharacterCMD(Main plugin) {}
  
  public boolean checkcolorperms(Player player, String argument) {
    String str = argument;
    while (str.contains("&")) {
      int andIndex = str.indexOf('&');
      if (andIndex == str.length() - 1)
        return false; 
      String colour = str.substring(andIndex + 1, andIndex + 2);
      if (!player.hasPermission("iccards.color." + colour))
        return false; 
      str = str.substring(andIndex + 1);
    } 
    return true;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("character") || cmd.getName().equalsIgnoreCase("char")) {
      if (args == null || args.length == 0) {
        player.sendMessage(mess("&8&l&m---------------&7[&3LLCards &7v&b" + this.version + "&7]&8&l&m---------------\n"));
        player.sendMessage(mess("&7/&3Char Name &7<&bFirst&7> &7<&bLast&7> &8- &3Change your name."));
        player.sendMessage(mess("&7/&3Char Gender &bMale&7/&bFemale &8- &3Change your gender."));
        player.sendMessage(mess("&7/&3Char Age &b14&7-&b120 &8- &3Change your age."));
        player.sendMessage(mess("&7/&3Char Desc &7<&bDescription&7> &8- &3Change your physical description."));
        player.sendMessage(mess("&7/&3Char Profession &7<&bProfession&7> &8- &3Pick a Profession."));
        player.sendMessage(mess("&7/&3Char View &8- &3View your card, or another player's!"));
        player.sendMessage(mess("&8&l&m----------------------------------------"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("name") && args.length == 2) {
        player.sendMessage(mess("&7[&c*&7] &7You must enter a last name!"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("name") && args.length == 1) {
        player.sendMessage(mess("&7[&c*&7] &7You must enter a first and last name!"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("name") && args.length > 3) {
        player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Name &7<&cFirst&7> &7<&cLast&7>"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("name") && args.length == 3) {
        File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
        String first_name = args[1].toString();
        String last_name = args[2].toString();
        if (last_name.equalsIgnoreCase("null"))
          last_name = ""; 
        if (checkcolorperms(player, first_name) && checkcolorperms(player, last_name)) {
          yamlConfiguration.set("Name", first_name + " " + last_name);
          player.sendMessage(mess("&7[&a*&7] &cCharacter Name Set As: &e" + yamlConfiguration.get("Name")));
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
        player.sendMessage(mess("&7[&c*&7] &7You must enter a gender&c! &7(&cMale &7or &cFemale&7)"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("gender") && args.length > 2) {
        player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Gender &7(&cMale &7or &cFemale&7)"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("gender") && args.length == 2) {
        String gender = args[1].toString();
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("boy") || gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("man")) {
          File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
          YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
          yamlConfiguration.set("Gender", "Male");
          player.sendMessage(mess("&7[&a*&7] &cGender Set As: &eMale"));
          try {
            yamlConfiguration.save(f);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          return true;
        } 
        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("girl") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("woman")) {
          File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
          YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
          yamlConfiguration.set("Gender", "Female");
          player.sendMessage(mess("&7[&a*&7] &cGender Set As: &eFemale"));
          try {
            yamlConfiguration.save(f);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          return true;
        } 
        player.sendMessage(mess("&7[&c*&7] &cYou must be Male&7/&cFemale"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("edit") && args.length == 1 && player.getName().equalsIgnoreCase("Explode")) {
        player.sendMessage(mess("&7[&c*&7] &7You must enter a &eprofession&7!"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("edit") && args.length == 2 && player.getName().equalsIgnoreCase("Explode")) {
        File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
        yamlConfiguration.set("Profession", args[1]);
        try {
          yamlConfiguration.save(f);
        } catch (IOException e) {
          e.printStackTrace();
        } 
        player.sendMessage(mess("&7[&a*&7] &aProfession changed to: &7" + args[1]));
        return true;
      } 
      if (args[0].equalsIgnoreCase("edit") && args.length == 3 && player.getName().equalsIgnoreCase("Explode")) {
        Player target = Bukkit.getPlayerExact(args[1]);
        File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + target.getPlayer().getUniqueId() + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
        yamlConfiguration.set("Profession", args[2]);
        try {
          yamlConfiguration.save(f);
        } catch (IOException e) {
          e.printStackTrace();
        } 
        player.sendMessage(mess("&7[&a*&7] &aYou changed &7" + target.getName() + "'s profession to: &7" + args[2]));
        return true;
      } 
      if (args[0].equalsIgnoreCase("delete") && args.length == 1 && player.hasPermission("iccards.admin.delete")) {
        player.sendMessage(mess("&7[&c*&7] &7You must enter a &eplayer's name&7!"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("delete") && args.length == 2 && player.hasPermission("iccards.admin.delete")) {
        Player target = Bukkit.getPlayerExact(args[1]);
        if (target != null) {
          File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + target.getPlayer().getUniqueId() + ".yml");
          if (f.exists()) {
            f.delete();
            player.sendMessage(mess("&7[&a*&7] &aDeleted &7" + target.getName() + "'s &aconfiguration file!"));
          } else {
            player.sendMessage(mess("&7[&c*&7] &7" + target.getName() + "'s &cconfiguration file doesn't exist!"));
          } 
          return true;
        } 
        player.sendMessage(mess("&7[&c*&7] &7" + args[1] + " &cisn't online!"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("profession") && args.length == 1) {
        player.sendMessage(mess("&7[&c*&7] &7You must enter a &eProfession&7! &7(&eBaker&7,&eFarmer&7,&eFisherman&7,&eMiner&7,&eSoldier&7,&eThief&7)"));
        return true;
      } 
      if (args[0].equalsIgnoreCase("profession") && args.length == 2) {
        if (API.getProfession(player).isEmpty()) {
          if (args[1].equalsIgnoreCase("baker")) {
            player.sendMessage(mess("&7[&a*&7] &7You chose the &eBaker &7profession!"));
            File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
            yamlConfiguration.set("Profession", "Baker");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
            try {
              yamlConfiguration.save(f);
            } catch (IOException e) {
              e.printStackTrace();
            } 
            return true;
          } 
          if (args[1].equalsIgnoreCase("farmer")) {
            player.sendMessage(mess("&7[&a*&7] &7You chose the &eFarmer &7profession!"));
            File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
            yamlConfiguration.set("Profession", "Farmer");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
            try {
              yamlConfiguration.save(f);
            } catch (IOException e) {
              e.printStackTrace();
            } 
            return true;
          }
          if (args[1].equalsIgnoreCase("fisherman")) {
              player.sendMessage(mess("&7[&a*&7] &7You chose the &eFisherman &7profession!"));
              File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
              yamlConfiguration.set("Profession", "Fisherman");
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              try {
                yamlConfiguration.save(f);
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return true;
          }
          if (args[1].equalsIgnoreCase("miner")) {
              player.sendMessage(mess("&7[&a*&7] &7You chose the &eMiner &7profession!"));
              File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
              yamlConfiguration.set("Profession", "Miner");
              PotionEffect haste1 = new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0);
              player.addPotionEffect(haste1);
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              try {
                yamlConfiguration.save(f);
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return true;
          }
          if (args[1].equalsIgnoreCase("soldier")) {
              player.sendMessage(mess("&7[&a*&7] &7You chose the &eSoldier &7profession!"));
              File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
              yamlConfiguration.set("Profession", "Soldier");
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add soldier");
              try {
                yamlConfiguration.save(f);
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return true;
          }
          if (args[1].equalsIgnoreCase("thief")) {
              player.sendMessage(mess("&7[&a*&7] &7You chose the &eThief &7profession!"));
              File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
              yamlConfiguration.set("Profession", "Thief");
              try {
                yamlConfiguration.save(f);
              } catch (IOException e) {
                e.printStackTrace();
              } 
              return true;
          }
        } else {
          player.sendMessage(mess("&7[&c*&7] &7You already chose a profession&e!&7 Your current profession: &e" + API.getProfession(player)));
          return true;
        } 
      } else {
        if (args[0].equalsIgnoreCase("profession") && args.length > 2) {
          player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Profession"));
          return true;
        } 
        if (args[0].equalsIgnoreCase("age") && args.length == 1) {
          player.sendMessage(mess("&7[&c*&7] &cYou must enter a age! &eUsage&7: &7/&cChar Age &c14&7-&c120"));
          return true;
        } 
        if (args[0].equalsIgnoreCase("age") && args.length > 2) {
          player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar Age &c14&7-&c120"));
          return true;
        } 
        if (args[0].equalsIgnoreCase("age") && args.length == 2) {
          File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
          YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
          int age = 0;
          age = Integer.parseInt(args[1]);
          if (age <= 120 && age >= 14) {
            yamlConfiguration.set("Age", Integer.valueOf(age));
            player.sendMessage(mess("&7[&a*&7] &cYou Set Your Age As &e" + age));
            try {
              yamlConfiguration.save(f);
              return true;
            } catch (IOException e) {
              e.printStackTrace();
            } 
          } else {
            player.sendMessage(mess("&7[&c*&7] &7Age must be &c14&7-&c120"));
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
            File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
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
            player.sendMessage(mess("&7[&c*&7] &cYou have set your physical description as: &7" + desc));
            return true;
          } 
          if (args[0].equalsIgnoreCase("view") && args.length > 2) {
            player.sendMessage(mess("&7[&c*&7] &cToo many args! &eUsage&7: &7/&cChar View &8<&cPlayer&8>"));
            return true;
          } 
          if (args[0].equalsIgnoreCase("view") && args.length == 1) {
            File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + player.getPlayer().getUniqueId() + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
            player.sendMessage(mess("&8&l&m-----------&7[&3" + player.getName() + "'s Card&7]&8&l&m-----------"));
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
            player.sendMessage(mess("&8&l&m-----------&7[&3" + player.getName() + "'s Desc&7]&8&l&m-----------"));
            if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
              player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
            } else {
              player.sendMessage(mess("&bEmpty"));
            } 
            player.sendMessage(mess("&8&l&m-----------------------------------"));
            return true;
          } 
          if (args[0].equalsIgnoreCase("view") && args.length == 2) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target != null) {
              File f = new File("plugins" + File.separator + "ICCards" + File.separator + "users" + File.separator + target.getPlayer().getUniqueId() + ".yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
              player.sendMessage(mess("&8&l&m-----------&7[&3" + target.getPlayer().getName() + "'s Card&7]&8&l&m-----------"));
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
              player.sendMessage(mess("&8&l&m-----------&7[&3" + target.getPlayer().getName() + "'s Desc&7]&8&l&m-----------"));
              if (!yamlConfiguration.getString("Physical Description").isEmpty()) {
                player.sendMessage(mess("&b" + yamlConfiguration.get("Physical Description")));
              } else {
                player.sendMessage(mess("&bEmpty"));
              } 
              player.sendMessage(mess("&8&l&m-----------------------------------"));
              return true;
            } 
            player.sendMessage(mess("&7[&3ICCards&7] &3" + args[1] + " &7is not online/has never joined before&3."));
            return true;
          } 
          player.sendMessage(mess("&7[&3ICCards&7] &3Invalid Command"));
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

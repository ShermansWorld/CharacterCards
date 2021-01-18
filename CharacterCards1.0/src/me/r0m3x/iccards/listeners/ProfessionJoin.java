package me.r0m3x.iccards.listeners;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;
import me.r0m3x.iccards.cmds.CharacterCMD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ProfessionJoin implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void professionJoin(final PlayerJoinEvent e) {
    Player player = e.getPlayer();
    player.setInvulnerable(false);
    PotionEffect haste1 = new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0);
    Bukkit.getScheduler().runTaskLater((Plugin)Main.getInstance(), new Runnable() {
          public void run() {
            Player p = e.getPlayer();
            if (API.getProfession(p).equalsIgnoreCase("Thief")) {
              p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lThief&7. \n&8[&a*&8] &eYou can now &clockpick things and walk faster while sneaking"));
              p.removePotionEffect(PotionEffectType.FAST_DIGGING);
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove fisherman");
              return;
            } 
            if (API.getProfession(p).equalsIgnoreCase("Fisherman")) { 
              p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lFisherman&7."));
              p.removePotionEffect(PotionEffectType.FAST_DIGGING);
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add fisherman");
              return;
            }
            if (API.getProfession(p).equalsIgnoreCase("Farmer")) { 
                p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lFarmer&7. \n&8[&a*&8] &eYou can now &cautoharvest with a hoe"));
                p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove fisherman");
                return;
              }
            if (API.getProfession(p).equalsIgnoreCase("Miner")) {
            	p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lMiner&7. \n&8[&a*&8] &eYou now have &cpermanant haste I"));
            	p.addPotionEffect(haste1);
              	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove fisherman");
              	return;
            }
            if (API.getProfession(p).equalsIgnoreCase("Baker")) {
            	p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lBaker&7. \n&8[&a*&8] &eYou can &ccraft &eBaker's Dough &cand &eBaker's Bread"));
            	p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
            	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove fisherman");
                return;
            }
            if (API.getProfession(p).equalsIgnoreCase("Soldier")) {
                p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lSoldier&7. \n&8[&a*&8] &eYou have access to &c/kit soldier"));
                p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add soldier");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove fisherman");
                return;
            }
          }
        },  20L);
  }
}

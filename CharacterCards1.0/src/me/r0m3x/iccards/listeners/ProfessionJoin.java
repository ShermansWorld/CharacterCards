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
    Bukkit.getScheduler().runTaskLater((Plugin)Main.getInstance(), new Runnable() {
          public void run() {
            Player p = e.getPlayer();
            if (API.getProfession(p).equalsIgnoreCase("Thief")) {
              p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lThief&7. \n&eYou now have a &a&lHIGHER &echance to successfully lockpick \ndoors/trapdoors/gates&7,&e and now have &c&lLOWER &ehearts&7. \n&e(&c8.5 &ehearts instead of &c10&e)"));
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              return;
            } 
            if (API.getProfession(p).equalsIgnoreCase("Fisherman")) { 
              p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lFisherman&7."));
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              return;
            } 
            if (API.getProfession(p).equalsIgnoreCase("Miner")) {
              p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lMiner&7. &eYou now have &cpermanant haste I"));
              PotionEffect haste1 = new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0);
              p.addPotionEffect(haste1);
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
              return;
            }
            if (API.getProfession(p).equalsIgnoreCase("Baker")) {
                p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lBaker&7. &eYou can now &ccraft dough as a more efficient way of making bread"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group remove soldier");
                return;
            }
            if (API.getProfession(p).equalsIgnoreCase("Soldier")) {
                p.sendMessage(CharacterCMD.mess("&8[&a*&8] &eYour current profession is &6&lSoldier&7. &eYou now have access to &c/kit soldier"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add soldier");
                return;
            }
          }
        },  20L);
  }
}

package me.r0m3x.iccards.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumableListener implements Listener {
    public void EatEvent(PlayerItemConsumeEvent e) {
        if (e.getItem() == CraftingRecipies.bakersBread) {
            Player p = e.getPlayer();
            //p.setFoodLevel(p.getFoodLevel() + 7);
        }

    }
}
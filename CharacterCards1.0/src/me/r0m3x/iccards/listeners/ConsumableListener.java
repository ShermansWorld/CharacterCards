package me.r0m3x.iccards.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumableListener implements Listener {
	@EventHandler
	public void EatEvent(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if (e.getItem().toString().contentEquals(CraftingRecipies.bakersBread.toString())) {
			p.setFoodLevel(p.getFoodLevel() + 2);
		}

	}
}
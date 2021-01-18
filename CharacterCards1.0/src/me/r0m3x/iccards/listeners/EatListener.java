package me.r0m3x.iccards.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;

import me.r0m3x.iccards.API;

public class EatListener implements Listener {
	@EventHandler
	public void EatEvent(PlayerItemConsumeEvent e) {
		if (e.getItem().getType() == Material.BREAD) {
			ItemMeta meta = e.getItem().getItemMeta();
			if (meta.getDisplayName().contentEquals(CraftingRecipies.getBread().getItemMeta().getDisplayName())
					&& meta.hasLore()) {
			Player p = e.getPlayer();
			p.setFoodLevel(p.getFoodLevel() + 2);
			}

		} else if (e.getItem().getType() == Material.COOKED_COD || e.getItem().getType() == Material.COOKED_SALMON) {
			Player p = e.getPlayer();
			if (API.getProfession(p).equalsIgnoreCase("Fisherman")) {
				p.setFoodLevel(p.getFoodLevel() + 2);
			}
		}
	}
	
	
}

package me.r0m3x.iccards.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;

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

		}
	}
}

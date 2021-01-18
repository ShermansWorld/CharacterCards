package me.r0m3x.iccards.listeners;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.cmds.CharacterCMD;

public class FishingListener implements Listener {

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@EventHandler
	public void FishingEvent(PlayerFishEvent e) {
		Player p = e.getPlayer();
		if (API.getProfession(p).equalsIgnoreCase("Fisherman")) {
			if (e.getCaught() instanceof Item) {
				Item item = (Item) e.getCaught();
				if (item.getItemStack().getType().equals(Material.COD)
						|| item.getItemStack().getType().equals(Material.SALMON)) {
					ItemStack is = item.getItemStack();
					ItemMeta meta = is.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					Random rand = new Random();
					int length = rand.nextInt(36) + 4; // random between 4-40
					double weight = round(
							rand.nextDouble() * ((Double.valueOf(length) / 1.8) - (Double.valueOf(length) / 3.0))
									+ Double.valueOf(length) / 1.8,
							2);
					double worth = round((weight * Double.valueOf(length) / 100), 1);
					lore.add("Length: " + String.valueOf(length) + "\"");
					lore.add("Weight: " + String.valueOf(weight) + " lbs");
					lore.add("Worth: $" + String.valueOf(worth));
					lore.add(CharacterCMD.mess("Type &e&o/fishing sellall &5&oto sell your fish"));
					meta.setLore(lore);
					is.setItemMeta(meta);
					item.setItemStack(is);
				}
			}
		}
	}
}

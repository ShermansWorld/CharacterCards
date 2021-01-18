package me.r0m3x.iccards.cmds;

import me.r0m3x.iccards.Main;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Fishing implements CommandExecutor {
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public Fishing(Main plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("fishing")) {
			if (args == null || args.length == 0) {
				p.sendMessage(mess("Type &e/fishing sellall &fto sell all your fish"));
			} else if (args.length == 1 && args[0].equalsIgnoreCase("sellall")) {
				double total = 0.0;
				int count = 0;
				for (ItemStack item : p.getInventory().getContents()) {
					if (item != null) {
						if ((item.getType() == Material.COD || item.getType() == Material.SALMON || item.getType() == Material.TROPICAL_FISH || item.getType() == Material.PUFFERFISH) && item.getItemMeta().hasLore()) {
							ItemMeta meta = item.getItemMeta();
							String worthLore = meta.getLore().get(2);
							int itemAmount = item.getAmount();
							item.setAmount(itemAmount - 1);
							double worth = Double.parseDouble(worthLore.substring(worthLore.lastIndexOf('$') + 1));
							total += worth;
							count++;
						}
					}
				}
				Main.economy.depositPlayer(p, round(total, 1));
				p.sendMessage(mess("You sold &b" + String.valueOf(count) + " &ffish for a total of &2$" + String.valueOf(round(total, 1))));
				
			} else {
				p.sendMessage(mess("Type &e/fishing sellall &fto sell all your fish"));
			}
		}
		return false;
	}

	private String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}

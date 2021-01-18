package me.r0m3x.iccards;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.WorldCoord;

import me.r0m3x.iccards.listeners.LockpickListener;
import net.md_5.bungee.api.ChatColor;

public class TownyCompatibility {
	
	public static void TownyChest(Player p, Block b, World w) {
		try {
			TownBlock townBlock = WorldCoord.parseWorldCoord((Entity) p).getTownBlock();
			Resident resident = TownyUniverse.getDataSource().getResident(p.getName());
			if (!resident.hasTown()) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&c*&7] &cYou cannot lockpick a town's chest!"));
				return;
			}
			Town town = resident.getTown();
			if (town.hasTownBlock(townBlock)) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&c*&7] &cYou cannot lockpick your town's chest!"));
				return;
			}
		} catch (NotRegisteredException ev) {
			if (!LockpickListener.lockpickMap.containsKey(p.getName())) {
				LockpickListener.lockpickMap.put(p.getName(), 0);
        	} else {
        		if (LockpickListener.lockpickMap.get(p.getName()) == 1) {
        			p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&c*&7] &cYou are already trying to lockpick something!"));
        			return;
        		}
        	}
			LockpickListener.lockpickMap.put(p.getName(), 1);
        	p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8[&a*&8] &7Here goes nothing..."));
			if (LockpickListener.getRandom(5) == 1) {
				LockpickListener.openChest(b, w, p);
			} else {
				LockpickListener.pickFailed(p);
			}
		}
	}
}

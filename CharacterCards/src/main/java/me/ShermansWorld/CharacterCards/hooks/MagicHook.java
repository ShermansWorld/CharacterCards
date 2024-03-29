package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.elmakers.mine.bukkit.api.magic.Mage;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;

import me.ShermansWorld.CharacterCards.Helper;

public class MagicHook {
	
	public static MagicAPI magicAPI;
	
	public static void initMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
          if (magicPlugin == null || !(magicPlugin instanceof MagicAPI)) {
        	  Bukkit.getLogger().warning("[CharacterCards] Failed to load MagicAPI. Is the plugin installed?");
              magicAPI = null;
          }
        magicAPI = (MagicAPI)magicPlugin;
    }
	
	public static void getMagicRank(Player player) {
		if (magicAPI == null) {
			initMagicAPI();
		}
		Mage mage = magicAPI.getController().getMage(player);
		if (mage.getActiveClass().getPath() != null) {
			String rank = mage.getActiveClass().getPath().getName();
			rank = rank.substring(2); // returns "&7 + Rank" so "&7" needs to be removed
			player.sendMessage(Helper.color("&3Mage Level&8 - &b" + rank));
		}
	}
	
	public static void getMagicRank(Player player, OfflinePlayer target) {
		try {
			Player targetPlayer;
			if (magicAPI == null) {
				initMagicAPI();
			}
			if (target.isOnline()) {
				targetPlayer = (Player) target;
				Mage mage = magicAPI.getController().getMage(targetPlayer);
				if (mage.getActiveClass().getPath() != null) {
					String rank = mage.getActiveClass().getPath().getName();
					rank = rank.substring(2); // returns "&7 + Rank" so "&7" needs to be removed
					player.sendMessage(Helper.color("&3Mage Level&8 - &b" + rank));
				}
			}
		} catch (NullPointerException e2) {
		}
	}
}

package me.ShermansWorld.CharacterCards.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.elmakers.mine.bukkit.api.magic.Mage;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;

public class MagicHook {
	
	private static MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
          if (magicPlugin == null || !(magicPlugin instanceof MagicAPI)) {
        	  Bukkit.getLogger().warning("[CharacterCards] Failed to load MagicAPI. Is the plugin installed?");
              return null;
          }
        return (MagicAPI)magicPlugin;
    }
	
	public static void displayMagicInfo(Player player) {
		MagicAPI magicAPI = getMagicAPI();
		Mage mage = magicAPI.getController().getMage(player);
		String rank = mage.getActiveWand().getPath().getName();
	}
	
	public static void displayMagicInfo(Player player, String playerName) {
	}
	
	public static void displayMagicInfo(Player player, Player target) {
	}
}

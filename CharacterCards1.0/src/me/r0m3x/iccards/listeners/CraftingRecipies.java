package me.r0m3x.iccards.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;
import me.r0m3x.iccards.cmds.CharacterCMD;
import net.md_5.bungee.api.ChatColor;

//import org.bukkit.inventory.FurnaceRecipe;
//import org.bukkit.inventory.ItemStack;

public class CraftingRecipies implements Listener {

	private ItemStack wheat = new ItemStack(Material.WHEAT, 1);
		
	private ItemStack getDough() {
		ItemStack bakersDough = new ItemStack(Material.WHEAT, 6);
		ItemMeta bakersDoughMeta = bakersDough.getItemMeta();
		bakersDoughMeta.setDisplayName(CharacterCMD.mess("&eBaker's Dough"));
		ArrayList<String> breadLore = new ArrayList<>();
		breadLore.add(CharacterCMD.mess("[Smelting yeilds 1 &e&oBaker's Bread&5&o]"));
		breadLore.add(CharacterCMD.mess("[Only &e&oBakers &5&ocan craft this]"));
		bakersDoughMeta.setLore(breadLore);
		bakersDough.setItemMeta(bakersDoughMeta);
		return bakersDough;
	}
	
	public void doughRecipe() {
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "1");
		ShapedRecipe bakersDoughRecipe = new ShapedRecipe(key, getDough());
		bakersDoughRecipe.shape("###", "#$#", "###");
		bakersDoughRecipe.setIngredient('$', Material.WATER_BUCKET);
		bakersDoughRecipe.setIngredient('#', Material.WHEAT);
		Main.getInstance().getServer().addRecipe(bakersDoughRecipe);

	}
	
	public static ItemStack getBread() {
		ItemStack bakersBread = new ItemStack(Material.BREAD, 1);
		ItemMeta bakersBreadMeta = bakersBread.getItemMeta();
		bakersBreadMeta.setDisplayName(CharacterCMD.mess("&eBaker's Bread"));
		ArrayList<String> breadLore = new ArrayList<>();
		breadLore.add(CharacterCMD.mess("[Made from &e&oBaker's Dough&5&o]"));
		breadLore.add(CharacterCMD.mess("[&2&o+2 Hunger &5&ocompared to regular bread]"));
		bakersBreadMeta.setLore(breadLore);
		bakersBread.setItemMeta(bakersBreadMeta);
		return bakersBread;
	}
	

	public void breadRecipe() {
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "2");
		FurnaceRecipe bakersBreadRecipe = new FurnaceRecipe(key, getBread(), Material.WHEAT, (float) 0.0, 30);
		Main.getInstance().getServer().addRecipe(bakersBreadRecipe);

	}

	public static ItemStack getLockPick() {
		ItemStack lockpick = new ItemStack(Material.IRON_HOE, 1);
		ItemMeta meta = lockpick.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lLockpick"));
		ArrayList<String> lore = new ArrayList<String>();
		// meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		lore.add(ChatColor.translateAlternateColorCodes('&', "A special tool to open locks, when used it"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "has a chance of opening a locked door or chest."));
		meta.setLore(lore);
		lockpick.setItemMeta(meta);
		return (lockpick);
	}

	public void lockpickRecipe() {
		ItemStack lockpick = getLockPick();
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "3");
		ShapedRecipe lockpickRecipe = new ShapedRecipe(key, lockpick);
		lockpickRecipe.shape("@@ ", " % ", "%  ");
		lockpickRecipe.setIngredient('@', Material.IRON_INGOT);
		lockpickRecipe.setIngredient('%', Material.STICK);
		Main.getInstance().getServer().addRecipe(lockpickRecipe);
	}
	
	public void myceliumRecipe() {
		ItemStack mycelium = new ItemStack(Material.MYCELIUM, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "4");
		ShapelessRecipe myceliumRecipe = new ShapelessRecipe(key, mycelium);
		myceliumRecipe.addIngredient(Material.DIRT);
		myceliumRecipe.addIngredient(Material.BROWN_MUSHROOM);
		myceliumRecipe.addIngredient(Material.RED_MUSHROOM);
		Main.getInstance().getServer().addRecipe(myceliumRecipe);
	}
	
	public void podzolRecipeOak() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "5");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.OAK_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}
	
	public void podzolRecipeBirch() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "6");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.BIRCH_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}
	
	public void podzolRecipeAcacia() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "7");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.ACACIA_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}
	
	public void podzolRecipeDarkOak() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "8");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.DARK_OAK_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}
	
	public void podzolRecipeJungle() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "9");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.JUNGLE_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}
	
	public void podzolRecipeSpruce() {
		ItemStack podzol = new ItemStack(Material.PODZOL, 1);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "10");
		ShapelessRecipe podzolRecipe = new ShapelessRecipe(key, podzol);
		podzolRecipe.addIngredient(Material.DIRT);
		podzolRecipe.addIngredient(Material.SPRUCE_LEAVES);
		Main.getInstance().getServer().addRecipe(podzolRecipe);
	}


	@EventHandler
	public void FurnaceSmeltListener(FurnaceSmeltEvent e) {

		if (e.getSource().isSimilar(wheat)) {
			ItemStack dirtItem = new ItemStack(Material.COARSE_DIRT, 1);
			ItemMeta dirtMeta = dirtItem.getItemMeta();
			ArrayList<String> lore = new ArrayList<>();
			lore.add(CharacterCMD.mess("This is inedible! Where's the Baker?"));
			dirtMeta.setLore(lore);
			dirtItem.setItemMeta(dirtMeta);
			e.setResult(dirtItem);
		} else if (e.getSource().getItemMeta().getDisplayName().contentEquals(getDough().getItemMeta().getDisplayName()) && e.getSource().getItemMeta().hasLore()) {
			ItemStack newResult = getBread();
			ItemMeta resultMeta = newResult.getItemMeta();
			ArrayList<String> resultLore = new ArrayList<>(resultMeta.getLore());
			resultLore.add(e.getSource().getItemMeta().getLore().get(2));
			resultMeta.setLore(resultLore);
			newResult.setItemMeta(resultMeta);
			e.setResult(newResult);
		}

	}

	@EventHandler
	public void CraftListener(CraftItemEvent e) {

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(CharacterCMD.mess("&eBaker's Dough"))) {
			if (e.getWhoClicked() instanceof Player) {
				Player p = (Player) e.getWhoClicked();
				if (API.getProfession(p).equalsIgnoreCase("Baker")) {
					ItemMeta meta = e.getCurrentItem().getItemMeta();
					ArrayList<String> lore = new ArrayList<String>(meta.getLore());
					lore.add(CharacterCMD.mess("[Made by &c&o" + p.getName() + "&5&o]"));
					meta.setLore(lore);
					e.getCurrentItem().setItemMeta(meta);
				} else {
					e.setCancelled(true);
					p.sendMessage(CharacterCMD.mess("&7[&c*&7] &cYou must be a &6Baker &cto craft this"));
					p.sendMessage(CharacterCMD.mess("&7[&c*&7] &7Type &e/char profession &7to choose a profession"));
				}
			}
			return;
		} else if (e.getCurrentItem().getType() == Material.PODZOL || e.getCurrentItem().getType() == Material.MYCELIUM) {
			if (e.getWhoClicked() instanceof Player) {
				Player p = (Player) e.getWhoClicked();
				if (!API.getProfession(p).equalsIgnoreCase("Farmer")) {
					e.setCancelled(true);
					p.sendMessage(CharacterCMD.mess("&7[&c*&7] &cYou must be a &6Farmer &cto craft this"));
					return;
				}
			}
		}

	}

}

// FurnaceRecipe breadRecipe = new FurnaceRecipe(bakersBread, Material.WHEAT);

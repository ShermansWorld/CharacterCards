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
import org.bukkit.inventory.meta.ItemMeta;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;
import me.r0m3x.iccards.cmds.CharacterCMD;

//import org.bukkit.inventory.FurnaceRecipe;
//import org.bukkit.inventory.ItemStack;


public class CraftingRecipies implements Listener {
	
	ItemStack wheat = new ItemStack(Material.WHEAT, 1);
	
	public boolean isBakersBread = false;
	
	public void doughRecipe(){
		ItemStack bakersDough = new ItemStack(Material.WHEAT, 6);
		ItemMeta bakersDoughMeta = bakersDough.getItemMeta();
		bakersDoughMeta.setDisplayName(CharacterCMD.mess("&eBaker's Dough"));
		ArrayList<String> breadLore = new ArrayList<>();
		breadLore.add(CharacterCMD.mess("[Smelting yeilds 1 &e&oBaker's Bread&5&o]"));
		breadLore.add(CharacterCMD.mess("[Only &e&oBakers &5&ocan craft this]"));
		bakersDoughMeta.setLore(breadLore);
		bakersDough.setItemMeta(bakersDoughMeta);
		
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "1");
		
		ShapedRecipe bakersDoughRecipe = new ShapedRecipe(key, bakersDough);
		bakersDoughRecipe.shape("###", "#$#", "###");
		bakersDoughRecipe.setIngredient('$', Material.WATER_BUCKET);
		bakersDoughRecipe.setIngredient('#', Material.WHEAT);
		Main.getInstance().getServer().addRecipe(bakersDoughRecipe);
		
		
	}
	
	public void breadRecipe(){
		ItemStack bakersBread = new ItemStack(Material.BREAD, 1);
		ItemMeta bakersBreadMeta = bakersBread.getItemMeta();
		bakersBreadMeta.setDisplayName(CharacterCMD.mess("&eBaker's Bread"));
		ArrayList<String> breadLore = new ArrayList<>();
		breadLore.add(CharacterCMD.mess("[Made from &e&oBaker's Dough &5&o]"));
		bakersBreadMeta.setLore(breadLore);
		bakersBread.setItemMeta(bakersBreadMeta);
		NamespacedKey key = new NamespacedKey(Main.getInstance(), Main.getInstance().getDescription().getName() + "2");
		//FurnaceRecipe bakersBreadRecipe = new FurnaceRecipe(bakersBread, Material.WHEAT);
		FurnaceRecipe bakersBreadRecipe = new FurnaceRecipe(key, bakersBread, Material.WHEAT, (float) 0.0, 30);
		Main.getInstance().getServer().addRecipe(bakersBreadRecipe);
		
	}
	
	
    @EventHandler
    public void FurnaceSmeltListener(FurnaceSmeltEvent e) {
    	
    	if (e.getSource().isSimilar(wheat)) {
    		if(!e.getSource().hasItemMeta()) {
    			ItemStack dirtItem = new ItemStack(Material.COARSE_DIRT, 1);
            	ItemMeta dirtMeta = dirtItem.getItemMeta();
            	ArrayList<String> lore = new ArrayList<>();
            	lore.add(CharacterCMD.mess("This is inedible! Where's the Baker?"));
            	dirtMeta.setLore(lore);
            	e.getResult().setItemMeta(dirtMeta);
            	e.setResult(dirtItem);
    		} else {
    			ItemMeta sourceMeta = e.getSource().getItemMeta();
        		String whoCrafted = sourceMeta.getLore().get(2);
        		ItemMeta resultMeta = e.getResult().getItemMeta();
        		ArrayList<String> resultLore = new ArrayList<String>(resultMeta.getLore());
        		resultLore.add(whoCrafted);
        		resultMeta.setLore(resultLore);
        		e.getResult().setItemMeta(resultMeta);
    		}
    	}
    	
    }
    
    @EventHandler
    public void CraftListener(CraftItemEvent e) {
    	
    	if(e.getCurrentItem().getItemMeta().getDisplayName().equals(CharacterCMD.mess("&eBaker's Dough"))) {
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
    	}
    	
    }
    
    
    
}
	
	
	//FurnaceRecipe breadRecipe = new FurnaceRecipe(bakersBread, Material.WHEAT);

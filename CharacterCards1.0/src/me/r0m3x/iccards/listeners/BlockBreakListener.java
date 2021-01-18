package me.r0m3x.iccards.listeners;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;

public class BlockBreakListener implements Listener {
	
	private static final Set<Material> hoes = new HashSet<>();
	static {
		hoes.add(Material.WOODEN_HOE);
		hoes.add(Material.STONE_HOE);
		hoes.add(Material.IRON_HOE);
		hoes.add(Material.GOLDEN_HOE);
		hoes.add(Material.DIAMOND_HOE);
		hoes.add(Material.NETHERITE_HOE);
	}
	
	@EventHandler
	public void BreakBlock(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		
		if (hoes.contains(p.getInventory().getItemInMainHand().getType()) && API.getProfession(p).contentEquals("Farmer")) { // if profession is farmer
			
			
			if (b.getType() == Material.WHEAT) {
				if (p.getInventory().contains(Material.WHEAT_SEEDS, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.WHEAT);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.WHEAT_SEEDS) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 1);
				}
			} else if (b.getType() == Material.BEETROOTS) {
				if (p.getInventory().contains(Material.BEETROOT_SEEDS, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.BEETROOTS);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.BEETROOT_SEEDS) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 1);
				}
			} else if (b.getType() == Material.CARROTS) {
				if (p.getInventory().contains(Material.CARROT, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.CARROTS);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.CARROT) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 1);
				}
			} else if (b.getType() == Material.POTATOES) {
				if (p.getInventory().contains(Material.POTATO, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.POTATOES);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.POTATO) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 1);
				}
			} else if (b.getType() == Material.SUGAR_CANE) {
				if (p.getInventory().contains(Material.SUGAR_CANE, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.SUGAR_CANE);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.SUGAR_CANE) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 2);
				}
			} else if (b.getType() == Material.CACTUS) {
				if (p.getInventory().contains(Material.CACTUS, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.CACTUS);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.CACTUS) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 2);
				}
			} else if (b.getType() == Material.BAMBOO) {
				if (p.getInventory().contains(Material.BAMBOO, 1)) {
					Location loc = b.getLocation();
					new BukkitRunnable() {
		                @Override public void run() {
		                	loc.getBlock().setType(Material.BAMBOO);
		                	for (ItemStack item : p.getInventory().getContents()) {
		                		if (item != null) {
		                			if (item.getType() == Material.BAMBOO) {
			                			int itemAmount = item.getAmount();
			                            item.setAmount(itemAmount - 1);
			                            final Damageable dmg = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
			                            dmg.setDamage(dmg.getDamage()+1);
			                            p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) dmg);
			                            if (dmg.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
			                            	p.getInventory().clear(p.getInventory().getHeldItemSlot());
			                            	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
			                            }
			                			return;
			                		}
	                			}
		                	}
		                }
		            }.runTaskLater(Main.getInstance(), 2);
				}
			}
			
			
		}
		
	}
	
	
	
	
}

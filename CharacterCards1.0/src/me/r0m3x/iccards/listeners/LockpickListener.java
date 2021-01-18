package me.r0m3x.iccards.listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import me.r0m3x.iccards.API;
import me.r0m3x.iccards.Main;
import me.r0m3x.iccards.TownyCompatibility;

public class LockpickListener implements Listener {

	private String mess(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	PluginManager pm = Main.getInstance().getServer().getPluginManager();

	public static Map<String, Integer> lockpickMap = new HashMap<>();
	
	private static final Set<Material> normalDoors = new HashSet<>();

	static {
		normalDoors.add(Material.ACACIA_DOOR);
		normalDoors.add(Material.BIRCH_DOOR);
		normalDoors.add(Material.DARK_OAK_DOOR);
		normalDoors.add(Material.JUNGLE_DOOR);
		normalDoors.add(Material.SPRUCE_DOOR);
		normalDoors.add(Material.OAK_DOOR);
	}

	private static final Set<Material> gates = new HashSet<>();

	static {
		gates.add(Material.ACACIA_FENCE_GATE);
		gates.add(Material.BIRCH_FENCE_GATE);
		gates.add(Material.DARK_OAK_FENCE_GATE);
		gates.add(Material.JUNGLE_FENCE_GATE);
		gates.add(Material.SPRUCE_FENCE_GATE);
		gates.add(Material.OAK_FENCE_GATE);
	}

	private static final Set<Material> trapdoors = new HashSet<>();

	static {
		trapdoors.add(Material.ACACIA_TRAPDOOR);
		trapdoors.add(Material.BIRCH_TRAPDOOR);
		trapdoors.add(Material.DARK_OAK_TRAPDOOR);
		trapdoors.add(Material.JUNGLE_TRAPDOOR);
		trapdoors.add(Material.SPRUCE_TRAPDOOR);
		trapdoors.add(Material.OAK_TRAPDOOR);
	}

	private static final Set<Material> sign = new HashSet<>();

	static {
		sign.add(Material.ACACIA_SIGN);
		sign.add(Material.ACACIA_WALL_SIGN);
		sign.add(Material.BIRCH_SIGN);
		sign.add(Material.BIRCH_WALL_SIGN);
		sign.add(Material.DARK_OAK_SIGN);
		sign.add(Material.DARK_OAK_WALL_SIGN);
		sign.add(Material.JUNGLE_SIGN);
		sign.add(Material.JUNGLE_WALL_SIGN);
		sign.add(Material.SPRUCE_SIGN);
		sign.add(Material.SPRUCE_WALL_SIGN);
		sign.add(Material.OAK_SIGN);
		sign.add(Material.OAK_WALL_SIGN);
	}

	private static final Set<Material> irontrapdoors = new HashSet<>();

	static {
		irontrapdoors.add(Material.IRON_TRAPDOOR);
	}

	private static final Set<Material> iron_door = new HashSet<>();

	static {
		iron_door.add(Material.IRON_DOOR);
	}

	public static int getRandom(int chance) {
		Random random = new Random();
		return random.nextInt(chance) + 1;
	}

	static void openDoor(Block b, World w, Player p) {
		if (!b.hasMetadata("door")) {
            b.setMetadata("door", new FixedMetadataValue(Main.getInstance(), "opened"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
                    Door d = (Door) b.getBlockData();
                    if (!d.isOpen()) { w.playSound(b.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 10, 1); }
                    d.setOpen(true);
                    b.setBlockData(d);
                    b.removeMetadata("door", Main.getInstance());
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
		
	}
	
	static void openGate(Block b, World w, Player p) {
		if (!b.hasMetadata("gate")) {
            b.setMetadata("gate", new FixedMetadataValue(Main.getInstance(), "opened"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
                    Gate g = (Gate) b.getBlockData();
                    if (!g.isOpen()) { w.playSound(b.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 10, 1); }
                    g.setOpen(true);
                    b.setBlockData(g);
                    b.removeMetadata("door", Main.getInstance());
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
		
	}
	
	static void openTrapdoor(Block b, World w, Player p) {
		if (!b.hasMetadata("trapdoor")) {
            b.setMetadata("trapdoor", new FixedMetadataValue(Main.getInstance(), "trapdoor"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
                    TrapDoor t = (TrapDoor) b.getBlockData();
                    if (!t.isOpen()) { w.playSound(b.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 10, 1); }
                    t.setOpen(true);
                    b.setBlockData(t);
                    b.removeMetadata("trapdoor", Main.getInstance());
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
		
	}
	
	static void openIronDoor(Block b, World w, Player p) {
		if (!b.hasMetadata("door")) {
            b.setMetadata("door", new FixedMetadataValue(Main.getInstance(), "opened"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
                    Door d = (Door) b.getBlockData();
                    if (!d.isOpen()) { w.playSound(b.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 10, 1); }
                    d.setOpen(true);
                    b.setBlockData(d);
                    b.removeMetadata("door", Main.getInstance());
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
		
	}
	
	static void openIronTrapdoor(Block b, World w, Player p) {
		if (!b.hasMetadata("trapdoor")) {
            b.setMetadata("trapdoor", new FixedMetadataValue(Main.getInstance(), "trapdoor"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
                    TrapDoor t = (TrapDoor) b.getBlockData();
                    if (!t.isOpen()) { w.playSound(b.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 10, 1); }
                    t.setOpen(true);
                    b.setBlockData(t);
                    b.removeMetadata("trapdoor", Main.getInstance());
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
		
	}
	
	public static void openChest(Block b, World w, Player p) {
            b.setMetadata("trapdoor", new FixedMetadataValue(Main.getInstance(), "trapdoor"));
            p.getInventory().clear(p.getInventory().getHeldItemSlot());
            new BukkitRunnable() {
                @Override public void run() {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a*&8] &aThe lock has successfully been opened!"));
					Chest chest = (Chest) b.getState();
					p.openInventory(chest.getInventory());
                    w.playSound(b.getLocation(), Sound.BLOCK_CHEST_OPEN, 10, 1);
                    p.getInventory().addItem(CraftingRecipies.getLockPick());
                    lockpickMap.put(p.getName(), 0);
                }
            }.runTaskLater(Main.getInstance(), 40);
	}
	
	public static void pickFailed(Player p) {
		p.getInventory().clear(p.getInventory().getHeldItemSlot());
		new BukkitRunnable() {
            @Override public void run() {
            	p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&c*&7] &cThe lockpick breaks, and you bleed a little..."));
				p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.1F);
				if (p.getHealth() > 1.0D) {
					p.setHealth(p.getHealth() - 1.0D);
				} else {
					p.setHealth(0.0D);
				}
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
				lockpickMap.put(p.getName(), 0);
            }
        }.runTaskLater(Main.getInstance(), 40);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		if (action == Action.LEFT_CLICK_BLOCK && p.isSneaking() && p.getInventory().getItemInMainHand().getType() == Material.IRON_HOE
				&& p.getInventory().getItemInMainHand().getItemMeta().hasLore()
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(mess("&e&lLockpick"))) {
			Block block = e.getClickedBlock();
			World w = p.getWorld();
			if (!API.getProfession(p).equalsIgnoreCase("Thief")) {
				p.sendMessage(mess("&7[&c*&7] &cOnly &eThieves &ccan use lockpicks"));
				return;
			}
			if (block.getType() == Material.CHEST || block.getType() == Material.TRAPPED_CHEST) {
				if (pm.getPlugin("Towny") != null) {
					TownyCompatibility.TownyChest(p, block, w);
					return;
				} else {
					if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
					if (getRandom(5) == 1) {
						openChest(block, w, p);
					} else {
						pickFailed(p);
					}
					return;
				}
			} else if (normalDoors.contains(block.getType())) {
                Door d = (Door) block.getBlockData();
                if (!d.isOpen()) {
                	if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
                	if (getRandom(5) == 1) {
                		openDoor(block, w, p);
    				} else {
    					pickFailed(p);
    				}
                }
			} else if (gates.contains(block.getType())) {
				Gate g = (Gate) block.getBlockData();
                if (!g.isOpen()) {
                	if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
                	if (getRandom(5) == 1) {
                		openGate(block, w, p);
    				} else {
    					pickFailed(p);
    				}
                }
			} else if (iron_door.contains(block.getType())) {
				Door d = (Door) block.getBlockData();
                if (!d.isOpen()) {
                	if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
                	if (getRandom(10) == 1) {
                		openIronDoor(block, w, p);
    				} else {
    					pickFailed(p);
    				}
                }
			} else if (trapdoors.contains(block.getType())) {
				TrapDoor t = (TrapDoor) block.getBlockData();
                if (!t.isOpen()) {
                	if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
                	if (getRandom(5) == 1) {
                		openTrapdoor(block, w, p);
    				} else {
    					pickFailed(p);
    				}
                }
			} else if (irontrapdoors.contains(block.getType())) {
				TrapDoor t = (TrapDoor) block.getBlockData();
                if (!t.isOpen()) {
                	if (!lockpickMap.containsKey(p.getName())) {
                		lockpickMap.put(p.getName(), 0);
                	} else {
                		if (lockpickMap.get(p.getName()) == 1) {
                			p.sendMessage(mess("&7[&c*&7] &cYou are already trying to lockpick something!"));
                			return;
                		}
                	}
                	lockpickMap.put(p.getName(), 1);
                	p.sendMessage(mess("&8[&a*&8] &7Here goes nothing..."));
                	if (getRandom(10) == 1) {
                		openTrapdoor(block, w, p);
    				} else {
    					pickFailed(p);
    				}
                }
			}
		}
	}
}
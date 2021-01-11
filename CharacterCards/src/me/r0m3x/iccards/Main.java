package me.r0m3x.iccards;

import java.io.File;
import java.util.Calendar;

import me.r0m3x.iccards.cmds.CharacterCMD;
import me.r0m3x.iccards.cmds.Profession;
import me.r0m3x.iccards.cmds.UUID;
import me.r0m3x.iccards.listeners.CraftingRecipies;
import me.r0m3x.iccards.listeners.MakeFileonJoin;
import me.r0m3x.iccards.listeners.PlayerInteractEntity;
import me.r0m3x.iccards.listeners.ProfessionJoin;
import me.r0m3x.tabCompletion.CharTabCompletion;
import me.r0m3x.tabCompletion.ProfessionTabCompletion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Calendar now = Calendar.getInstance();

	int year = this.now.get(1);

	PluginDescriptionFile pdf = getDescription();

	PluginManager pm = Bukkit.getPluginManager();

	ConsoleCommandSender cs = Bukkit.getConsoleSender();

	public static Main instance = null;

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {
    File usersfolder = new File("plugins" + File.separator + "ICCards" + File.separator + "users");
    if (!usersfolder.exists())
      usersfolder.mkdirs(); 
    instance = this;
    registerEvents();
    registerCommands();
    registerTabCompletion();
  }

	private void registerCommands() {
		getCommand("Character").setExecutor((CommandExecutor) new CharacterCMD(this));
		getCommand("UUID").setExecutor((CommandExecutor) new UUID(this));
		getCommand("Profession").setExecutor((CommandExecutor) new Profession(this));
	}

	private void registerTabCompletion() {
		getCommand("Character").setTabCompleter(new CharTabCompletion());
		getCommand("Profession").setTabCompleter(new ProfessionTabCompletion());
	}

	private void registerEvents() {
		this.pm.registerEvents((Listener) new MakeFileonJoin(), (Plugin) this);
		this.pm.registerEvents((Listener) new PlayerInteractEntity(), (Plugin) this);
		this.pm.registerEvents((Listener) new ProfessionJoin(), (Plugin) this);
		this.pm.registerEvents((Listener) new CraftingRecipies(), (Plugin) this);

		// Custom recipies
		CraftingRecipies customRecipies = new CraftingRecipies();
		customRecipies.breadRecipe();
		customRecipies.doughRecipe();
	}

	public void onDisable() {
	}

	private String mess(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}

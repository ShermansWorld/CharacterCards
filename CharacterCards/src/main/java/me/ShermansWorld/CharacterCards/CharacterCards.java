package me.ShermansWorld.CharacterCards;

import java.io.File;

import me.ShermansWorld.CharacterCards.commands.CharTabCompletion;
import me.ShermansWorld.CharacterCards.commands.CharacterCardsCommands;
import me.ShermansWorld.CharacterCards.commands.CharacterCardsTabCompletion;
import me.ShermansWorld.CharacterCards.commands.CharacterCommands;
import me.ShermansWorld.CharacterCards.commands.UUID;
import me.ShermansWorld.CharacterCards.config.Config;
import me.ShermansWorld.CharacterCards.lang.Languages;
import me.ShermansWorld.CharacterCards.listeners.MakeFileonJoin;
import me.ShermansWorld.CharacterCards.listeners.PlayerInteractEntity;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CharacterCards extends JavaPlugin {
  
  public static boolean usingTowny = false;
  
  public static boolean usingMythicalRaces = false;
  
  public static boolean usingKonquest = false;
  
  public static boolean usingMagic = false;
  
  public static boolean usingPAPI = false;
  
  PluginDescriptionFile pdf = getDescription();
  
  PluginManager pm = Bukkit.getPluginManager();
  
  ConsoleCommandSender cs = Bukkit.getConsoleSender();
  
  public static CharacterCards instance = null;
  
  private void registerCommands() {
    getCommand("Character").setExecutor((CommandExecutor)new CharacterCommands(this));
    getCommand("CharacterCards").setExecutor((CommandExecutor)new CharacterCardsCommands(this));
    getCommand("UUID").setExecutor((CommandExecutor)new UUID(this));
  }
  
  private void registerTabCompletion() {
    getCommand("Character").setTabCompleter((TabCompleter)new CharTabCompletion());
    getCommand("CharacterCards").setTabCompleter((TabCompleter)new CharacterCardsTabCompletion());
  }
  
  private void registerEvents() {
    this.pm.registerEvents((Listener)new MakeFileonJoin(), (Plugin)this);
    this.pm.registerEvents((Listener)new PlayerInteractEntity(), (Plugin)this);
  }
  
  private void initHooks() {
    if (Bukkit.getServer().getPluginManager().getPlugin("Towny") != null) {
      usingTowny = true;
      Bukkit.getLogger().info("[CharacterCards] Towny detected! Enabling support...");
    } 
    if (Bukkit.getServer().getPluginManager().getPlugin("MythicalRaces") != null) {
      usingMythicalRaces = true;
      Bukkit.getLogger().info("[CharacterCards] Mythical Races detected! Enabling support...");
    } 
    if (Bukkit.getServer().getPluginManager().getPlugin("Konquest") != null) {
      usingKonquest = true;
      Bukkit.getLogger().info("[CharacterCards] Konquest detected! Enabling support...");
    } 
    if (Bukkit.getServer().getPluginManager().getPlugin("Magic") != null) {
      usingMagic = true;
      Bukkit.getLogger().info("[CharacterCards] Magic detected! Enabling support...");
    } 
    if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
      usingPAPI = true;
      Bukkit.getLogger().info("[CharacterCards] PlaceholderAPI (PAPI) detected! Enabling support...");
    } 
  }
  
  public static void initData() {
    File usersfolder = new File("plugins" + File.separator + "CharacterCards" + File.separator + "users");
    if (!usersfolder.exists())
      usersfolder.mkdirs(); 
    File langFolder = new File("plugins" + File.separator + "CharacterCards" + File.separator + "lang");
    if (!langFolder.exists())
      langFolder.mkdirs(); 
    Languages.initLangs();
  }
  
  public static CharacterCards getInstance() {
    return instance;
  }
  
  public void onEnable() {
    instance = this;
    saveDefaultConfig();
    initData();
    registerEvents();
    registerCommands();
    registerTabCompletion();
    initHooks();
    Config.initConfigVals();
  }
}

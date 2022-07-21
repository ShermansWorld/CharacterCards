package me.ShermansWorld.CharacterCards.config;

import me.ShermansWorld.CharacterCards.Main;

public class ConfigVals {
	
	// config version
	public static int configVersion = 1;
	
	// language
	public static String lang = "enUS"; // defaults to English
	
	// character card settings
	public static int minAge = 14;
	public static int maxAge = 120;
	public static int descCharLimit = 512;
	
	// interaction settings
	public static boolean shiftRightClick = true;
	public static boolean paperRightClick = true;
	
	// hooks
	public static boolean integrateTowny = true;
	public static boolean integrateMythicalRaces = true;
	public static boolean integrateKonquest = true;
	public static boolean integrateMagic = true;
	
	public static void initConfigVals() {
		
		// config version
		configVersion = Main.getInstance().getConfig().getInt("config-version");
		
		// language
		lang = Main.getInstance().getConfig().getString("lang");
		
		// character card settings
		minAge = Main.getInstance().getConfig().getInt("MinAge");
		maxAge = Main.getInstance().getConfig().getInt("MaxAge");
		descCharLimit = Main.getInstance().getConfig().getInt("DescCharLimit");
		
		// interaction settings
		shiftRightClick = Main.getInstance().getConfig().getBoolean("ShiftRightClick");
		paperRightClick = Main.getInstance().getConfig().getBoolean("RightClickPaper");
		
		// hooks
		integrateTowny = Main.getInstance().getConfig().getBoolean("IntegrateTowny");
		integrateMythicalRaces = Main.getInstance().getConfig().getBoolean("IntegrateMythicalRaces");
		integrateKonquest = Main.getInstance().getConfig().getBoolean("IntegrateKonquest");
		integrateMagic = Main.getInstance().getConfig().getBoolean("IntegrateMagic");
	}	
}

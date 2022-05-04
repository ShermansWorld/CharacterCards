package me.ShermansWorld.CharacterCards.config;

import me.ShermansWorld.CharacterCards.Main;

public class ConfigVals {
	
	// config version
	public static int configVersion = 1;
	
	// character card settings
	public static int minAge = 14;
	public static int maxAge = 120;
	
	// interaction settings
	public static boolean shiftRightClick = true;
	public static boolean paperRightClick = true;
	
	// hooks
	public static boolean integrateTowny = true;
	
	public static void initConfigVals() {
		
		// config version
		configVersion = Main.getInstance().getConfig().getInt("config-version");
		
		// character card settings
		minAge = Main.getInstance().getConfig().getInt("MinAge");
		maxAge = Main.getInstance().getConfig().getInt("MaxAge");
		
		// interaction settings
		shiftRightClick = Main.getInstance().getConfig().getBoolean("ShiftRightClick");
		paperRightClick = Main.getInstance().getConfig().getBoolean("RightClickPaper");
		
		// hooks
		integrateTowny = Main.getInstance().getConfig().getBoolean("IntegrateTowny");
	}
}

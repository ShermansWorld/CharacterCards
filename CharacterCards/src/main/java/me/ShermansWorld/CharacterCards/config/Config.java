package me.ShermansWorld.CharacterCards.config;

import java.util.LinkedHashMap;

import org.bukkit.Bukkit;

import me.ShermansWorld.CharacterCards.Main;

public class Config {

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

	// custom fields
	public static boolean usingCustomFields = false;

	// Label, Placeholder
	public static LinkedHashMap<String, String> customFieldsMap = new LinkedHashMap<String, String>();

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

		// custom fields (requires PAPI)
		if (Main.usingPAPI) {
			int customFieldLen = Main.getInstance().getConfig().getConfigurationSection("CustomFields").getKeys(false)
					.size();
			if (customFieldLen > 0) {
				usingCustomFields = true;
				Bukkit.getLogger().info("[CharacterCards] Custom Fields detected! Loading...");
				for (int i = 1; i <= customFieldLen; i++) {
					String label = Main.getInstance().getConfig()
							.getString("CustomFields." + String.valueOf(i) + ".label");
					String placeholder = Main.getInstance().getConfig()
							.getString("CustomFields." + String.valueOf(i) + ".placeholder");
					customFieldsMap.put(label, placeholder);
				}

				Bukkit.getLogger().info(
						"[CharacterCards] Successfully loaded " + String.valueOf(customFieldLen) + " custom fields");
			}
		}
	}
}

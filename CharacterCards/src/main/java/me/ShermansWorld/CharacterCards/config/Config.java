package me.ShermansWorld.CharacterCards.config;

import java.util.LinkedHashMap;
import me.ShermansWorld.CharacterCards.CharacterCards;
import org.bukkit.Bukkit;

public class Config {
	public static int configVersion = 1;

	public static String lang = "enUS";

	public static int minAge = 14;

	public static int maxAge = 120;

	public static int descCharLimit = 512;

	public static boolean shiftRightClick = true;

	public static boolean paperRightClick = true;

	public static boolean integrateTowny = true;

	public static boolean integrateMythicalRaces = true;

	public static boolean integrateKonquest = true;

	public static boolean integrateMagic = true;

	public static boolean integratePAPI = true;

	public static boolean usingCustomFields = false;

	public static LinkedHashMap<String, String> customFieldsMap = new LinkedHashMap<>();

	public static void initConfigVals() {
		configVersion = CharacterCards.getInstance().getConfig().getInt("config-version");
		lang = CharacterCards.getInstance().getConfig().getString("lang");
		minAge = CharacterCards.getInstance().getConfig().getInt("MinAge");
		maxAge = CharacterCards.getInstance().getConfig().getInt("MaxAge");
		descCharLimit = CharacterCards.getInstance().getConfig().getInt("DescCharLimit");
		shiftRightClick = CharacterCards.getInstance().getConfig().getBoolean("ShiftRightClick");
		paperRightClick = CharacterCards.getInstance().getConfig().getBoolean("RightClickPaper");
		integrateTowny = CharacterCards.getInstance().getConfig().getBoolean("IntegrateTowny");
		integrateMythicalRaces = CharacterCards.getInstance().getConfig().getBoolean("IntegrateMythicalRaces");
		integrateKonquest = CharacterCards.getInstance().getConfig().getBoolean("IntegrateKonquest");
		integrateMagic = CharacterCards.getInstance().getConfig().getBoolean("IntegrateMagic");
		integratePAPI = CharacterCards.getInstance().getConfig().getBoolean("IntegratePAPI");
		if (CharacterCards.usingPAPI && integratePAPI) {
			int customFieldLen = CharacterCards.getInstance().getConfig().getConfigurationSection("CustomFields").getKeys(false)
					.size();
			if (customFieldLen > 0) {
				usingCustomFields = true;
				Bukkit.getLogger().info("[CharacterCards] Custom Fields detected! Loading...");
				for (int i = 1; i <= customFieldLen; i++) {
					String label = CharacterCards.getInstance().getConfig()
							.getString("CustomFields." + String.valueOf(i) + ".label");
					String placeholder = CharacterCards.getInstance().getConfig()
							.getString("CustomFields." + String.valueOf(i) + ".placeholder");
					customFieldsMap.put(label, placeholder);
				}
				Bukkit.getLogger().info(
						"[CharacterCards] Successfully loaded " + String.valueOf(customFieldLen) + " custom fields");
			}
		}
	}
}

package me.r0m3x.tabCompletion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CharTabCompletion implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();

		if (args.length == 1) {
			completions.add("Name");
			completions.add("Gender");
			completions.add("Age");
			completions.add("Desc");
			completions.add("Profession");
			completions.add("View");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("GENDER")) {
			completions.add("Male");
			completions.add("Female");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("AGE")) {
			for (int i = 14; i <= 120; i++) {
				completions.add(String.valueOf(i));
			}

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("PROFESSION")) {
			completions.add("Thief");
			completions.add("Fisherman");
			completions.add("Miner");
			completions.add("Soldier");
			completions.add("Baker");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("VIEW")) {

			return null;
		}

		return Collections.emptyList();
	}
}
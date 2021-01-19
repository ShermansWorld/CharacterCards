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
			completions.add("name");
			completions.add("gender");
			completions.add("age");
			completions.add("desc");
			completions.add("profession");
			completions.add("view");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("GENDER")) {
			completions.add("male");
			completions.add("female");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("AGE")) {
			for (int i = 14; i <= 120; i++) {
				completions.add(String.valueOf(i));
			}

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("PROFESSION")) {
			completions.add("thief");
			completions.add("farmer");
			completions.add("fisherman");
			completions.add("miner");
			completions.add("soldier");
			completions.add("baker");

			return completions;
		}
		
		if (args.length == 2 && args[0].toUpperCase().contentEquals("VIEW")) {

			return null;
		}

		return Collections.emptyList();
	}
}

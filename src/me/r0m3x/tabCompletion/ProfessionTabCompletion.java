package me.r0m3x.tabCompletion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ProfessionTabCompletion implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();

		if (args.length == 1) {
			completions.add("Thief");
			completions.add("Fisherman");
			completions.add("Miner");
			completions.add("Soldier");
			completions.add("Baker");

			return completions;
		}

		return Collections.emptyList();
	}
}
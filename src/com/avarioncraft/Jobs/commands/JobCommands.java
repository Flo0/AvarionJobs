package com.avarioncraft.Jobs.commands;

import org.bukkit.entity.Player;

import com.avarioncraft.Jobs.core.AvarionJobs;

import net.crytec.api.devin.commands.Command;
import net.crytec.api.devin.commands.CommandResult;
import net.crytec.api.devin.commands.Commandable;

public class JobCommands implements Commandable{
	
	@Command(struct = "job", aliases = "jobs", desc = "Öffnet das Jobs Menü.")
	public CommandResult openJobGUI(Player sender) {
		AvarionJobs.getPlugin().getGui().open(sender);
		return CommandResult.success();
	}
	
	@Command(struct = "job spawnfish", desc = "Öffnet das Jobs Menü.")
	public CommandResult JobDebugCommand(Player sender) {

		
		
		return CommandResult.success();
	}

}

package com.avarioncraft.Jobs.listener;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.fileIO.JobFiles;
import com.avarioncraft.Jobs.skills.mineSkills.MassDestructPickaxe;
import com.avarioncraft.Jobs.skills.mineSkills.util.OrePouch;
import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;

public class JoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void playerJoin(PlayerJoinEvent event) {
		JobPlayer jp = new JobPlayer(event.getPlayer());
		jp.getSkills(JobSkillTrigger.BLOCK_BREAK).add(new MassDestructPickaxe(jp, 4));
		FileConfiguration config = JobFiles.get().getOrePouchConfig();
		if (!config.contains(event.getPlayer().getUniqueId().toString())) {
			// TODO size löschen
			new OrePouch(jp, 5);
			return;
		}
		new OrePouch(jp, config, 5);
	}

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		if (event.getClickedBlock().getType() != Material.ACACIA_PLANKS)
			return;
		SiteablePersistentInventory.of(event.getPlayer()).openPage(1);
	}

	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		JobPlayer.leave(event.getPlayer());
	}
}
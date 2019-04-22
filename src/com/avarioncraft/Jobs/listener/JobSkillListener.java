package com.avarioncraft.Jobs.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.skills.BlockBreakTrigger;
import com.avarioncraft.Jobs.skills.ClickTrigger;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;

public class JobSkillListener implements Listener{
	
	@EventHandler
	public void blockBreakTrigger(BlockBreakEvent event) {
		JobPlayer.of(event.getPlayer()).getSkills(JobSkillTrigger.BLOCK_BREAK).forEach(skill->{
			((BlockBreakTrigger) skill).onBlockBreak(event);
		});
	}
	
	@EventHandler
	public void clickTrigger(PlayerInteractEvent event) {
		JobPlayer.of(event.getPlayer()).getSkills(JobSkillTrigger.CLICK).forEach(skill->{
			((ClickTrigger) skill).onClick(event);
		});
	}

}

package com.avarioncraft.Jobs.skills.mineSkills;

import org.bukkit.event.player.PlayerInteractEvent;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.skills.ClickTrigger;
import com.avarioncraft.Jobs.skills.JobSkill;
import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;

public class OrePouchSkill extends JobSkill implements ClickTrigger{

	public OrePouchSkill(JobPlayer player) {
		super(player, "Erzbeutel", JobSkillTrigger.CLICK, 0);
	}

	@Override
	public void onClick(PlayerInteractEvent event) {
		SiteablePersistentInventory.of(event.getPlayer()).openPage(1);
	}

	@Override
	public void onTrigger() {}

}

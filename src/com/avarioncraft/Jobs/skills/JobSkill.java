package com.avarioncraft.Jobs.skills;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;

import lombok.Getter;

public abstract class JobSkill {
	
	public JobSkill(JobPlayer player, String name, JobSkillTrigger trigger, int cooldown) {
		this.name = name;
		this.trigger = trigger;
		this.cooldown = cooldown;
		this.player = player;
	}
	
	@Getter
	private final int cooldown;
	@Getter
	private final String name;
	@Getter
	private final JobSkillTrigger trigger;
	@Getter
	private final JobPlayer player; 
	
	public abstract void onTrigger();
	
}
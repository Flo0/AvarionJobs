package com.avarioncraft.Jobs.data;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import com.avarioncraft.Jobs.skills.JobSkill;
import com.avarioncraft.Jobs.util.enums.Job;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.Getter;

public class JobPlayer {
	
	//Constructor
	public JobPlayer(Player player) {
		this.player = player;
		this.settings = new JobSettings(player);
		for(Job job : Job.values()) {
			this.jobInformation.put(job, new JobPackage(player, job));
		}
		for(JobSkillTrigger trigger : JobSkillTrigger.values()) {
			this.jobSkills.put(trigger, Sets.newHashSet());
		}
		
		jobPlayerMap.put(player, this);
	}
	
	//Statics
	private static Map<Player, JobPlayer> jobPlayerMap = Maps.newHashMap();
	
	public static JobPlayer of(Player player) {
		return jobPlayerMap.get(player);
	}
	
	public static void leave(Player player) {
		jobPlayerMap.remove(player);
	}
	
	//Variablen
	@Getter
	private final Player player;
	private Map<Job, JobPackage> jobInformation = Maps.newHashMap();
	@Getter
	private Set<ExpModificationBuff> expBuffs = Sets.newHashSet();
	@Getter
	private JobSettings settings;
	private Map<JobSkillTrigger, Set<JobSkill>> jobSkills = Maps.newHashMap();
	
	//Methoden
	
	public Set<JobSkill> getSkills(JobSkillTrigger trigger){
		return this.jobSkills.get(trigger);
	}
	
	public JobPackage getJobInfo(Job job) {
		return this.jobInformation.get(job);
	}
	

}
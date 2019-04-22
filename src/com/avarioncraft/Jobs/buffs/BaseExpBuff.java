package com.avarioncraft.Jobs.buffs;

import com.avarioncraft.Jobs.data.ExpModificationBuff;
import com.avarioncraft.Jobs.data.JobExpPackage;
import com.avarioncraft.Jobs.events.PlayerJobExpEvent;
import com.avarioncraft.Jobs.util.enums.Job;

import lombok.Getter;
import lombok.Setter;

public class BaseExpBuff extends ExpModificationBuff{

	public BaseExpBuff(Job job, int percentBuff) {
		this.job = job;
		this.percentBuff = percentBuff;
	}

	private final Job job;
	@Getter @Setter
	private int percentBuff;
	
	@Override
	public void eventHandle(PlayerJobExpEvent event) {
		
		if(event.getJob().equals(this.job)) {
			JobExpPackage pack = event.getExpPack();
			pack.setExp(pack.getExp() + pack.getExp() * (this.percentBuff / 100));
		}
		
	}

}

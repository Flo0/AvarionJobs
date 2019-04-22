package com.avarioncraft.Jobs.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.avarioncraft.Jobs.data.JobExpPackage;
import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.events.PlayerJobExpEvent;

public class ExpEventListener implements Listener{
	
	@EventHandler
	public void expBar(PlayerJobExpEvent event) {
		System.out.println("Exp Event gefeuert");
		JobExpPackage expPack = event.getExpPack();
		
		String pre = "+";
		double value = expPack.getExp();
		if(expPack.getExp() < 0) pre = "";
		String jobName = expPack.getJob().getDisplayName();
		
		String bar = pre + " " + value + " " + jobName;
		
		JobPlayer.of(event.getPlayer()).getSettings().getJobBar().setTitle(bar);
	}

}

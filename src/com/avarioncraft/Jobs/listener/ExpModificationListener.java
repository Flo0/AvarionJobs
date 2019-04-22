package com.avarioncraft.Jobs.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.events.PlayerJobExpEvent;

public class ExpModificationListener implements Listener{
	
	@EventHandler
	public void expModify(PlayerJobExpEvent event) {
		
		JobPlayer jp = JobPlayer.of(event.getPlayer());
		jp.getExpBuffs().forEach(buff->{
			buff.eventHandle(event);
		});
		
	}
	
}
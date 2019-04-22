package com.avarioncraft.Jobs.data;

import com.avarioncraft.Jobs.events.PlayerJobExpEvent;

public abstract class ExpModificationBuff {
	
	public ExpModificationBuff() {
		
	}
	
	public abstract void eventHandle(PlayerJobExpEvent event);
	
}
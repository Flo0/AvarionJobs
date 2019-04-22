package com.avarioncraft.Jobs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.avarioncraft.Jobs.util.enums.Job;

import lombok.Getter;
import lombok.Setter;

public class PlayerJobLevelUpEvent extends JobEvent {

	private static final HandlerList handlers = new HandlerList();
		
	@Getter @Setter
	private int level;
	
	public PlayerJobLevelUpEvent(Player who, Job job, int level) {
		super(who, job);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
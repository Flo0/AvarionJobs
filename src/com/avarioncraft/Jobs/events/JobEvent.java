package com.avarioncraft.Jobs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.avarioncraft.Jobs.util.enums.Job;

import lombok.Getter;

public class JobEvent extends PlayerEvent{

	private static final HandlerList handlers = new HandlerList();
	
	public JobEvent(Player player, Job job) {
		super(player);
		this.job = job;
	}
	
	@Getter
	private final Job job;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}

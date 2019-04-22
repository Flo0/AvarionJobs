package com.avarioncraft.Jobs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.avarioncraft.Jobs.data.JobExpPackage;

import lombok.Getter;

public class PlayerJobExpEvent extends JobEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	
	public PlayerJobExpEvent(Player player, JobExpPackage expPack) {
		super(player, expPack.getJob());
		this.expPack = expPack;
	}
	
	@Getter
	private JobExpPackage expPack;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
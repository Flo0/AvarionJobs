package com.avarioncraft.Jobs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;

import lombok.Getter;

public class PersPageChangeEvent extends PersOpenEvent{
	
	private static final HandlerList handlers = new HandlerList();
	
	public PersPageChangeEvent(Player player, SiteablePersistentInventory inventory, int from, int to) {
		super(player, inventory, to);
		this.inventory = inventory;
		this.fromPage = from;
	}
	
	@Getter
	private final SiteablePersistentInventory inventory;
	@Getter
	private final int fromPage;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}

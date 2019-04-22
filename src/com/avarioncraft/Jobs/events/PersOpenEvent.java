package com.avarioncraft.Jobs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;

import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;

import lombok.Getter;

public class PersOpenEvent extends PlayerEvent{
	
	private static final HandlerList handlers = new HandlerList();
	
	public PersOpenEvent(Player player, SiteablePersistentInventory siteable, int page) {
		super(player);
		this.siteable = siteable;
		this.page = page;
		this.inv = siteable.getPage(page).getInventory();
	}
	
	@Getter
	private final SiteablePersistentInventory siteable;
	@Getter
	private final int page;
	@Getter
	private final Inventory inv;
	
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}

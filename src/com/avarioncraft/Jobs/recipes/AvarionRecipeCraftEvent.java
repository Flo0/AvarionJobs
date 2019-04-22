package com.avarioncraft.Jobs.recipes;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import com.avarioncraft.Jobs.data.JobPlayer;

import lombok.Getter;

public class AvarionRecipeCraftEvent extends Event implements Cancellable {

	public AvarionRecipeCraftEvent(Player player, AvarionRecipe recipe, ItemStack expectedResult) {
		this.recipe = recipe;
		this.jobPlayer = JobPlayer.of(player);
		this.expectedResult = expectedResult;
	}

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	
	@Getter
	private final Recipe recipe;
	@Getter
	private final JobPlayer jobPlayer;
	@Getter
	private ItemStack expectedResult;
	
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

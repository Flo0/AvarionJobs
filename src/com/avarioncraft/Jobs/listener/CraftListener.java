package com.avarioncraft.Jobs.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

import com.avarioncraft.Jobs.recipes.AvarionRecipe;

public class CraftListener implements Listener{
	
	private static int x = 0;
	
	@EventHandler
	public void prepareCraft(PrepareItemCraftEvent event) {
		Recipe vanillaRecipe = event.getRecipe();
		if(!(vanillaRecipe instanceof AvarionRecipe)) return;
		AvarionRecipe recipe = (AvarionRecipe) vanillaRecipe;
		recipe.onPrepare(event);
	}
	
	@EventHandler
	public void craft(CraftItemEvent event) {
		System.out.println("Fired" + x);
		x++;
	}
	
}

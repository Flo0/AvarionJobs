package com.avarioncraft.Jobs.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;

public class PersistentInventoryListener implements Listener{
	
	@EventHandler
	public void clickListener(InventoryClickEvent event) {
		if(event.getClickedInventory() == null) return;
		SiteablePersistentInventory siteable = SiteablePersistentInventory.of((Player) event.getWhoClicked());
		if(siteable == null) return;
		if(!siteable.getUsedPage().getInventory().equals(event.getInventory())) return;
		
		if(!siteable.isSlotValid(event.getSlot())) {
			siteable.onInvalidClick(event);
		}else if(event.getSlot() > 53) {
			if(!siteable.validateInput(event.getSlot(), event.getCurrentItem())) {
				siteable.onInvalidInput(event);
			}
		}else {
			siteable.onValidClick(event);
		}
	}
	
}

package com.avarioncraft.Jobs.skills.mineSkills.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import lombok.Getter;

public class InventoryPage implements InventoryHolder{
	
	public InventoryPage(Player player, int page, int size, String name) {
		this.page = page;
		this.inv = Bukkit.createInventory(player, size, name);
	}
	
	private final Inventory inv;
	@Getter
	private final int page;
	
	@Override
	public Inventory getInventory() {
		return inv;
	}

}

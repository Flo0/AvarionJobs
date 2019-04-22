package com.avarioncraft.Jobs.skills.mineSkills.util;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.avarioncraft.Jobs.util.enums.ItemSortValue;
import com.google.common.collect.Maps;

import lombok.Getter;

public abstract class InventoryContainer {
	
	public InventoryContainer(Player player, int size, int pageSize, String pageName) {
		this.size = size;
		this.inventorys = Maps.newHashMapWithExpectedSize(size);
		
		for(int count = 1; count <= size; count++) {
			this.inventorys.put(count, new InventoryPage(player, count, pageSize, 
					pageName 
					+ ChatColor.GOLD + " [ " 
					+ ChatColor.YELLOW + count 
					+ ChatColor.GOLD + " / " 
					+ ChatColor.YELLOW + size 
					+ ChatColor.GOLD + " ]"));
		}
	}
	
	@Getter
	private final int size;
	@Getter
	private final Map<Integer, InventoryPage> inventorys;
	
	public InventoryPage getPage(int page) {
		return this.inventorys.get(page);
	}
	
	public abstract void sort(ItemSortValue value);
	
}

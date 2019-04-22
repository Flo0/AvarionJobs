package com.avarioncraft.Jobs.util.enums;

import org.bukkit.Material;

import lombok.Getter;

public enum Job {
	
	MINER("Bergarbeiter", 100, Material.IRON_PICKAXE),
	WOODCUTTER("Holzfäller", 100, Material.IRON_AXE),
	SMITH("Schmied", 100, Material.ANVIL),
	CRAFTER("Handwerker", 100, Material.CRAFTING_TABLE),
	HUNTER("Jäger", 100, Material.BOW),
	WITCHER("Hexer", 100, Material.CAULDRON),
	JEWELER("Juwelier", 100, Material.DIAMOND),
	COOK("Koch", 100, Material.FURNACE),
	FARMER("Farmer", 100, Material.IRON_HOE);
	
	@Getter
	private String displayName;
	@Getter
	private int maxLevel;
	@Getter
	private Material icon;
	
	private Job(String displayName, int maxLevel, Material icon) {
		this.displayName = displayName;
		this.maxLevel = maxLevel;
		this.icon = icon;
	}
	
}
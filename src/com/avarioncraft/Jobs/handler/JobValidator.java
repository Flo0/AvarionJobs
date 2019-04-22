package com.avarioncraft.Jobs.handler;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.avarioncraft.Jobs.util.enums.Job;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class JobValidator {
	
	private static final Map<Job, Set<Material>> VALID_MATERIALS;
	
	static {
		Map<Job, Set<Material>> fillMats = Maps.newHashMap();
		
		fillMats.put(Job.MINER, Sets.newHashSet(Arrays.asList(new Material[]{
				Material.STONE,
				Material.DIORITE,
				Material.ANDESITE,
				Material.GRANITE,
				Material.GRAVEL,
				Material.COAL_ORE,
				Material.IRON_ORE,
				Material.GOLD_ORE,
				Material.LAPIS_ORE,
				Material.REDSTONE_ORE,
				Material.DIAMOND_ORE,
				Material.EMERALD_ORE
			})));
		
		VALID_MATERIALS = fillMats;
		
	}
	
	public JobValidator() {
		
	}
	
	public boolean isValidItem(ItemStack item, Job job) {
		if(!this.isValidMaterial(item.getType(), job)) return false;
		return true;
	}
	
	public boolean isValidBlock(Block block, Job job) {
		if(!this.isValidMaterial(block.getType(), job)) return false;
		return true;
	}
	
	public boolean isValidMaterial(Material mat, Job job) {
		if(!VALID_MATERIALS.get(job).contains(mat)) return false;
		return true;
	}
	
}

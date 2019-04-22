package com.avarioncraft.Jobs.handler;

import java.util.Map;

import org.bukkit.Material;

import com.avarioncraft.Jobs.data.JobExpPackage;
import com.avarioncraft.Jobs.util.enums.Job;
import com.avarioncraft.Jobs.util.enums.JobExpSource;
import com.google.common.collect.Maps;

import lombok.Getter;

public class JobExpHandler {
	
	public JobExpHandler() {
		this.addBreak(Material.ACACIA_WOOD, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.BIRCH_WOOD, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.DARK_OAK_WOOD, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.JUNGLE_WOOD, Job.WOODCUTTER, 3.2);
		this.addBreak(Material.OAK_WOOD, Job.WOODCUTTER, 3.5);
		this.addBreak(Material.SPRUCE_WOOD, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.ACACIA_LOG, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.BIRCH_LOG, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.DARK_OAK_LOG, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.JUNGLE_LOG, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.OAK_LOG, Job.WOODCUTTER, 4.0);
		this.addBreak(Material.SPRUCE_LOG, Job.WOODCUTTER, 3.2);
		
		this.addBreak(Material.STONE, Job.MINER, 0.5);
		this.addBreak(Material.ANDESITE, Job.MINER, 0.5);
		this.addBreak(Material.DIORITE, Job.MINER, 0.5);
		this.addBreak(Material.GRANITE, Job.MINER, 0.5);
		
		this.addBreak(Material.COAL_ORE, Job.MINER, 8.0);
		this.addBreak(Material.IRON_ORE, Job.MINER, 20.0);
		this.addBreak(Material.GOLD_ORE, Job.MINER, 90.0);
		this.addBreak(Material.DIAMOND_ORE, Job.MINER, 100.0);
		this.addBreak(Material.LAPIS_ORE, Job.MINER, 90.0);
		this.addBreak(Material.EMERALD_ORE, Job.MINER, 120.0);
		this.addBreak(Material.REDSTONE_ORE, Job.MINER, 30.0);
		this.addBreak(Material.NETHER_QUARTZ_ORE, Job.MINER, 16.0);
		
		this.addBreak(Material.WHEAT, Job.FARMER, 2.0);
		this.addBreak(Material.CARROTS, Job.FARMER, 2.0);
		this.addBreak(Material.POTATOES, Job.FARMER, 2.0);
		this.addBreak(Material.MELON, Job.FARMER, 4.0);
		this.addBreak(Material.PUMPKIN, Job.FARMER, 4.0);
		this.addBreak(Material.CACTUS, Job.FARMER, 1.2);
		this.addBreak(Material.SUGAR_CANE, Job.FARMER, 1.0);
		this.addBreak(Material.BROWN_MUSHROOM, Job.FARMER, 9.0);
		this.addBreak(Material.RED_MUSHROOM, Job.FARMER, 9.0);
		this.addBreak(Material.COCOA, Job.FARMER, 6.0);
		
		this.addBreak(Material.BEETROOT, Job.WITCHER, 3.0);
		this.addBreak(Material.NETHER_WART, Job.WITCHER, 3.5);
	}
	
	@Getter
	private Map<Material, JobExpPackage> blockBreakMap = Maps.newHashMap();
	
	private void addBreak(Material mat, Job job, double exp) {
		this.blockBreakMap.put(mat, new JobExpPackage(job, JobExpSource.BLOCK_BREAK, exp));
	}
}

package com.avarioncraft.Jobs.data;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import com.avarioncraft.Jobs.events.PlayerJobExpEvent;
import com.avarioncraft.Jobs.events.PlayerJobLevelUpEvent;
import com.avarioncraft.Jobs.util.enums.Job;
import com.google.common.collect.Lists;

import lombok.Getter;
import net.crytec.api.itemstack.ItemBuilder;
import net.crytec.api.util.F;

public class JobPackage {
	
	public JobPackage(Player player, Job job, int level, double currentExp, double nextLvlExp) {
		this.player = player;
		this.job = job;
		this.level = level;
		this.currentExp = currentExp;
		this.nextLvlExp = nextLvlExp;
	}
	
	public JobPackage(Player player, Job job) {
		this.player = player;
		this.job = job;
	}
	
	@Getter
	private final Player player;
	@Getter
	private final Job job;
	
	@Getter
	private int level = 1;
	@Getter
	private double currentExp = 0.0;
	@Getter
	private double nextLvlExp = 1000.0;
	
	private void placeNextExp() {
		this.nextLvlExp = (int) 1000 + Math.pow(level, 2.0) + Math.exp((level * 0.1));
	}
	
	public void addExp(JobExpPackage expPack) {
		if(level >= job.getMaxLevel()) return;
		
		PlayerJobExpEvent expEvent = new PlayerJobExpEvent(this.player, expPack);
		Bukkit.getPluginManager().callEvent(expEvent);
		
		if (expEvent.isCancelled()) {
			return;
		}
		
		JobExpPackage e_expPack = expEvent.getExpPack();
		
		if(currentExp + e_expPack.getExp() >= nextLvlExp) {
			PlayerJobLevelUpEvent lvlEvent = new PlayerJobLevelUpEvent(this.player, expPack.getJob(), this.level + 1);
			Bukkit.getPluginManager().callEvent(lvlEvent);
			this.level = lvlEvent.getLevel();
			e_expPack.setExp(this.currentExp + e_expPack.getExp() - this.nextLvlExp);
			this.addExp(e_expPack);
			this.placeNextExp();
		}else {
			this.currentExp += e_expPack.getExp();
		}
		
	}
	
	
	public List<String> getDescription() {
		List<String> desc = Lists.newArrayList();
		desc.add("");
		desc.add("§e" + this.currentExp + " / " + this.nextLvlExp);
		desc.add(F.ProgressBar(this.currentExp, this.nextLvlExp, 20, "▇"));
		return desc;
	}

	
	public String getDisplayName() {
		return "§6" + this.job.getDisplayName() + " §e" + this.getLevel() + " / " + this.job.getMaxLevel();
	}

	
	public ItemStack getIcon() {
		return new ItemBuilder(this.getIconMaterial())
				.name(this.getDisplayName())
				.lore(this.getDescription())
				.setItemFlag(ItemFlag.HIDE_ATTRIBUTES)
				.build();
	}

	
	public Material getIconMaterial() {
		return job.getIcon();
	}
	
}

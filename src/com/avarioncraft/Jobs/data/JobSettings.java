package com.avarioncraft.Jobs.data;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import lombok.Getter;

public class JobSettings {
	
	public JobSettings(Player player) {
		this.jobBar = Bukkit.createBossBar("Job Erfahrung", BarColor.BLUE, BarStyle.SEGMENTED_20);
		this.jobBar.setVisible(false);
		jobBar.addPlayer(player);
	}
	
	@Getter
	private BossBar jobBar;
	
	public Material getBossBarWool() {
		if(jobBar.isVisible()) return Material.GREEN_WOOL;
		return Material.RED_WOOL;
	}
	
	public void toggleUseBossBar() {
		if(jobBar.isVisible()) {
			jobBar.setVisible(false);
			return;
		}
		jobBar.setVisible(true);
	}
}
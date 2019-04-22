package com.avarioncraft.Jobs.recipes;

import java.util.Optional;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.util.enums.Job;

import lombok.Getter;

public abstract class AvarionRecipe extends ShapedRecipe{
	
	public AvarionRecipe(NamespacedKey key, @Nullable Job job, int lvl, double expGain) {
		super(key, new ItemStack(Material.AIR));
		this.job = Optional.ofNullable(job);
		this.reqLvl = lvl;
		this.expGain = expGain;
	}
	
	@Getter
	private final Optional<Job> job;
	@Getter
	private final int reqLvl;
	@Getter
	private final double expGain;
	
	public void onPrepare(PrepareItemCraftEvent event) {
		
		Player player = (Player) event.getView().getPlayer();
		JobPlayer jobPlayer = JobPlayer.of(player);
		
		if(this.job.isPresent() && this.reqLvl > jobPlayer.getJobInfo(this.job.get()).getLevel()) return;
		
		AvarionRecipeCraftEvent ownEvent = new AvarionRecipeCraftEvent(player, this, this.getModified(jobPlayer));
		Bukkit.getPluginManager().callEvent(ownEvent);
		
		if(ownEvent.isCancelled()) return;
		
		event.getInventory().setResult(ownEvent.getExpectedResult());
	}
	
	public abstract ItemStack getModified(JobPlayer jobPlayer);
}

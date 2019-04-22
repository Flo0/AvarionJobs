package com.avarioncraft.Jobs.gui;

import org.bukkit.entity.Player;

import com.avarioncraft.Jobs.core.AvarionJobs;
import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.util.enums.Job;

import net.crytec.api.itemstack.ItemBuilder;
import net.crytec.api.smartInv.ClickableItem;
import net.crytec.api.smartInv.content.InventoryContents;
import net.crytec.api.smartInv.content.InventoryProvider;
import net.crytec.api.smartInv.content.SlotPos;

public class JobMainGUI implements InventoryProvider{
	
	@Override
	public void init(Player player, InventoryContents contents) {
		
		JobPlayer jobPlayer = JobPlayer.of(player);
		
		int count = 0;
		
		for(Job job : Job.values()) {
			
			contents.set(SlotPos.of(1, count), ClickableItem.empty(jobPlayer.getJobInfo(job).getIcon()));
			count++;
		}
		
		contents.set(SlotPos.of(4, 0), ClickableItem.of(new ItemBuilder(jobPlayer.getSettings().getBossBarWool()).name("§eBoss Bar verwenden.").build(), event->{
			jobPlayer.getSettings().toggleUseBossBar();
			AvarionJobs.getPlugin().getGui().open(player);
		}));
		
		
	}
	
	@Override
	public void update(Player player, InventoryContents contents) {
		
	}
	
}
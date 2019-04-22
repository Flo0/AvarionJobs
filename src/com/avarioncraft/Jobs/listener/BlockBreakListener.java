package com.avarioncraft.Jobs.listener;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.avarioncraft.Jobs.data.JobExpPackage;
import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.handler.JobExpHandler;
import com.avarioncraft.Jobs.handler.blocker.BlockedLocation;

import net.crytec.api.util.F;

public class BlockBreakListener implements Listener{
	
	public BlockBreakListener() {
		breakMap = new JobExpHandler().getBlockBreakMap();
	}
	
	private final Map<Material, JobExpPackage> breakMap;
	
	@EventHandler
	public void jobBreak(BlockBreakEvent event) {
		
		if(breakMap.containsKey(event.getBlock().getType())) {
			if(BlockedLocation.isBlocked(event.getBlock().getLocation())) {
				event.getPlayer().sendMessage(F.main("Jobs", "Dieser Block gibt keine EXP mehr..."));
				return;
			}
			JobExpPackage pack = breakMap.get(event.getBlock().getType());
			JobPlayer.of(event.getPlayer()).getJobInfo(pack.getJob()).addExp(pack);
		}
		
	}
	
}
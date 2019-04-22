package com.avarioncraft.Jobs.skills;

import org.bukkit.event.block.BlockBreakEvent;

public interface BlockBreakTrigger {
	
	public void onBlockBreak(BlockBreakEvent event);
	
}

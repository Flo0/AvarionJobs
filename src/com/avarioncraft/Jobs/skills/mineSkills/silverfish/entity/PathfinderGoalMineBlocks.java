package com.avarioncraft.Jobs.skills.mineSkills.silverfish.entity;

import java.util.List;

import org.bukkit.Location;

import net.minecraft.server.v1_13_R2.EntityInsentient;
import net.minecraft.server.v1_13_R2.PathfinderGoal;

public class PathfinderGoalMineBlocks extends PathfinderGoal {

	EntityInsentient entity;
	int step = 0;
	List<Location> blocks; // Todo: Better implementation
	
	public PathfinderGoalMineBlocks(EntityInsentient entityInsentient, List<Location> blocks) {
		this.entity = entityInsentient;
		this.blocks = blocks;
	}
	
	// should execute
	@Override
	public boolean a() {
		
		// Check if blocklist/iterator has next && is solid/breakable block
		
		return false;
	}

	// shouldContinueExecuting
	@Override
	public boolean b() {
		return this.a();
	}

	// startExecuting
	@Override
	public void c() {
		// ToDo: Break block, then walk to
		
		
		// walk to location
		this.entity.getNavigation().a(blocks.get(step).getX(), blocks.get(step).getY(), blocks.get(step).getZ());
		
	}

	// Update Task
	@Override
	public void e() {

	}
	
	// Keine Ahnung was das macht, scheint aber nicht so wichtig zu sein
	@Override
	public boolean f() {
		return false;
	}

}

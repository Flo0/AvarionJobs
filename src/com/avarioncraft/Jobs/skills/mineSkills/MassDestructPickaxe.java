package com.avarioncraft.Jobs.skills.mineSkills;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.handler.JobValidator;
import com.avarioncraft.Jobs.handler.WeaponDamager;
import com.avarioncraft.Jobs.skills.BlockBreakTrigger;
import com.avarioncraft.Jobs.skills.JobSkill;
import com.avarioncraft.Jobs.util.enums.Job;
import com.avarioncraft.Jobs.util.enums.JobSkillTrigger;
import com.google.common.collect.Sets;

import net.crytec.api.util.Cuboid;
import net.crytec.api.util.Cuboid.CuboidDirection;

public class MassDestructPickaxe extends JobSkill implements BlockBreakTrigger {

	public MassDestructPickaxe(JobPlayer player, int enchantLevel) {
		super(player, "Durchbruch", JobSkillTrigger.BLOCK_BREAK, 0);
		this.lvl = enchantLevel;
	}

	private Set<Block> inUse = Sets.newHashSet();
	private final int lvl;
	private final JobValidator validator = new JobValidator();

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (this.inUse.contains(event.getBlock())) return;
		
		Block broken = event.getBlock();
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		
		this.inUse.add(broken);
		
		for (Block bl : this.getBreakeBlocks(event)) {
			if(!this.validator.isValidBlock(bl, Job.MINER)) continue;
			this.inUse.add(bl);
			BlockBreakEvent newBreak = new BlockBreakEvent(bl, event.getPlayer());
			Bukkit.getPluginManager().callEvent(newBreak);
			bl.breakNaturally(item);
		}
		
		new WeaponDamager(item).damage(this.inUse.size(), true);
		
		this.inUse.clear();
		
	}

	@Override
	public void onTrigger() {}
	
	private Set<Block> getBreakeBlocks(BlockBreakEvent event) {

		switch (this.lvl) {
		case 1:
			return this.enchI(event);
		case 2:
			return this.enchII(event);
		case 3:
			return this.enchIII(event);
		case 4:
			return this.enchIV(event);
		default:
			return this.enchN(event);
		}

	}

	private Set<Block> enchI(BlockBreakEvent event) {
		Set<Block> blocks = Sets.newHashSet();
		BlockFace face = event.getPlayer().rayTraceBlocks(6D).getHitBlockFace();
		Block middle = event.getBlock();

		if (face.equals(BlockFace.DOWN) || face.equals(BlockFace.UP)) {
			blocks.add(middle.getRelative(BlockFace.SOUTH));
			blocks.add(middle.getRelative(BlockFace.NORTH));
			blocks.add(middle.getRelative(BlockFace.EAST));
			blocks.add(middle.getRelative(BlockFace.WEST));
		} else if (face.equals(BlockFace.SOUTH) || face.equals(BlockFace.NORTH)) {
			blocks.add(middle.getRelative(BlockFace.UP));
			blocks.add(middle.getRelative(BlockFace.DOWN));
			blocks.add(middle.getRelative(BlockFace.EAST));
			blocks.add(middle.getRelative(BlockFace.WEST));
		} else if (face.equals(BlockFace.EAST) || face.equals(BlockFace.WEST)) {
			blocks.add(middle.getRelative(BlockFace.SOUTH));
			blocks.add(middle.getRelative(BlockFace.NORTH));
			blocks.add(middle.getRelative(BlockFace.UP));
			blocks.add(middle.getRelative(BlockFace.DOWN));
		}

		return blocks;
	}

	private Set<Block> enchII(BlockBreakEvent event) {
		Set<Block> blocks = Sets.newHashSet();
		BlockFace face = event.getPlayer().rayTraceBlocks(6D).getHitBlockFace();
		Location middle = event.getBlock().getLocation().clone().add(0.5,0.5,0.5);
		Cuboid cube;

		if (face.equals(BlockFace.DOWN) || face.equals(BlockFace.UP)) {
			cube = new Cuboid(middle.clone().add(1D, 0D, 1D), middle.clone().add(-1D, 0D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		} else if (face.equals(BlockFace.SOUTH) || face.equals(BlockFace.NORTH)) {
			cube = new Cuboid(middle.clone().add(1D, 1D, 0D), middle.clone().add(-1D, -1D, 0D));
			return Sets.newHashSet(cube.getBlocks());
		} else if (face.equals(BlockFace.EAST) || face.equals(BlockFace.WEST)) {
			cube = new Cuboid(middle.clone().add(0D, 1D, 1D), middle.clone().add(0D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		}

		return blocks;
	}

	private Set<Block> enchIII(BlockBreakEvent event) {
		BlockFace face = event.getPlayer().rayTraceBlocks(6D).getHitBlockFace();
		Location middle = event.getBlock().getLocation().clone().add(0.5,0.5,0.5);
		Cuboid cube;

		switch (face) {
		case DOWN:
			cube = new Cuboid(middle.clone().add(1D, 0D, 1D), middle.clone().add(-1D, 1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case UP:
			cube = new Cuboid(middle.clone().add(1D, 0D, 1D), middle.clone().add(-1D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case NORTH:
			cube = new Cuboid(middle.clone().add(1D, 1D, 0D), middle.clone().add(-1D, -1D, 1D));
			return Sets.newHashSet(cube.getBlocks());
		case SOUTH:
			cube = new Cuboid(middle.clone().add(1D, 1D, 0D), middle.clone().add(-1D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case EAST:
			cube = new Cuboid(middle.clone().add(0D, 1D, 1D), middle.clone().add(-1D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case WEST:
			cube = new Cuboid(middle.clone().add(0D, 1D, 1D), middle.clone().add(1D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		default:
			return Sets.newHashSet();
		}
	}

	private Set<Block> enchIV(BlockBreakEvent event) {
		BlockFace face = event.getPlayer().rayTraceBlocks(6D).getHitBlockFace();
		Location middle = event.getBlock().getLocation().clone().add(0.5,0.5,0.5);
		Cuboid cube;

		switch (face) {
		case DOWN:
			cube = new Cuboid(middle.clone().add(1D, 0D, 1D), middle.clone().add(-1D, 2D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case UP:
			cube = new Cuboid(middle.clone().add(1D, 0D, 1D), middle.clone().add(-1D, -2D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case NORTH:
			cube = new Cuboid(middle.clone().add(1D, 1D, 0D), middle.clone().add(-1D, -1D, 2D));
			return Sets.newHashSet(cube.getBlocks());
		case SOUTH:
			cube = new Cuboid(middle.clone().add(1D, 1D, 0D), middle.clone().add(-1D, -1D, -2D));
			return Sets.newHashSet(cube.getBlocks());
		case EAST:
			cube = new Cuboid(middle.clone().add(0D, 1D, 1D), middle.clone().add(-2D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		case WEST:
			cube = new Cuboid(middle.clone().add(0D, 1D, 1D), middle.clone().add(2D, -1D, -1D));
			return Sets.newHashSet(cube.getBlocks());
		default:
			return Sets.newHashSet();
		}

	}

	private Set<Block> enchN(BlockBreakEvent event) {
		Location middle = event.getBlock().getLocation().clone().add(0.5,0.5,0.5);
		Cuboid cube = new Cuboid(middle);
		cube.expand(CuboidDirection.Both, this.lvl);
		return Sets.newHashSet(cube.getBlocks());
	}
}

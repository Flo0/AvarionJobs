package com.avarioncraft.Jobs.skills.mineSkills.silverfish.entity;

import java.lang.reflect.Field;
import java.util.Set;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.DamageSource;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.EntitySilverfish;
import net.minecraft.server.v1_13_R2.Item;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_13_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_13_R2.PathfinderGoalSelector;
import net.minecraft.server.v1_13_R2.World;

public class SilverBreaker extends EntitySilverfish {

	public SilverBreaker(Player owner, World world) {
		super(world);
		
		this.setPosition(owner.getLocation().getX(), owner.getLocation().getY(), owner.getLocation().getZ());
		this.setLocation(owner.getLocation().getX(), owner.getLocation().getY(), owner.getLocation().getZ(), owner.getLocation().getYaw(), owner.getLocation().getPitch());
		
		// No Fire Damage
		this.fireProof = true;
		
		this.setCustomNameVisible(true);
		
        Set<?> goalB = (Set<?>)   getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
        Set<?> goalC = (Set<?>)   getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        Set<?> targetB = (Set<?>) getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        Set<?> targetC = (Set<?>) getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        
        goalSelector.a(0 , new PathfinderGoalMineBlocks(this, null));
        goalSelector.a(1 , new PathfinderGoalFloat(this));
        goalSelector.a(2 , new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 2.5F));
		
	}
	
	@Override
	public boolean damageEntity(DamageSource dmg, float f) {
		return false; // Not damagable
	}

	@Override
	protected void burnFromLava() {
		// Fix lava c:
		return;
	}

	@Override
	public void setOnFire(int i) {
		// Fix fire
		return;
	}

	@Override
	protected Item getLoot() {
		return null;
	}

	@Override
	protected void dropDeathLoot(boolean arg0, int arg1) {
		return;
	}

	// Do nothing with NBT
	// Never store custom entity data inside chunk files/nbt.
	// Bad things will happen if you do.
	@Override
	public void b(NBTTagCompound nbttagcompound) {
	}

	@Override
	public boolean c(NBTTagCompound nbttagcompound) {
		return false;
	}

	@Override
	public void a(NBTTagCompound nbttagcompound) {
	}

	@Override
	public boolean d(NBTTagCompound nbttagcompound) {
		return false;
	}
	
	
	
	// private reflection stuff

	private static Object getPrivateField(String field, Class<?> clazz, Object object) {
		Field f;
		Object o = null;

		try {

			f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			o = f.get(object);

		} catch (NoSuchFieldException | IllegalAccessException ex) {
			ex.printStackTrace();
		}

		return o;

	}

}

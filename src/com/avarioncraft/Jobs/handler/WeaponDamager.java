package com.avarioncraft.Jobs.handler;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponDamager {
	
	public WeaponDamager(ItemStack item) {
		this.item = item;
		this.dmg = 0;
		if(item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
			this.lvl = item.getItemMeta().getEnchantLevel(Enchantment.DURABILITY);
		}else {
			this.lvl = 0;
		}
	}
	
	private final ItemStack item;
	private final int lvl;
	private int dmg;
	
	public ItemStack damage(int actions, boolean isNatural) {
		
		if(item.getType().equals(Material.AIR)) return item;
		
		Damageable dmgbl;
		
		if(lvl <= 0) {
			dmgbl = ((Damageable) item.getItemMeta());
			dmgbl.setDamage(dmgbl.getDamage() + 1);
			
			item.setItemMeta((ItemMeta)dmgbl);
			return item;
		}
		
		if(actions <= 8) {
			for(int trys = 0; trys < actions; trys++) {
				if(this.useDurability(this.lvl)) {
					dmg++;
				}
			}
		}else {
			dmg = (int) (actions * (1.0 / (this.lvl + 1)));
		}
		
		dmgbl = ((Damageable) item.getItemMeta());
		dmgbl.setDamage(dmgbl.getDamage() + this.dmg);
		
		item.setItemMeta((ItemMeta)dmgbl);
		return this.item;
	}
	
	private boolean useDurability(int durabilityLvl) {

		if(ThreadLocalRandom.current().nextFloat() >= (1.0 / (durabilityLvl + 1))) {
			return false;
		}
		
		return true;
	}
	
}

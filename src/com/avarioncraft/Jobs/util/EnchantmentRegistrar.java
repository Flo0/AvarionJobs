package com.avarioncraft.Jobs.util;

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentRegistrar {

	public EnchantmentRegistrar() {

		try {
			Field accepting = Enchantment.class.getDeclaredField("acceptingNew");
			accepting.setAccessible(true);
			accepting.set(null, true);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	public void registerEnchantment(Enchantment enchantment) {
		Enchantment.registerEnchantment(enchantment);
	}
	
	
	public void stopAcceptingNewRegistrations() {
		Enchantment.stopAcceptingRegistrations();
		try {
			Field accepting = Enchantment.class.getDeclaredField("acceptingNew");
			accepting.setAccessible(false);
			accepting.set(null, true);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}

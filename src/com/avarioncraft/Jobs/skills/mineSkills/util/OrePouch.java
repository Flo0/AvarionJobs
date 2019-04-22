package com.avarioncraft.Jobs.skills.mineSkills.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.avarioncraft.Jobs.data.JobPlayer;
import com.avarioncraft.Jobs.events.PersOpenEvent;
import com.avarioncraft.Jobs.events.PersPageChangeEvent;
import com.avarioncraft.Jobs.handler.JobValidator;
import com.avarioncraft.Jobs.util.enums.ItemSortValue;
import com.avarioncraft.Jobs.util.enums.Job;

import net.crytec.api.itemstack.ItemBuilder;

public class OrePouch extends SiteablePersistentInventory {

	private static final JobValidator VALIDATE = new JobValidator();
	
	public OrePouch(JobPlayer player) {
		super(player.getPlayer(), (int) (player.getJobInfo(Job.MINER).getLevel() / 20), "Erzbeutel", 4);
		this.fill();
		super.add(player.getPlayer(), this);
	}
	
	public OrePouch(JobPlayer player, int size) {
		super(player.getPlayer(), size, "Erzbeutel", 4);
		this.fill();
		super.add(player.getPlayer(), this);
	}
	
	public OrePouch(JobPlayer player, ConfigurationSection section, int size) {
		super(player.getPlayer(), size, "Erzbeutel", 4);
		this.fill();
		
		section.getKeys(false).forEach(invKey->{
			Inventory inv = super.getPage(Integer.parseInt(invKey)).getInventory();
			section.getConfigurationSection(invKey).getKeys(false).forEach(slotKey->{
				ItemStack item = section.getItemStack(invKey + "." + slotKey);
				inv.setItem(Integer.parseInt(slotKey), item);
			});
		});
		
		super.add(player.getPlayer(), this);
	}
	
	public OrePouch(JobPlayer player, ConfigurationSection section) {
		super(player.getPlayer(), (int) (player.getJobInfo(Job.MINER).getLevel() / 20), "Erzbeutel", 4);
		this.fill();
		
		section.getKeys(false).forEach(invKey->{
			Inventory inv = super.getPage(Integer.parseInt(invKey)).getInventory();
			section.getConfigurationSection(invKey).getKeys(false).forEach(slotKey->{
				ItemStack item = section.getItemStack(invKey + "." + slotKey);
				inv.setItem(Integer.parseInt(slotKey), item);
			});
		});
		
		super.add(player.getPlayer(), this);
	}

	@Override
	public void onOpen(PersOpenEvent event) {}

	@Override
	public void onPageChange(PersPageChangeEvent event) {}

	@Override
	public void sort(ItemSortValue value) {}

	@Override
	public void onValidClick(InventoryClickEvent event) {}

	@Override
	public void onInvalidClick(InventoryClickEvent event) {
		event.setCancelled(true);
		int slot = event.getSlot();
		if(slot == 45) {
			if(this.getUsedPageNumber() == 1) return;
			this.openPage(this.getUsedPageNumber() - 1);
		}else if(slot == 53) {
			if(this.getUsedPageNumber() == this.getSize()) return;
			this.openPage(this.getUsedPageNumber() + 1);
		}
		
	}
	
	@Override
	public boolean validateInput(int slot, ItemStack item) {
		if(VALIDATE.isValidItem(item, Job.MINER)) return true;
		return false;
	}
	
	@Override
	public void onInvalidInput(InventoryClickEvent event) {
		event.setCancelled(true);
	}

	private final void fill() {
		ItemStack filler = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build();
		for (int count = 1; count <= this.getSize(); count++) {
			Inventory inv = this.getPage(count).getInventory();
			
			if (count == 1) {
				for (int sp = 45; sp < 53; sp++) {
					inv.setItem(sp, filler);
				}
				inv.setItem(53, new ItemBuilder(Material.ARROW)
						.name("§6Zur Seite §e" + (count + 1) + " §6/ §e" + this.getSize()).build());
			} else if (count > 1 && count < this.getSize()) {
				for (int sp = 46; sp < 53; sp++) {
					inv.setItem(sp, filler);
				}
				inv.setItem(45, new ItemBuilder(Material.ARROW)
						.name("§6Zur Seite §e" + (count - 1) + " §6/ §e" + this.getSize()).build());
				inv.setItem(53, new ItemBuilder(Material.ARROW)
						.name("§6Zur Seite §e" + (count + 1) + " §6/ §e" + this.getSize()).build());
			} else if (count == this.getSize()) {
				for (int sp = 46; sp < 54; sp++) {
					inv.setItem(sp, filler);
				}
				inv.setItem(45, new ItemBuilder(Material.ARROW)
						.name("§6Zur Seite §e" + (count - 1) + " §6/ §e" + this.getSize()).build());
			}
			
		}
	}


}

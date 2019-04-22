package com.avarioncraft.Jobs.skills.mineSkills.util;

import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.avarioncraft.Jobs.events.PersOpenEvent;
import com.avarioncraft.Jobs.events.PersPageChangeEvent;
import com.avarioncraft.Jobs.fileIO.JobFiles;
import com.google.common.collect.Maps;

import lombok.Getter;

public abstract class SiteablePersistentInventory extends InventoryContainer{

	public SiteablePersistentInventory(Player player, int size, String pageName, int validLines) {
		super(player, size, 54, pageName);
		this.validLines = validLines;
		this.owner = player;
	}
	
	public static void saveAll() {
		FileConfiguration config = JobFiles.get().getOrePouchConfig();
		
		playerPersInventorys.forEach((player, siteable)->{
			String plid = player.getUniqueId().toString();
			siteable.getInventorys().forEach((id,page)->{
				Inventory inv = page.getInventory();
				for(int cslot = 0; cslot <= 45; cslot++) {
					config.set(plid + ".Inventory_" + id + ".Slot_" + cslot, inv.getItem(cslot));
				}
			});
		});
		
		try {
			JobFiles.get().saveConfigurations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Map<Player, SiteablePersistentInventory> playerPersInventorys = Maps.newHashMap();
	
	public static void add(Player player, SiteablePersistentInventory siteable) {
		playerPersInventorys.put(player, siteable);
	}
	
	public static SiteablePersistentInventory of(Player player) {
		return playerPersInventorys.get(player);
	}
	
	private final Player owner;
	private final int validLines;
	@Getter
	private int usedPageNumber;
	
	public boolean isSlotValid(int slotID) {
		if(slotID > (validLines * 9) && slotID < 54) return false;
		return true;
	}
	
	public void openPage(int page) {
		PersOpenEvent event = new PersOpenEvent(this.owner, this, page);
		Bukkit.getPluginManager().callEvent(event);
		this.usedPageNumber = page;
		this.onOpen(event);
		this.owner.openInventory(this.getPage(page).getInventory());
	}
	
	public void changePage(int to) {
		PersPageChangeEvent event = new PersPageChangeEvent(this.owner, this, this.usedPageNumber, to);
		this.usedPageNumber = to;
		this.onPageChange(event);
		this.owner.openInventory(this.getPage(to).getInventory());
	}
	
	public InventoryPage getUsedPage() {
		return this.getPage(this.usedPageNumber);
	}
	
	public abstract void onValidClick(InventoryClickEvent event);
	public abstract void onInvalidClick(InventoryClickEvent event);
	
	public abstract boolean validateInput(int slot, ItemStack item);
	public abstract void onInvalidInput(InventoryClickEvent event);
	
	public abstract void onOpen(PersOpenEvent event);
	public abstract void onPageChange(PersPageChangeEvent event);
	
}

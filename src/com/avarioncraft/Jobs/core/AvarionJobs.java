package com.avarioncraft.Jobs.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.avarioncraft.Jobs.commands.JobCommands;
import com.avarioncraft.Jobs.fileIO.JobFiles;
import com.avarioncraft.Jobs.gui.JobMainGUI;
import com.avarioncraft.Jobs.listener.BlockBreakListener;
import com.avarioncraft.Jobs.listener.CraftListener;
import com.avarioncraft.Jobs.listener.ExpEventListener;
import com.avarioncraft.Jobs.listener.ExpModificationListener;
import com.avarioncraft.Jobs.listener.JobSkillListener;
import com.avarioncraft.Jobs.listener.JoinListener;
import com.avarioncraft.Jobs.listener.PersistentInventoryListener;
import com.avarioncraft.Jobs.skills.mineSkills.util.SiteablePersistentInventory;
import com.avarioncraft.Jobs.threads.LocationCleanUp;

import lombok.Getter;
import net.crytec.api.devin.commands.CommandRegistrar;
import net.crytec.api.smartInv.SmartInventory;

public class AvarionJobs extends JavaPlugin {
	
	@Getter
	private static AvarionJobs plugin;
	
	@Getter
	private CommandRegistrar commandRegistrar;
	
	@Getter
	private SmartInventory gui = SmartInventory.builder().provider(new JobMainGUI()).title("Berufe").size(5, 9).build();
	
	@Getter
	private JobFiles files;
	
	@Override
	public void onLoad() {
		AvarionJobs.plugin = this;
	}
	
	@Override
	public void onEnable() {
		this.commandRegistrar = new CommandRegistrar(this);
		this.getCommandRegistrar().registerCommands(new JobCommands());
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new ExpEventListener(), this);
		Bukkit.getPluginManager().registerEvents(new ExpModificationListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new JobSkillListener(), this);
		Bukkit.getPluginManager().registerEvents(new PersistentInventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new CraftListener(), this);
		
		Bukkit.getScheduler().runTaskTimer(this, new LocationCleanUp(), 20, 20);
		
		this.files = JobFiles.get();
	//	EnchantmentRegistrar reg = new EnchantmentRegistrar();
		
		
		
		
	}
	
	@Override
	public void onDisable() {
		SiteablePersistentInventory.saveAll();
	}
}
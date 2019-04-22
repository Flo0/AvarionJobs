package com.avarioncraft.Jobs.fileIO;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.avarioncraft.Jobs.core.AvarionJobs;

import lombok.Getter;

public class JobFiles {
	
	private JobFiles() {
		this.jobPlayerFile = new File(AvarionJobs.getPlugin().getDataFolder() + File.separator + "PlayerJobs.yml");
		this.orePouchFile = new File(AvarionJobs.getPlugin().getDataFolder() + File.separator + "Erzbeutel.yml");
		this.orePouchConfig = YamlConfiguration.loadConfiguration(this.orePouchFile);
		this.jobPlayerConfig = YamlConfiguration.loadConfiguration(this.jobPlayerFile);
		this.mkdirs();
	}
	
	private static JobFiles instance;
	
	public static final JobFiles get() {
		if(instance == null) {
			instance = new JobFiles();
		}
		return instance;
	}
	
	private final File orePouchFile;
	private final File jobPlayerFile;
	@Getter
	private final FileConfiguration orePouchConfig;
	@Getter
	private final FileConfiguration jobPlayerConfig;
	
	public final void saveConfigurations() throws IOException {
		this.orePouchConfig.save(this.orePouchFile);
		this.jobPlayerConfig.save(this.jobPlayerFile);
	}
	
	private final void mkdirs() {
		
	}
}

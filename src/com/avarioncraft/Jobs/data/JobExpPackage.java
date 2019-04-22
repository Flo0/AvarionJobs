package com.avarioncraft.Jobs.data;

import com.avarioncraft.Jobs.util.enums.Job;
import com.avarioncraft.Jobs.util.enums.JobExpSource;

import lombok.Getter;
import lombok.Setter;

public class JobExpPackage {

	public JobExpPackage(Job job, JobExpSource source, double exp) {
		this.job = job;
		this.source = source;
		this.exp = exp;
	}
	
	@Getter
	private final Job job;
	@Getter
	private final JobExpSource source;
	@Getter @Setter
	private double exp;
	
}

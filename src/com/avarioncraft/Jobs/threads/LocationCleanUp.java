package com.avarioncraft.Jobs.threads;

import com.avarioncraft.Jobs.handler.blocker.BlockedLocation;

public class LocationCleanUp implements Runnable{
	
	public LocationCleanUp() {
		System.out.println("&f[AvarionJobs] Starting Location CleanUp Thread.");
	}
	
	@Override
	public void run() {
		
		BlockedLocation.cleanUp();
		
	}

}

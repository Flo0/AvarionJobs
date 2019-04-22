package com.avarioncraft.Jobs.handler.blocker;

import java.util.Queue;
import java.util.Set;

import org.bukkit.Location;

import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

public class BlockedLocation {
	
	private static final long blockTime = 10000;
	
	private BlockedLocation(Location loc) {
		
		this.location = loc;
		this.timestamp = System.currentTimeMillis() + blockTime;
		
	}
	
	//Static
	private static Queue<BlockedLocation> locMem = Queues.newArrayDeque();
	private static Set<Location> blockedLocations = Sets.newHashSet();
	
	public static boolean isBlocked(Location loc) {
		return blockedLocations.contains(loc);
	}
	
	public static void add(Location loc) {
		blockedLocations.add(loc);
		locMem.add(new BlockedLocation(loc));
	}
	
	public static void cleanUp() {
		BlockedLocation loc;
		long start = System.currentTimeMillis();
		
		while(!locMem.isEmpty()) {
			
			if(System.currentTimeMillis() - start > 50) {
				System.out.println("§c!--- Blocked Location Memory was too big. ---!");
				break;
			}
			
			loc = locMem.peek();
			if(loc.isDone()) {
				locMem.remove(loc);
				blockedLocations.remove(loc.location);
				return;
			}else {
				break;
			}
		}
	}
	
	//Dynamic
	public final Location location;
	private final long timestamp;
	
	public boolean isDone() {
		return this.timestamp < System.currentTimeMillis();
	}
	
}
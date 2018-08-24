package com.example.core;

import java.util.Hashtable;
import java.util.Map;

public class ParkingFloorBean {
	
	// Private instance variable for parking floor.
	private static final Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();	
	private ParkingFloorBean(){		
	}
	
	public static synchronized Map<Integer, Car> getFloorMap(){
		return parkingFloor;
	}

}

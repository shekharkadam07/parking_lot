package com.example.util;

import java.io.BufferedReader;
import java.util.Map;

import com.example.core.Car;

public interface IParkingUtility {

	public void park(Map<Integer, Car> parkingFloor, Car car);
	
	public void leave(Map<Integer, Car> parkingFloor, String slotNumber);

	public void status(Map<Integer, Car> parkingFloor);

	public void createParkingLot(int size);

	public BufferedReader createReadBuffer(String fileName);	

	public void findSlotsRegNumByColor(Map<Integer, Car> parkingFloor,
			String string, String slotnumCmd);

	public void findSlotByRegistrationNum(Map<Integer, Car> parkingFloor,
			Car car3);

}

package com.example.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.example.constant.UtilityConstant;
import com.example.core.Car;

/**
 * This is business utility class, parking operational business logic
 * implemented
 * 
 * @author go-jek
 * 
 */
@Component
public class ParkingUtility implements IParkingUtility {

	private static int lotSize;

	/**
	 * This method used to park new car
	 * 
	 * @param parkingFloor
	 * @param car
	 */
	@Override
	public synchronized void park(Map<Integer, Car> parkingFloor, Car car) {

		int slotNumber = generateSlotNumber(parkingFloor);
		if (slotNumber != 0) {

			parkingFloor.put(slotNumber, car);
			System.out.format(UtilityConstant.ALLOCATED_SLOT_NUM_MSG,
					String.valueOf(slotNumber));
		}

	}

	/**
	 * This is method for remove your car while leaving
	 * 
	 * @param parkingFloor
	 * @param slotNumber
	 */
	@Override
	public synchronized void leave(Map<Integer, Car> parkingFloor, String slotNumber) {
		int slotNum = 0;
		try{
			slotNum =  Integer.parseInt(slotNumber);
		}catch(Exception e){
			System.out.format(UtilityConstant.PLS_ENT_VLD_SLOT_NUM);
			return;
		}

		if (slotNum <= ParkingUtility.lotSize) {
			parkingFloor.remove(slotNum);
			System.out.format(UtilityConstant.FREE_SLOT_NUM_MSG, slotNum);
		} else {
			System.out.format(UtilityConstant.BEYOND_LOT_SIZE_MSG, slotNum,
					ParkingUtility.lotSize);
		}

	}

	/**
	 * This method shows current status of parking lot in tabular format
	 * 
	 * @param parkingFloor
	 */
	@Override
	public void status(Map<Integer, Car> parkingFloor) {

		Set<Integer> keySet = parkingFloor.keySet();
		if (keySet.size() == 0)
			System.out
					.println(UtilityConstant.PLEASE_INITIALIZE_PARKING_LOT_MSG);
		else
			System.out.println(UtilityConstant.STATUS_HEADING);

		Set<Integer> mySortedSet = new TreeSet<Integer>(keySet);
		
		for (int slot : mySortedSet)
			System.out.println(slot + "		"
					+ parkingFloor.get(slot));
	}

	/**
	 * This method create parking lot of given size
	 * 
	 * @param size
	 */
	@Override
	public synchronized void createParkingLot(int size) {

		if (ParkingUtility.lotSize == 0) {
			ParkingUtility.lotSize = size;
			System.out
					.format(UtilityConstant.CREATED_PARKING_SLOT_MSG, ParkingUtility.lotSize);
		} else {
			System.out.format(UtilityConstant.ALRD_CRTD_PARKING_FLOOR_MSG, ParkingUtility.lotSize);
		}
	}

	/**
	 * This method assign free slot number for new car
	 * 
	 * @param parkingFloor
	 * @return
	 */
	private synchronized int generateSlotNumber(Map<Integer, Car> parkingFloor) {

		Set<Integer> slotSet = parkingFloor.keySet();
		int slotNumber = 0;

		if (parkingFloor.size() < ParkingUtility.lotSize) {

			int tempSlotNum = 0;
			while (tempSlotNum < ParkingUtility.lotSize) {
				tempSlotNum = tempSlotNum + 1;
				if (!slotSet.contains(tempSlotNum)) {
					slotNumber = tempSlotNum;
					break;
				}
			}

		} else if (ParkingUtility.lotSize == 0) {
			System.out
					.println(UtilityConstant.PLEASE_INITIALIZE_PARKING_LOT_MSG);
		} else {
			System.out.println(UtilityConstant.PARKING_LOT_FULL_MSG);
		}

		return slotNumber;
	}

	/**
	 * This method finds car registration or slot number number by given color
	 * the requirement param distinguishes for slot or registration number
	 * 
	 * @param parkingFloor
	 * @param color
	 * @param requirment
	 */
	@Override
	public void findSlotsRegNumByColor(Map<Integer, Car> parkingFloor,
			String color, String requirment) {
		Set<String> requiredSet = new HashSet<String>();
		Set<Integer> keySet = parkingFloor.keySet();
		for (int slot : keySet) {
			Car carTemp = parkingFloor.get(slot);
			if (color.equalsIgnoreCase(carTemp.getCarColor())) {
				if (requirment.equalsIgnoreCase(UtilityConstant.SLOTNUM_CMD))
					requiredSet.add(String.valueOf(slot));
				if (requirment.equalsIgnoreCase(UtilityConstant.REGNUM_CMD))
					requiredSet.add(carTemp.getCarRegNumber());
			} // End of color if
		}
		if (requiredSet.size() == 0)
			System.out.println(UtilityConstant.NOT_FOUND_MSG);
		else
			System.out.println(requiredSet);
	}

	/**
	 * This method finds car slot number by given registration number.
	 * 
	 * @param parkingFloor
	 * @param carToSearch
	 */
	@Override
	public void findSlotByRegistrationNum(Map<Integer, Car> parkingFloor,
			Car carToSearch) {
		boolean carPresent = parkingFloor.containsValue(carToSearch);

		if (carPresent) {
			Set<Integer> keySet = parkingFloor.keySet();
			int slotNumber = 0;
			for (int slot : keySet) {
				if (parkingFloor.get(slot).equals(carToSearch)) {
					slotNumber = slot;
					break;
				}
			}
			System.out.println(slotNumber);

		} else
			System.out.println(UtilityConstant.NOT_FOUND_MSG);

	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	@Override
	public BufferedReader createReadBuffer(String fileName) {

		// 2.0.1 read text file
		FileReader fileReader = null;

		if (fileName != null && fileName.length() > 0) {
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				fileReader = new FileReader(classLoader.getResource(fileName)
						.getFile());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return null;
			}
			// 2.0.2 read text file- FileReader in BufferedReader.
			return new BufferedReader(fileReader);

		}

		// 2.0.2 Initialize string buffer for standard user input
		return new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public void invalidCommand(String command) {
		System.out.println(UtilityConstant.INVALID_INPUT_COMMAND);		
	}

	/*public static void main(String[] args) {
		//ParkingUtility.lotSize

		ParkingUtility parkingUtility = new ParkingUtility();
		
		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		Car car = new Car("0-TEST-0000", "blue");
		parkingUtility.park(parkingFloor, car);
	}*/
	
}

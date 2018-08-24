package com.example.service;

import java.io.BufferedReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.constant.UtilityConstant;
import com.example.core.Car;
import com.example.core.Command;
import com.example.core.ParkingFloorBean;
import com.example.util.ICommandUtility;
import com.example.util.IParkingUtility;

@Service
public class ParkingService implements IParkingService{
	
	@Autowired
	IParkingUtility parkingUtility;
	
	@Autowired
	ICommandUtility commandUtility;
	
	Map<Integer, Car> parkingFloor = ParkingFloorBean.getFloorMap();

	public BufferedReader createReadBuffer(String fileName) {
		return parkingUtility.createReadBuffer(fileName);
	}

	public void processCommand(String commandLine) {
		
		Command cmd = commandUtility.processCommandLine(commandLine);
		String command = cmd.getCommand();
			
		
		if (command.equalsIgnoreCase(UtilityConstant.CREATE_PARKING_LOT_CMD))
			parkingUtility.createParkingLot(Integer.parseInt(cmd.getArg1()));
		else if (command.equalsIgnoreCase(UtilityConstant.PARK_COMMAND)){
			// Add code to validate arg1 and arg2 both should not be null
			Car car = commandUtility.createCar(cmd.getArg1(), cmd.getArg2());
			if(car==null)
				return;
			parkingUtility.park(parkingFloor, car);
		}
		else if (command.equalsIgnoreCase(UtilityConstant.LEAVE_COMMAND))
			parkingUtility.leave(parkingFloor, cmd.getArg1());
		else if (command.equalsIgnoreCase(UtilityConstant.STATUS))
			parkingUtility.status(parkingFloor);
		else if (command
				.equalsIgnoreCase(UtilityConstant.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_CMD)){
			// Add code to validate arg1 (reg_num), here arg2 will be null 
			Car car = commandUtility.createCar(cmd.getArg1());
			if(car==null)
				return;
			parkingUtility.findSlotByRegistrationNum(parkingFloor, car);
		}
		else if (command.equalsIgnoreCase(UtilityConstant.SLOTNUM_CMD)
				|| command.equalsIgnoreCase(UtilityConstant.REGNUM_CMD)){
			// Add code to validate arg1 (color), here arg2 will be null
			parkingUtility.findSlotsRegNumByColor(parkingFloor, cmd.getArg1(), command);
		}
		else{
			parkingUtility.invalidCommand(command);
		}
		

	}// End of function : processCommand

}

package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.constant.UtilityConstant;
import com.example.service.IParkingService;

@Controller
public class ParkingLotController {

	@Autowired
	IParkingService parkingService;

	public BufferedReader readUserInput(String fileName) {
		// 3 create Buffered Reader
		return parkingService.createReadBuffer(fileName);
	}

	/**
	 * 
	 * @param br
	 * @param fileName
	 */
	public void readProcessUsrInptByFileOrIO(BufferedReader br, String fileName) {

		String commandLine = null;

		//1. User input reading logic
		try {
			while ((commandLine = br.readLine()) != null) {
				if (commandLine.equalsIgnoreCase(UtilityConstant.PROGRAM_EXIT))
					break;
				
				// 4.1 process the command
				parkingService.processCommand(commandLine);
				if (fileName.length() == 0)
					System.out.print(UtilityConstant.ASK_FOR_INPUT_MSG);
			}

			// 2. close buffered reader
			br.close();

		} catch (IOException e1) {
			e1.printStackTrace();

		} // End of while : User input reading logic
	}
}

package com.example.parkinglot;

import java.io.BufferedReader;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.constant.UtilityConstant;
import com.example.controller.ParkingLotController;

/**
 * <h1>Hello, ParkingLotApplication!</h1>
 * This is main class for Parking lot Application
 * 
 * @author go-jek 
 */
@Component
@Configuration
@ComponentScan("com.example")
public class ParkingLotApplication {

	public static void main(String[] args) {
		
		//Spring Boot
		ApplicationContext context = SpringApplication.run(ParkingLotApplication.class, args);
		//Spring 4.3
		/*@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ParkingLotApplication.class);
		*/
		ParkingLotController parkingLotController = context.getBean(ParkingLotController.class);

		// 2.0 read file name string
		String fileName = "";
		for(String flName:args){
			fileName = flName;
		}
		
		// 3 create Buffered Reader
		BufferedReader br = parkingLotController.readUserInput(fileName);		

		if(fileName.length()==0)
			System.out.print(UtilityConstant.ASK_FOR_INPUT_MSG);
		
		parkingLotController.readProcessUsrInptByFileOrIO(br, fileName);
				
	} // End of main : ParkingLotApplication
	
} // End of class : ParkingLotApplication


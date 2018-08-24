package com.example.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.core.Command;
import com.example.parkinglot.ParkingLotApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ParkingLotApplication.class})
public class CommandUtilityTest {

	@Autowired
	ICommandUtility commandUtility;
	
	@Test
	public void processCommandLineTest(){

		commandUtility = new CommandUtility();
		
		String commandLine = "park KA-01-HH-1234 White";
		Command c1 = commandUtility.processCommandLine(commandLine);

		// Test command
		Assert.assertEquals(c1.getCommand(), "park");
		//Test argument 1
		Assert.assertEquals(c1.getArg1(), "KA-01-HH-1234");
		
		commandLine = "create_parking_lot 8";
		Command c2 = commandUtility.processCommandLine(commandLine);
		
		// Test command
		Assert.assertEquals(c2.getCommand(), "create_parking_lot");
		//Test argument 1
		Assert.assertEquals(c2.getArg1(), "8");
		
		//System.out.println("This is test707 ");
		
		
		
	}

}

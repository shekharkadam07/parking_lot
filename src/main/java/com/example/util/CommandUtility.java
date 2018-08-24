package com.example.util;

import org.springframework.stereotype.Component;

import com.example.core.Car;
import com.example.core.Command;

@Component
public class CommandUtility implements ICommandUtility{

	@Override
	public Command processCommandLine(String commandLine){
		
		String commandArray[] = commandLine.split(" ");
		String command = "";
		String arg1 = null;
		String arg2 = null;

		if (commandArray.length >= 1)
			command = commandArray[0];
		if (commandArray.length >= 2)
			arg1 = commandArray[1];
		if (commandArray.length >= 3)
			arg2 = commandArray[2];
		
		return new Command(command, arg1, arg2);
	}

	@Override
	public Car createCar(String regNum, String color) {
		if(regNum==null || (regNum!=null && regNum.length()==0 )  ){
			System.out.println("Please enter valid car Registraion Number!");
			return null;
		}
		if(color==null || (color!=null && color.length()==0 )  ){
			System.out.println("Please enter car Color!");
			return null;
		}
		
		return new Car(regNum,color);
	}

	@Override
	public Car createCar(String regNum) {
		if(regNum==null || (regNum!=null && regNum.length()==0 )  ){
			System.out.println("Please enter valid car Registraion Number!");
			return null;
		}
		return new Car(regNum,null);
	}
	
	
	

}

package com.example.util;

import com.example.core.Car;
import com.example.core.Command;

public interface ICommandUtility {

	public Command processCommandLine(String commandLine);
	
	public Car createCar(String regNum, String clor);
	
	public Car createCar(String regNum);
}

package com.example.service;

import java.io.BufferedReader;

public interface IParkingService {
	
	public BufferedReader createReadBuffer(String fileName);
	
	public void processCommand(String commandLine);

}

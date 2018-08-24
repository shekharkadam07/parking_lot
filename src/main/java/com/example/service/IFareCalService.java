package com.example.service;

import com.example.core.Car;

public interface IFareCalService {
	
	public int findParkTimeInMinutes(Car c);

	public float calFare(int totParkTime, int amtPerUnit, int oneUnitTime );
}

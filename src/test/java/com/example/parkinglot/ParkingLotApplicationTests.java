package com.example.parkinglot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;

import com.example.util.CommandUtilityTest;
import com.example.util.ParkingUtilityTest;

@RunWith(Suite.class)
@SuiteClasses({ParkingUtilityTest.class, CommandUtilityTest.class})
@ContextConfiguration(classes = {ParkingLotApplication.class})
public class ParkingLotApplicationTests {

}

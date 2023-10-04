package com.moonpark.factories;

import com.moonpark.domains.ParkZoneType;

public class ParkingCostCalculatorFactory {

	public static ParkingCostCalculator createParkingCostCalculator(ParkZoneType zone) {
		return switch (zone) {
			case M1 -> new M1ParkingCostCalculator();
			case M2 -> new M2ParkingCostCalculator();
			case M3 -> new M3ParkingCostCalculator();
		};
	}
}

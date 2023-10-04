package com.moonpark.factories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class M1ParkingCostCalculator  implements ParkingCostCalculator {
	@Override
	public BigDecimal calculateParkingCost(LocalDateTime entryTimestamp, LocalDateTime exitTimestamp) {
		BigDecimal minutesParked = BigDecimal.valueOf(Duration.between(entryTimestamp, exitTimestamp).toMinutes());
		BigDecimal hoursParked = minutesParked.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
		return hoursParked.multiply(BigDecimal.valueOf(60));
	}
}

package com.moonpark.factories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class M2ParkingCostCalculator implements ParkingCostCalculator {
	@Override
	public BigDecimal calculateParkingCost(LocalDateTime entryTimestamp, LocalDateTime exitTimestamp) {
		BigDecimal minutesParked = BigDecimal.valueOf(Duration.between(entryTimestamp, exitTimestamp).toMinutes());

		BigDecimal hoursParked = minutesParked.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

		DayOfWeek dayOfWeek = entryTimestamp.getDayOfWeek();

		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			return hoursParked.multiply(BigDecimal.valueOf(200));
		} else {
			return hoursParked.multiply(BigDecimal.valueOf(100));
		}
	}
}

package com.moonpark.factories;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class M3ParkingCostCalculator  implements ParkingCostCalculator {
	@Override
	public BigDecimal calculateParkingCost(LocalDateTime entryTimestamp, LocalDateTime exitTimestamp) {
		DayOfWeek dayOfWeek = entryTimestamp.getDayOfWeek();

		if (dayOfWeek != DayOfWeek.SUNDAY){
			return getCostWeekday(entryTimestamp, exitTimestamp);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getCostWeekday(final LocalDateTime entryParkTime, final LocalDateTime exitParkTime){

		final long minutesParked = Duration.between(entryParkTime, exitParkTime).toMinutes();
		LocalTime startTime = entryParkTime.toLocalTime();

		if (startTime.isAfter(LocalTime.of(7, 59,59))
						&& startTime.isBefore(LocalTime.of(16, 0))){
			return getPriceBetween8to16(minutesParked);
		}
		return BigDecimal.valueOf(3).multiply(BigDecimal.valueOf(minutesParked));
	}

	private BigDecimal getPriceBetween8to16(final long minutesParked){
		BigDecimal hourlyRate = BigDecimal.valueOf(2);
		if (minutesParked <= 60){
			return BigDecimal.ZERO;
		} else {
			return hourlyRate.multiply(BigDecimal.valueOf((minutesParked - 60)));
		}
	}
}

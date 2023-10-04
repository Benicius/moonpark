package com.moonpark.factories;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ParkingCostCalculator {
	BigDecimal calculateParkingCost(LocalDateTime entryTimestamp, LocalDateTime exitTimestamp);
}

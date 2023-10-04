package com.moonpark.helper;

import com.moonpark.domains.ParkResponse;
import com.moonpark.domains.ParkZoneType;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class ParkResponseHelperMock {

	public static ParkResponse getParkResponse(){
		return new ParkResponse(
						"ABC123",
						LocalDateTime
							.now()
							.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
							.with(LocalTime.of(10, 15,1)),
						LocalDateTime
							.now()
							.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
							.with(LocalTime.of(21, 23,1)),
						ParkZoneType.M1,
						BigDecimal.valueOf(230));
	}
}

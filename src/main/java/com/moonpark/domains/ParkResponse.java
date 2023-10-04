package com.moonpark.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParkResponse(
				String licensePlate,
				LocalDateTime entryTimestamp,
				LocalDateTime exitTimestamp,
				ParkZoneType zoneType,
				BigDecimal price) {
}

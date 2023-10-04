package com.moonpark.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Park {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
	private String licensePlate;
	private LocalDateTime entryParkTime;
	private LocalDateTime exitParkTime;

	@Enumerated(EnumType.STRING)
	private ParkZoneType zoneType;

	private BigDecimal price;
}

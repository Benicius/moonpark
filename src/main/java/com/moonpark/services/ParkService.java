package com.moonpark.services;

import com.moonpark.domains.Park;
import com.moonpark.domains.ParkResponse;
import com.moonpark.exceptions.NoValidContentException;
import com.moonpark.factories.ParkingCostCalculator;
import com.moonpark.factories.ParkingCostCalculatorFactory;
import com.moonpark.repositories.ParkRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.moonpark.constants.ApiConstants.DATA_REGEX;

@Service
public class ParkService {

	private final ParkRepository parkRepository;

	public ParkService(ParkRepository parkRepository) {
		this.parkRepository = parkRepository;
	}

	public ParkResponse getCostOfPark(final String licensePlate, final String exitParkTime) {
		Park park = parkRepository.findByLicensePlate(licensePlate)
						.orElseThrow(() -> new NoValidContentException("License Plate is not exist!"));
		park.setExitParkTime(convertingData(exitParkTime));

		calculateParkPrice(park);

		return new ParkResponse(
						park.getLicensePlate(),
						park.getEntryParkTime(),
						park.getExitParkTime(),
						park.getZoneType(),
						park.getPrice()
		);
	}

	public void calculateParkPrice(final Park park){
		ParkingCostCalculator calculator = ParkingCostCalculatorFactory.createParkingCostCalculator(park.getZoneType());
		BigDecimal cost = calculator.calculateParkingCost(park.getEntryParkTime(), park.getExitParkTime());
		park.setPrice(cost);
	}

	private LocalDateTime convertingData(final String exitParkTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_REGEX);
		return LocalDateTime.parse(exitParkTime, formatter);
	}
}

package com.moonpark.controllers;

import com.moonpark.constants.ApiConstants;
import com.moonpark.domains.ParkResponse;
import com.moonpark.exceptions.NoValidContentException;
import com.moonpark.services.ParkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/v1")
public class ParkController {

	private final ParkService parkService;

	public ParkController(ParkService parkService) {
		this.parkService = parkService;
	}


	@GetMapping("/zone/{licensePlate}")
	public ResponseEntity<ParkResponse> calculateParkingCost(
					@PathVariable String licensePlate,
					@RequestParam String exitParkTime) {
		validationExitParkTime(exitParkTime);
		return new ResponseEntity<>(parkService.getCostOfPark(licensePlate, exitParkTime), HttpStatus.OK);
	}

	private void validationExitParkTime(final String exitParkTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApiConstants.DATA_REGEX);
		try {
			LocalDateTime.parse(exitParkTime, formatter);
		} catch (DateTimeParseException e) {
			throw new NoValidContentException("exitParkTime is not correct!");
		}
	}
}

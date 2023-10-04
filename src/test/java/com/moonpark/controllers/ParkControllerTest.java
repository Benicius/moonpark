package com.moonpark.controllers;

import com.moonpark.exceptions.NoValidContentException;
import com.moonpark.services.ParkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static com.moonpark.helper.ParkResponseHelperMock.getParkResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkController.class)
public class ParkControllerTest {

	@InjectMocks
	ParkController parkController;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParkService parkService;

	@BeforeTestMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void calculateParkingCost_Exceptions() {
		String licensePlate = "ABC123";
		String exitParkTime = LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
						.with(LocalTime.of(14,50)).toString();

		Throwable exception =
						Assertions.assertThrows(
										NoValidContentException.class,
										() -> parkController.calculateParkingCost(licensePlate, exitParkTime));

		Assertions.assertEquals("exitParkTime is not correct!", exception.getMessage());
	}

	@Test
	public void calculateParkingCost_ValidRequest_ReturnsOK() throws Exception {
		String licensePlate = "ABC123";
		String exitParkTime = LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
						.with(LocalTime.of(10, 15, 0, 1)) // Defina os segundos como 0
						.toString();

		Mockito.when(parkService.getCostOfPark(licensePlate, exitParkTime)).thenReturn(getParkResponse());

		mockMvc.perform(
						MockMvcRequestBuilders
										.get("/api/v1/zone/{licensePlate}", licensePlate)
										.param("exitParkTime", exitParkTime))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.licensePlate").value("ABC123"))
						.andExpect(jsonPath("$.entryTimestamp").value(LocalDateTime
										.now()
										.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
										.with(LocalTime.of(10, 15,1)).toString()))
						.andExpect(jsonPath("$.exitTimestamp").value(LocalDateTime
										.now()
										.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
										.with(LocalTime.of(21, 23,1)).toString()))
						.andExpect(jsonPath("$.zoneType").value("M1"))
						.andExpect(jsonPath("$.price").value(230));
	}
}

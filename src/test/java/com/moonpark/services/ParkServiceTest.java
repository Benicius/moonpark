package com.moonpark.services;

import com.moonpark.domains.Park;
import com.moonpark.exceptions.NoValidContentException;
import com.moonpark.repositories.ParkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import static com.moonpark.helper.ParkHelperMock.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkServiceTest {

	@InjectMocks
	ParkService parkService;

	@Mock
	ParkRepository parkRepository;

	@Test
	public void shouldCalculateM1Zone(){
		Park park = getParkM1();
		BigDecimal expectedCost = new BigDecimal("70.20");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldCalculateM2ZoneOnWeek(){
		Park park = getParkM2WeekDay();
		BigDecimal expectedCost = new BigDecimal("292.00");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldCalculateM2ZoneOnWeekendSaturday(){
		Park park = getParkM2WeekendSaturday();
		BigDecimal expectedCost = new BigDecimal("350.00");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldCalculateM2ZoneOnWeekendSunday(){
		Park park = getParkM2WeekendSunday();
		BigDecimal expectedCost = new BigDecimal("600.00");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldGetCostOfPark(){
		Park park = getCostOfParkM2WeekDay();
		Mockito.when(parkRepository.findByLicensePlate("ED-52-XT")).thenReturn(Optional.of(park));
		BigDecimal expectedCost = new BigDecimal("463.00");
		String exitTimePark = LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))
						.with(LocalTime.of(13, 21,0, 1))
						.toString();
		parkService.getCostOfPark("ED-52-XT", exitTimePark);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldGetCostOfParkException(){
		Mockito.when(parkRepository.findByLicensePlate("ED-52-XT")).thenReturn(Optional.empty());
		String exitTimePark = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)).toString();

		Throwable exception =
						Assertions.assertThrows(
										NoValidContentException.class,
										() -> parkService.getCostOfPark("ED-52-XT", exitTimePark));

		Assertions.assertEquals("License Plate is not exist!", exception.getMessage());
	}

	@Test
	public void shouldCalculateM3Sunday(){
		Park park = getParkM3Sunday();
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(BigDecimal.ZERO, park.getPrice());
	}

	@Test
	public void shouldCalculateM3WeekdayMin60Minutes(){
		Park park = getParkM3WeekdayMin60Minutes();
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(BigDecimal.ZERO, park.getPrice());
	}

	@Test
	public void shouldCalculateM3WeekdayBefore8(){
		Park park = getParkM3WeekdayBefore8();
		BigDecimal expectedCost = new BigDecimal("330");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldCalculateM3WeekdayPlus60MinutesBetween8to16(){
		Park park = getParkM3WeekdayPlus60MinutesBetween8to16();
		BigDecimal expectedCost = new BigDecimal("564");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}

	@Test
	public void shouldCalculateM3WeekdayNightTime(){
		Park park = getParkM3WeekdayNightTime();
		BigDecimal expectedCost = new BigDecimal("789");
		parkService.calculateParkPrice(park);

		Assertions.assertEquals(expectedCost, park.getPrice());
	}
}

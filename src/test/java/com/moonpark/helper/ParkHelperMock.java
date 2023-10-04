package com.moonpark.helper;

import com.moonpark.domains.Park;
import com.moonpark.domains.ParkZoneType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class ParkHelperMock {

	public ParkHelperMock() {
	}

	public static Park getParkM1(){
		final Park park = new Park();
		park.setCode("25-652-65");
		park.setLicensePlate("Ac-22-XB");
		park.setZoneType(ParkZoneType.M1);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(LocalTime.of(8, 20)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(LocalTime.of(9, 30)));
		return park;
	}

	public static Park getParkM2WeekDay(){
		final Park park = new Park();
		park.setCode("856-jghKj-748");
		park.setLicensePlate("ED-52-XT");
		park.setZoneType(ParkZoneType.M2);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))
						.with(LocalTime.of(7, 20)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))
						.with(LocalTime.of(10, 15)));
		return park;
	}

	public static Park getCostOfParkM2WeekDay(){
		final Park park = new Park();
		park.setCode("856-jghKj-748");
		park.setLicensePlate("ED-52-XT");
		park.setZoneType(ParkZoneType.M2);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))
						.with(LocalTime.of(8, 43)));
		return park;
	}

	public static Park getParkM2WeekendSaturday(){
		final Park park = new Park();
		park.setCode("25Ef65xdfg5");
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M2);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
						.with(LocalTime.of(12, 35)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
						.with(LocalTime.of(14, 20)));
		return park;
	}

	public static Park getParkM2WeekendSunday(){
		final Park park = new Park();
		park.setCode("25Ef65xdfg5");
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M2);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
						.with(LocalTime.of(10, 0)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
						.with(LocalTime.of(13, 0)));
		return park;
	}

	public static Park getParkM3Sunday(){
		final Park park = new Park();
		park.setCode("25Ef65xdfg5");
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M3);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
						.with(LocalTime.of(9, 0)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
						.with(LocalTime.of(15, 0)));
		return park;
	}

	public static Park getParkM3WeekdayMin60Minutes(){
		final Park park = new Park();
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M3);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(8, 0)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(9, 0)));
		return park;
	}

	public static Park getParkM3WeekdayBefore8(){
		final Park park = new Park();
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M3);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(7, 30)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(9, 20)));
		return park;
	}

	public static Park getParkM3WeekdayPlus60MinutesBetween8to16(){
		final Park park = new Park();
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M3);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(8, 0)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY))
						.with(LocalTime.of(13, 42)));
		return park;
	}

	public static Park getParkM3WeekdayNightTime(){
		final Park park = new Park();
		park.setLicensePlate("BR-86-ZW");
		park.setZoneType(ParkZoneType.M3);
		park.setEntryParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
						.with(LocalTime.of(17, 0)));
		park.setExitParkTime(LocalDateTime
						.now()
						.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
						.with(LocalTime.of(21, 23)));
		return park;
	}
}

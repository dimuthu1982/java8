package com.test.dimuthu.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateTimeMain {

	/*
	 * Advantages over the old API
	 * 1. Old APi suitable for simple date/time operations. Any complex tasks would require third party library's. With Java 8 JodaTime is introduced to simplify.
	 * 2. Remove anomalies. eg: Calendar month use to represent in zero based and but days of the week one-based.
	 * 3. Flexible. Old date use to hold the Unix epoch (Time in milliseconds elapsed from 1970 January 1). New API address different needs.
	 * 
			Instant – represents a point in time (timestamp)
			LocalDate – represents a date (year, month, day)
			LocalDateTime – same as LocalDate, but includes time with nanosecond precision
			OffsetDateTime – same as LocalDateTime, but with time zone offset
			LocalTime – time with nanosecond precision and without date information
			ZonedDateTime – same as OffsetDateTime, but includes a time zone ID
			OffsetLocalTime – same as LocalTime, but with time zone offset
			MonthDay – month and day, without year or time
			YearMonth – month and year, without day or time
			Duration – amount of time represented in seconds, minutes and hours. Has nanosecond precision
			Period – amount of time represented in days, months and years
			
		4. Thread Safe. New Time APi is immutable meaning any operation will return a new object hence its Thread safe 
	 */
	public static void main(String[] args) throws ParseException {

		System.out.println("============== Getting current time ============== ");
		System.out.println("Old: " + new Date());
		System.out.println("New: " + ZonedDateTime.now());
		
		System.out.println("\n============== Representing specific time ============== ");
		System.out.println("Old: " + new GregorianCalendar(2017, Calendar.SEPTEMBER, 20).getTime());
		System.out.println("New: " + LocalDate.of(2017, Calendar.SEPTEMBER, 20));
		
		System.out.println("\n============== Extracting Month ============== ");
		System.out.println("Old: " + new GregorianCalendar().get(Calendar.MONTH));
		System.out.println("New: " + LocalDateTime.now().getMonth());
		
		System.out.println("\n============== Adding 2 months to current date ============== ");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, 2);
		System.out.println("Old: " + now.getTime());
		System.out.println("New: " + LocalDateTime.now().plusMonths(2));
		
		System.out.println("\n============== Setting current day to 22nd ============== ");
		now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 22);
		System.out.println("Old: " + now.getTime());
		System.out.println("New: " + LocalDateTime.now().withDayOfMonth(22));
		
		System.out.println("\n============== Setting current day to 22nd ============== ");
		now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 22);
		System.out.println("Old: " + now.getTime());
		System.out.println("New: " + LocalDateTime.now().withDayOfMonth(22));
		
		System.out.println("\n============== Truncating values hours ============== ");
		now = Calendar.getInstance();
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		System.out.println("Old: " + now.getTime());
		System.out.println("New: " + LocalTime.now().truncatedTo(ChronoUnit.HOURS));
		
		System.out.println("\n============== Setting the zone ============== ");
		now = Calendar.getInstance();
		now.setTimeZone(TimeZone.getTimeZone("CET"));
		System.out.println("Old: " + now.getTime());
		System.out.println("New: " + LocalDateTime.now().atZone(ZoneId.of("CET")));
		
		System.out.println("\n============== Getting time difference between two days ============== ");
		now = Calendar.getInstance();
		
		Calendar addOneHour = Calendar.getInstance();
		addOneHour.add(Calendar.HOUR, 1);
		
		long elapsedOld = addOneHour.getTime().getTime() - now.getTime().getTime();
		
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		LocalDateTime localDateTimeAddOneHour = LocalDateTime.now().plusHours(1);
		Duration elapsedNew = Duration.between(localDateTimeNow, localDateTimeAddOneHour);
		
		System.out.println("Old Milliseconds: " + elapsedOld);
		System.out.println("New Milliseconds: " + elapsedNew.toMillis());
		
		System.out.println("Old Minuits: " + TimeUnit.MILLISECONDS.toMinutes(elapsedOld));
		System.out.println("New Minuits: " + elapsedNew.toMinutes());
		
		System.out.println("Old Hour: " + TimeUnit.MINUTES.toHours(TimeUnit.MILLISECONDS.toMinutes(elapsedOld)));
		System.out.println("New Hour: " + elapsedNew.toHours());
		
		System.out.println("\n============== Date Format ============== ");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		now = Calendar.getInstance();
		String formattedDate = dateFormat.format(now.getTime());
		
		LocalDate localDatenow = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateNew = localDatenow.format(formatter);
		
		System.out.println("Old: " + formattedDate);
		System.out.println("New: " + LocalDate.parse(formattedDateNew, formatter));
	}

}

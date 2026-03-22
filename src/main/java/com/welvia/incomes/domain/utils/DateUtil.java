package com.welvia.incomes.domain.utils;

import com.welvia.incomes.domain.constants.DateConstants;
import com.welvia.incomes.domain.exception.BadRequestException;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static Instant createDateTime(String isoDate) {
        LocalDateTime ldt = LocalDateTime.parse(isoDate);

        return ldt.atZone(DateConstants.DEFAULT_TIME_ZONE).toInstant();
    }

    public static Instant now() {
        return Instant.now();
    }

    public static String formatDate(Instant date) {
        return DateTimeFormatter.ISO_LOCAL_DATE
                .withZone(DateConstants.DEFAULT_TIME_ZONE)
                .format(date);
    }

    public static String formatDateTime(Instant date) {
        return DateTimeFormatter.ISO_INSTANT.format(date);
    }

    public static Instant getStartDateByFilter(String month, String year) throws Exception {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_FORMAT).withZone(DateConstants.DEFAULT_TIME_ZONE);

            String adjustedMonth = month.length() < 2 ? "0" + month : month;

            LocalDate firstDateOfMonth = LocalDate.parse(String.format("01/%s/%s", adjustedMonth, year), fmt);

            return firstDateOfMonth.atStartOfDay(DateConstants.DEFAULT_TIME_ZONE).toInstant();
        } catch (DateTimeException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Instant getEndDateByFilter(String month, String year) throws Exception {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_FORMAT).withZone(DateConstants.DEFAULT_TIME_ZONE);

            String adjustedMonth = month.length() < 2 ? "0" + month : month;

            LocalDate firstDateOfMonth = LocalDate.parse(String.format("01/%s/%s", adjustedMonth, year), fmt);
            LocalDate lastDateOfMonth = firstDateOfMonth.withDayOfMonth(firstDateOfMonth.lengthOfMonth());

            return lastDateOfMonth.atTime(23, 59, 59, 999).atZone(DateConstants.DEFAULT_TIME_ZONE).toInstant();
        } catch (DateTimeException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }
}
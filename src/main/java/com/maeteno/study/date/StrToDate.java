package com.maeteno.study.date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Slf4j
public class StrToDate {

    @Test
    public void strToDate() throws ParseException {
        var strDate = "2021-06-25 12:34:09";
        var format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        var date = format.parse(strDate);
        log.info("{}", date);
    }

    @Test
    public void dateToStr() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        log.info("{}", dateStr);

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        log.info("{}", format2.format(date));
    }

    @Test
    public void timeToDate() {
        // 时间戳转时间
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        log.info("Date: {} By {}", date, millis);

        // 时间获取时间戳
        long timestamp = date.getTime();
        log.info("Timestamp: {} By {}", timestamp, date);
    }

    @Test
    public void timeToLocalDate() {
        // 时间戳转时间
        long millis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(millis);

        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        LocalTime localTime = LocalTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        log.info("Local Date: {}", localDate);
        log.info("Local Time: {}", localTime);
        log.info("Local Date Time: {}", localDateTime);
    }

    @Test
    public void strToLocalDate() {
        var strDate = "2021-06-25 12:34:09";
        var format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var date = LocalDate.parse(strDate, format);
        var time = LocalTime.parse(strDate, format);
        var dateTime = LocalDateTime.parse(strDate, format);

        log.info("Local Date: {}", date);
        log.info("Local Time: {}", time);
        log.info("Local Date Time: {}", dateTime);

        log.info("Local Date: {}", LocalDate.parse("2021-06-25")); // 默认格式 yyyy-MM-dd
        log.info("Local Time: {}", LocalTime.parse("12:34:09")); // 默认格式 HH:mm:ss
        log.info("Local Date Time: {}", LocalDateTime.parse("2021-06-25T12:34:09")); // 默认格式 yyyy-MM-ddTHH:mm:ss
    }

    @Test
    public void localDateToStr() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.info("Local Date: {}", localDate.format(localDateFormat));

        LocalTime time = LocalTime.now();
        DateTimeFormatter localTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        log.info("Local Time: {}", localTimeFormat);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("Local Date Time: {}", localDateTimeFormat);
    }

    @Test
    public void huToolDateUtils() {
        var dateStr = "2021-06-25 12:34:09";

        Date date = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
        log.info("Date: {}", date);

        LocalDateTime localDateTime = LocalDateTimeUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
        log.info("Local Date Time: {}", localDateTime);
    }

    @Test
    public void huToolDateUtilsFormat() {
        Date date = new Date();
        log.info("Date: {}", DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));

        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Local Date Time: {}", LocalDateTimeUtil.format(localDateTime, "yyyy-MM-dd HH:mm:ss"));

        LocalDate localDate = LocalDate.now();
        log.info("Local Date Time: {}", LocalDateTimeUtil.format(localDate, "yyyy-MM-dd HH:mm:ss"));
    }
}

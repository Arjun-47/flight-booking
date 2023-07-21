package com.cleartrip.automation.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Utility {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMM");

    public static Properties getAutomationProperties() {
        System.out.println("Reading the automation.properties file");
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("./src/test/resources/automation.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static Calendar getXWeekFromNow(int x) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, x);
        return calendar;
    }

    public static int getDayFromDate(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getMonthFromDate(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }

}

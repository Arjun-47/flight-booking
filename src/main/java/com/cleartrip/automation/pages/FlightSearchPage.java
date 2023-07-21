package com.cleartrip.automation.pages;


import com.cleartrip.automation.framework.Base;
import org.openqa.selenium.By;

import java.text.MessageFormat;

public class FlightSearchPage extends Base {

    private static final By TRIP_TYPE_MENU = By.xpath("//span[text()='One way']/../../parent::button");
    private static final By PASSENGER_MENU = By.xpath("//span[text()='1 Adult, Economy']/../../parent::button");
    private static final By WHERE_FROM = By.xpath("//input[@placeholder='Where from?']");
    private static final By WHERE_TO = By.xpath("//input[@placeholder='Where to?']");
    private static final By TRAVEL_DATE_FIELD = By.xpath("//div[contains(@class, 'homeCalender')]/button[1]");
    private static final By SEARCH_FLIGHT_BUTTON = By.xpath("//span[text()='Search flights']/..");

    private static final String TRIP_TYPE_MENU_OPTION = "//span[text()='%s'][contains(@class, 'dropdown-text')]/../../parent::li";
    private static final String PASSENGER_MENU_OPTION = "//span[text()='%s']/parent::div/following-sibling::ul/li[3]";
    private static final String AIRPORT_LIST_OPTION = "//ul[@class='airportList']/li//p[contains(text(), '%s')]";
    private static final String DAY_IN_CALENDER = "//div[@class=''DayPicker-Caption'']/div[contains(text(),''{0}'')]/../../div[@class=''DayPicker-Body'']//div[text()=''{1}'']";


    public void clickTripTypeMenu() {
        click(TRIP_TYPE_MENU);
    }

    public void selectTripType(String type) {
        click(By.xpath(String.format(TRIP_TYPE_MENU_OPTION, type)));
    }

    public void clickPassengerMenu() {
        click(PASSENGER_MENU);
    }

    public void addPassenger(String passengerType) {
        click(By.xpath(String.format(PASSENGER_MENU_OPTION, passengerType)));
    }

    public void enterAndSelectWhereFrom(String from) {
        enterText(WHERE_FROM, from);
        click(By.xpath(String.format(AIRPORT_LIST_OPTION, from)));
    }

    public void enterAndSelectWhereTo(String to) {
        enterText(WHERE_TO, to);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
        }
        click(By.xpath(String.format(AIRPORT_LIST_OPTION, to)));
    }

    public void clickTravelDate() {
        click(TRAVEL_DATE_FIELD);
    }

    public void selectDate(String month, int day) {
        click(By.xpath(MessageFormat.format(DAY_IN_CALENDER, month, day)));
    }

    public void clickSearchFlightButton() {
        click(SEARCH_FLIGHT_BUTTON);
    }
}

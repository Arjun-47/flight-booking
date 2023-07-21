package com.cleartrip.tests;

import com.cleartrip.automation.framework.Base;
import com.cleartrip.automation.framework.Utility;
import com.cleartrip.automation.pages.FlightSearchPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * @author Nagarjun Chittyala
 */
@Test
public class ClearTripFlightBooking extends Base {

    private FlightSearchPage flightSearchPage;

    @BeforeMethod
    public void pageObjectSetUp() {
        flightSearchPage = new FlightSearchPage();
    }

    @Test
    public void execution() throws InterruptedException {
        flightSearchPage.clickTripTypeMenu();
        flightSearchPage.selectTripType("Round trip");

        flightSearchPage.clickPassengerMenu();
        flightSearchPage.addPassenger("Children");

        flightSearchPage.enterAndSelectWhereFrom("Bangalore");
        flightSearchPage.enterAndSelectWhereTo("Hyderabad");

        Calendar calendar = Utility.getXWeekFromNow(2);

        flightSearchPage.clickTravelDate();
        flightSearchPage.selectDate(Utility.getMonthFromDate(calendar), Utility.getDayFromDate(calendar));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        flightSearchPage.selectDate(Utility.getMonthFromDate(calendar), Utility.getDayFromDate(calendar));
        flightSearchPage.clickSearchFlightButton();
        Thread.sleep(5000);
    }
}

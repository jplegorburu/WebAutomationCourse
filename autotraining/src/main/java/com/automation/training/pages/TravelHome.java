package com.automation.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.training.pages.cruises.CruisesSearch;
import com.automation.training.pages.flights.FlightSearch;
import com.automation.training.pages.hotels.HotelSearch;
import com.automation.training.pages.packages.PackageSearch;

public class TravelHome extends BasePage {

    public TravelHome(WebDriver driver){
        super(driver);
        driver.get("https://www.travelocity.com");
    }

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id = "primary-header-flight")
    private WebElement flights;

    @FindBy(id = "primary-header-hotel")
    private WebElement hotels;

    @FindBy(id = "primary-header-package")
    private WebElement flightAndHotel;

    @FindBy(id = "primary-header-cruise")
    private WebElement cruises;

    public FlightSearch searchFlights(){
        getWait().until(ExpectedConditions.elementToBeClickable(flights));
        flights.click();
        return new FlightSearch(getDriver());
    }

    public CruisesSearch searchCruises(){
        getWait().until(ExpectedConditions.elementToBeClickable(cruises));
        cruises.click();
        return new CruisesSearch(getDriver());
    }

    public HotelSearch searchHotels(){
        getWait().until(ExpectedConditions.elementToBeClickable(hotels));
        hotels.click();
        return new HotelSearch(getDriver());
    }


    public PackageSearch searchPackages(){
        getWait().until(ExpectedConditions.elementToBeClickable(flightAndHotel));
        flightAndHotel.click();
        return new PackageSearch(getDriver());
    }
}

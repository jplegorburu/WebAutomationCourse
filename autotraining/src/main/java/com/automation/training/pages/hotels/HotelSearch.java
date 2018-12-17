package com.automation.training.pages.hotels;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.training.pages.BaseSearch;

public class HotelSearch extends BaseSearch {
	
	@FindBy(id = "hotel-rooms-hlp")
    private WebElement roomsNum;

    @FindBy(id = "hotel-destination-hlp")
    private WebElement destination;

    @FindBy(id = "hotel-checkin-hlp")
    private WebElement fromDate;

    @FindBy(id = "hotel-checkout-hlp")
    private WebElement toDate;

    @FindBy(id = "hotel-1-adults-hlp")
    private WebElement adultsNum;
    
    public HotelSearch(WebDriver driver){
        super(driver);
    }
    
    public void setDestination(String origin) {
    	getWait().until(ExpectedConditions.elementToBeClickable(destination));
    	destination.click();
    	destination.sendKeys(origin);
    }
    
    public HotelResultPage search() {
    	clickSearchButton();
    	return new HotelResultPage(getDriver());
    }
}
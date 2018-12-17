package com.automation.training.pages.hotels;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.training.pages.BasePage;
import com.automation.training.pages.flights.FlightResultPage;

public class HotelReservationPage extends BasePage {
	
    @FindBy(className = "price")
    private WebElement hotelPrice;
    
    @FindBy(id = "hotel-name")
    private WebElement hotelName;
    
    @FindBy(className = "phone-number")
    private WebElement hotelPhone;
    
    @FindBy(className = "book-button-wrapper")
    private List<WebElement> bookOptions;
    
    public HotelReservationPage(WebDriver driver){
        super(driver);
    }
    
    public String getHotelName() {
    	getWait().until(ExpectedConditions.visibilityOf(hotelName));
    	return hotelName.getText();
    }
    
    public String getHotelPrice() {
    	getWait().until(ExpectedConditions.visibilityOf(hotelPrice));
    	return hotelPrice.getText();
    }
    
    public String getPhone() {
    	getWait().until(ExpectedConditions.visibilityOf(hotelPhone));
    	return hotelPhone.getText();
    }
    
    public FlightResultPage selectCheapestOption() {
    	getWait().until(ExpectedConditions.elementToBeClickable(bookOptions.get(0)));
    	bookOptions.get(0).click();
    	return new FlightResultPage(getDriver());
    }
}

package com.automation.training.pages.cruises;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.training.pages.BasePage;

public class CruisesReservationPage extends BasePage {
    
    @FindBy(className = "departure-port")
    private WebElement departurePort;
    
    @FindBy(className = "trip-title")
    private WebElement tripTitle;
    
    public CruisesReservationPage(WebDriver driver){
        super(driver);
    }
    
    public String getDeparturePort() {
    	getWait().until(ExpectedConditions.visibilityOf(departurePort));
    	return departurePort.getText();
    }
    
    public String getTripTitle() {
    	getWait().until(ExpectedConditions.visibilityOf(tripTitle));
    	return tripTitle.getText();
    }
}

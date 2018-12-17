package com.automation.training.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TripDetailsPage extends BasePage {
	@FindBy(id = "bookButton")
    private WebElement bookButton;

    @FindBy(className = "tripTotals")
    private WebElement totalPrice;
    
    @FindBy(id = "FlightUDPBookNowButton1")
    private WebElement bookPackage;
    
    @FindBy(id = "tripTotal")
    private WebElement packagePrice;

    @FindBy(className = "priceGuarantee")
    private WebElement priceGuarantee;

    @FindBy(className = "flex-tile")
    private List<WebElement> flightInformation;

    @FindBy(id = "dxGroundTransportationModule")
    private WebElement transportation;

    @FindBy(className = "added-to-trip")
    private WebElement carAdded;

    @FindBy(id = "hotels-card")
    private WebElement hotel;

    public TripDetailsPage(WebDriver driver){
        super(driver);
    }

    public WebElement getPriceGuarantee(){
        getWait().until(ExpectedConditions.visibilityOf(priceGuarantee));
        return priceGuarantee;
    }

    public WebElement getTotalPrice(){
        getWait().until(ExpectedConditions.visibilityOf(totalPrice));
        return totalPrice;
    }
    
    public WebElement getPackagePrice() {
    	getWait().until(ExpectedConditions.visibilityOf(packagePrice));
        return packagePrice.findElement(By.className("visuallyhidden"));
    }

    public WebElement getHotel(){
        getWait().until(ExpectedConditions.visibilityOf(hotel));
        return hotel;
    }
    
    public List<WebElement> getFlightInformation(){
        return flightInformation;
    }

    public PaymentPage bookIt(){
        getWait().until(ExpectedConditions.elementToBeClickable(bookButton));
        bookButton.click();
        return new PaymentPage(getDriver());
    }
    
    public PaymentPage bookPackage(){
        getWait().until(ExpectedConditions.elementToBeClickable(bookPackage));
        bookPackage.click();
        return new PaymentPage(getDriver());
    }
    
    public void selectCar() {
    	getWait().until(ExpectedConditions.visibilityOf(transportation));
    	transportation.findElements(By.className("gt-add-btn")).get(0).click();
    }
    
    public WebElement getCarAdded() {
    	getWait().until(ExpectedConditions.visibilityOf(carAdded));
    	return carAdded;
    }
}
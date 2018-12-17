package com.automation.training.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PaymentPage extends BasePage {
    @FindBy(id = "complete-booking")
    private WebElement completeBookingButton;

    @FindBy(id = "preferences")
    private WebElement travelerInfo;

    @FindBy(id = "rulesandrestrictionspayment")
    private WebElement rulesInfo;
    
    @FindBy(id = "email")
    private WebElement emailInfo;

    @FindBy(id = "payments")
    private WebElement paymentInfo;

    @FindBy(id = "trip-summary")
    private WebElement tripSummary;

    @FindBy(css = "span[data-price-update='tripTotal']")
    private WebElement tripTotal;
    
    
    public PaymentPage(WebDriver driver){
        super(driver);
    }

    public WebElement getCompleteBookingButton(){
        getWait().until(ExpectedConditions.visibilityOf(completeBookingButton));
        return completeBookingButton;
    }

    public WebElement getTravelerInfo(){
        getWait().until(ExpectedConditions.visibilityOf(travelerInfo));
        return travelerInfo;
    }
    
    public WebElement getRulesInfo(){
        getWait().until(ExpectedConditions.visibilityOf(rulesInfo));
        return rulesInfo;
    }

    public WebElement getEmailInfo(){
        getWait().until(ExpectedConditions.visibilityOf(emailInfo));
        return emailInfo;
    }

    public WebElement getPaymentInfo(){
        getWait().until(ExpectedConditions.visibilityOf(paymentInfo));
        return paymentInfo;
    }

    public WebElement getTripSummary(){
        getWait().until(ExpectedConditions.visibilityOf(tripSummary));
        return tripSummary;
    }
    
    public WebElement getTripTotal() {
        getWait().until(ExpectedConditions.visibilityOf(tripTotal));
        return tripTotal;
    }
}
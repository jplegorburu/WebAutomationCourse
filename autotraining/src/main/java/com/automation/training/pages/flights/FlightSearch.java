package com.automation.training.pages.flights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.training.pages.BaseSearchDateTime;

public class FlightSearch extends BaseSearchDateTime{

    public FlightSearch(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "flight-type-roundtrip-label-flp")
    private WebElement roundTripButton;

    @FindBy(id = "flight-origin-flp")
    private WebElement fromCity;


    @FindBy(id = "flight-destination-flp")
    private WebElement toCity;

    @FindBy(id = "flight-departing-flp")
    private WebElement fromDate;

    @FindBy(id = "flight-returning-flp")
    private WebElement toDate;

    @FindBy(id = "flight-adults-flp")
    private WebElement adultsNum;

    @FindBy(id = "typeaheadDataPlain")
    private WebElement autocompletDropDown;
    
    public void setFromCity(String origin) {
    	getWait().until(ExpectedConditions.elementToBeClickable(fromCity));
    	fromCity.click();
        fromCity.sendKeys(origin);
    }

    public void setToCity(String destination) {
    	getWait().until(ExpectedConditions.elementToBeClickable(toCity));
    	toCity.click();
    	toCity.sendKeys(destination);
    }
    
    public void setAdultsNum(Integer adults) {
    	getWait().until(ExpectedConditions.elementToBeClickable(adultsNum));
    	Select adultsDropDown = new Select(adultsNum);
        adultsDropDown.selectByValue(adults.toString());
    }
    
    public void setFromDate(String from) {
    	getWait().until(ExpectedConditions.elementToBeClickable(fromDate));
    	fromDate.click();
        setDate(from);
    }
    public void setToDate(String to) {
    	getWait().until(ExpectedConditions.elementToBeClickable(toDate));
    	toDate.click();
        setDate(to);
    }   

    public FlightResultPage search() {
    	clickSearchButton();
    	return new FlightResultPage(getDriver());
    }
}
package com.automation.training.pages.cruises;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.training.pages.BaseSearch;

public class CruisesSearch extends BaseSearch {
    public CruisesSearch(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "cruise-destination-cruiselp")
    private WebElement destination;

    @FindBy(id = "cruise-departure-month-cruiselp")
    private WebElement date;

    @FindBy(id = "cruise-adults-cruiselp")
    private WebElement adultsNum;

    @FindBy(id = "typeaheadDataPlain")
    private WebElement autocompletDropDown;
    
    public void setAdultsNum(Integer adults) {
    	getWait().until(ExpectedConditions.elementToBeClickable(adultsNum));
    	Select adultsDropDown = new Select(adultsNum);
        adultsDropDown.selectByValue(adults.toString());
    }
    
    public void setDestination(String cruiseDest) {
    	getWait().until(ExpectedConditions.elementToBeClickable(destination));
    	Select destinationDropDown = new Select(destination);
    	destinationDropDown.selectByValue(cruiseDest);
    }
    
    public void setDate(String cruiseDate) {
    	getWait().until(ExpectedConditions.elementToBeClickable(date));
    	Select dateDropDown = new Select(date);
    	dateDropDown.selectByValue(cruiseDate);
    }
    
    public CruisesResultPage search() {
    	this.clickSearchButton();
    	return new CruisesResultPage(getDriver());
    }
}

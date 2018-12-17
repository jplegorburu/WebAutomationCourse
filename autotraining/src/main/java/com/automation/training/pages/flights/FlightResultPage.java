package com.automation.training.pages.flights;
import com.automation.training.Flight;
import com.automation.training.pages.BaseResultPage;
import com.automation.training.pages.TripDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class FlightResultPage extends BaseResultPage {

    @FindBy(id = "sortDropdown")
    private WebElement sortCriterias;

    @FindBy(css = "div[data-test-id='listing-main']")
    private List<WebElement> flightResultList;

    @FindBy(id = "xSellHotelForcedChoice-title")
    private List<WebElement> addHotelPopup;

    @FindBy(id = "forcedChoiceNoThanks")
    private WebElement noThanksButton;
    

    @FindBy(id = "selected-departure")
    private List<WebElement> selectedDeparture;

    public FlightResultPage(WebDriver driver){
        super(driver);
    }

    public List<WebElement> getSortCriterias(){
        Select sortCombo = new Select(sortCriterias);
        return sortCombo.getOptions();
    }

    public void setSortCriterias(String value){
        Select sortCombo = new Select(sortCriterias);
        sortCombo.selectByValue(value);
        getWait().until(ExpectedConditions.elementToBeClickable(sortCriterias));
    }

    public List<Flight> getResultsList(){
        //getWait().until(ExpectedConditions.elementToBeClickable(sortCriterias));
        getWait().until(ExpectedConditions.visibilityOfAllElements(flightResultList));
        return flightResultList.stream().map(f -> new Flight(f, getWait())).collect(Collectors.toList());
    }

    public TripDetailsPage goToTripDetails(){
    	String actual = getDriver().getWindowHandle();
    	String new_window = switchTab();
    	if( !new_window.equals(actual)) {
    		getDriver().close();
    		getDriver().switchTo().window(new_window);
        }
    	return new TripDetailsPage(getDriver());
    }

    public Boolean popupExists(){
        return addHotelPopup.size() > 0;
    }

    public WebElement getNoThanksButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(noThanksButton));
        return noThanksButton;
    }
    
    public Boolean departureTripSelected(){
        return selectedDeparture.size() > 1;
    }
    
}




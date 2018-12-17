package com.automation.training.pages.cruises;



import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.training.Cruises;
import com.automation.training.pages.BaseResultPage;

//destination-select id Destination
//month-select id Date
//modalCloseButton id closeModal
//length-10-14 name Checkboxfilter
public class CruisesResultPage extends BaseResultPage {

	@FindBy(id = "modalCloseButton")
    private WebElement modalClosePopup;

    @FindBy(id = "destination-select")
    private WebElement selectedDestination;

    @FindBy(id = "month-select")
    private WebElement selectedDates;
    
    //	
    @FindBy(name= "length-10-14")
    private List<WebElement> filter10to14days;

    @FindBy(className= "cruise-card-content")
    private List<WebElement> cruisesResultList;

    
    public CruisesResultPage(WebDriver driver){
        super(driver);
    }
    
    public List<Cruises> getCruisesResults(){
    	getWait().until(ExpectedConditions.visibilityOfAllElements(cruisesResultList));
        return cruisesResultList.stream().map(f -> new Cruises(f, getWait())).collect(Collectors.toList());
    }
    
    public void set10to14daysFilter() {
    	getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dismiss")));
    	filter10to14days.get(1).click();
    	getWait().until(ExpectedConditions.numberOfElementsToBe(By.className("active-modal-no-bg"), 0));
    }
    
    public WebElement getSelectedDestination() {
    	getWait().until(ExpectedConditions.elementToBeClickable(selectedDestination));
    	return selectedDestination;
    }

    public WebElement getSelectedDate() {
    	getWait().until(ExpectedConditions.elementToBeClickable(selectedDates));
    	return selectedDates;
    }
    
    public CruisesReservationPage goToReservationPage() {
    	getWait().until(ExpectedConditions.numberOfWindowsToBe(2));
    	String actual =  getDriver().getWindowHandle();
        String new_win = switchTab();
    	if( !actual.equals(new_win)) {
        	getDriver().close();
        	getDriver().switchTo().window(new_win);
        	return new CruisesReservationPage(getDriver());
        }
        return null;
    }
    
    public void closeModalPopup() {
    	getWait().until(ExpectedConditions.elementToBeClickable(modalClosePopup));
    	modalClosePopup.click();
    }

}


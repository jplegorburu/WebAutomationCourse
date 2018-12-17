package com.automation.training.pages.hotels;
import com.automation.training.Hotel;
import com.automation.training.pages.BaseResultPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HotelResultPage extends BaseResultPage {

    @FindBy(id = "resultsContainer")
    private WebElement hotelResults;

    @FindBy(id = "xSellHotelForcedChoice-title")
    private List<WebElement> addHotelPopup;

    @FindBy(id = "forcedChoiceNoThanks")
    private WebElement noThanksButton;
    
    @FindBy(className= "imgLoading")
    private WebElement loadingImage;

    @FindBy(id= "mer-signup-title-link")
    private WebElement signupLink;

    @FindBy(id= "mer-banner-signup-email")
    private WebElement signupEmail;
    
    public HotelResultPage(WebDriver driver){
        super(driver);
    }
    
    public List<Hotel> getHotelResults(){
    	pageFinishLoading();
    	getWait().until(ExpectedConditions.visibilityOf(hotelResults));
    	getWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(hotelResults, By.tagName("article")));
    	// getWait().until(ExpectedConditions.visibilityOfAllElements(hotelResults.findElements(By.tagName("article"))));
    	List<WebElement> hotels = hotelResults.findElements(By.tagName("article"));
    	hotels.removeIf(h -> h.findElements(By.className("ad")).size() > 0);
    	return hotels.stream().map(h -> new Hotel(h, getWait())).collect(Collectors.toList());
    }
    
    public void setSortCriteria(String criteria) {
    	By criteriaElement = By.cssSelector("button[data-opt-group='"+criteria+"']");
    	getWait().until(ExpectedConditions.elementToBeClickable(criteriaElement));
    	getDriver().findElement(criteriaElement).click();
    	closePopup();
    	getWait().until(ExpectedConditions.numberOfElementsToBe(By.className("active-modal-no-bg"), 0));
    }

    public HotelReservationPage goToReservationPage() {
    	String actual =  getDriver().getWindowHandle();
        String new_win = switchTab();
    	if( !actual.equals(new_win)) {
        	getDriver().close();
        	getDriver().switchTo().window(new_win);
        	return new HotelReservationPage(getDriver());
        }
        return null;
    }
    
    public void pageFinishLoading() {
    	getWait().until(ExpectedConditions.invisibilityOf(loadingImage));
    }
    
    public WebElement getSignupLink() {
    	getWait().until(ExpectedConditions.visibilityOf(signupLink));
    	return signupLink;
    }
    
    public WebElement getSignupEmail() {
    	getWait().until(ExpectedConditions.visibilityOf(signupEmail));
    	return signupEmail;
    }
}

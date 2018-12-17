package com.automation.training.pages.packages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.training.pages.BaseSearchDateTime;
import com.automation.training.pages.hotels.HotelResultPage;

public class PackageSearch extends BaseSearchDateTime {
	
	@FindBy(id = "package-rooms-plp-fh")
    private WebElement roomsNum;

    @FindBy(id = "package-origin-plp-fh")
    private WebElement fromCity;

    @FindBy(id = "package-destination-plp-fh")
    private WebElement toCity;

    @FindBy(id = "package-departing-plp-fh")
    private WebElement fromDate;

    @FindBy(id = "package-returning-plp-fh")
    private WebElement toDate;

    @FindBy(id = "package-1-adults-plp-fh")
    private WebElement adultsNum;
    
    @FindBy(id = "partialHotelBooking-plp-fh")
    private WebElement partialStay;
    
    @FindBy(id = "package-checkin-plp-fh")
    private WebElement checkinDate;

    @FindBy(id = "package-checkout-plp-fh")
    private WebElement checkoutDate;
    
    @FindBy(className = "validation-alert")
    private WebElement validationAlert;
    
    
    public PackageSearch(WebDriver driver){
        super(driver);
    }
    
    public void setRoomsNum(Integer rooms) {
    	Select roomsDropDown = new Select(roomsNum);
        roomsDropDown.selectByValue(rooms.toString());
    }

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
    
    public void setCheckinDate(String to) {
    	getWait().until(ExpectedConditions.elementToBeClickable(checkinDate));
    	checkinDate.click();
        setDate(to);
    }
    public void setCheckoutDate(String to) {
    	getWait().until(ExpectedConditions.elementToBeClickable(checkoutDate));
    	checkoutDate.click();
        setDate(to);
    }
    
    public WebElement getPartialStay() {
    	getWait().until(ExpectedConditions.elementToBeClickable(partialStay));
    	return partialStay;
    }
    
    public HotelResultPage search() {
    	clickSearchButton();
    	return new HotelResultPage(getDriver());
    }
    
    public WebElement getValidationAlert() {
    	getWait().until(ExpectedConditions.visibilityOf(validationAlert));
    	return validationAlert;
    }
    
}
package com.automation.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Hotel extends BaseElement{

    private By byHotelName = By.className("hotelTitle");
    private By byStarRating = By.className("starRating");
    private By byPrice = By.className("hotel-price");
    private By byPhone = By.className("phone");
    private By byLocation = By.className("neighborhood");
    
    public Hotel(WebElement element, WebDriverWait wdWait){
        super(element, wdWait);
    }
    
    public void selectHotel() {
        getWebElement().click();
    }

    public static Comparator<Hotel> HotelPriceComparator = (h1, h2) -> {
        Integer HotelPrice1 = getPriceNumber(h1.getPrice().substring(1));
        Integer HotelPrice2 = getPriceNumber(h2.getPrice().substring(1));
        
        //ascending order
        return HotelPrice1.compareTo(HotelPrice2);

    };
    
    private static Integer getPriceNumber(String price) {
    	String result = "";
    	for(String s:price.split(",")) {
    		result += s;
    	}
    	return Integer.parseInt(result);
    }
    
    public WebElement getHotelName() {
    	getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(getWebElement(), byHotelName));
    	return getWebElement().findElement(byHotelName);
    }
    
    public String getStarRating() {
    	getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(getWebElement(), byStarRating));
    	WebElement rating = getWebElement().findElement(byStarRating).findElement(By.className("value-title"));
    	return rating.getAttribute("title");
    }
    
    public WebElement getLocation() {
    	return getWebElement().findElement(byLocation);
    }
    
    public String getPhone() {
    	return getWebElement().findElement(byPhone).getText();
    }
    
    public String getPrice() {

    	List<WebElement> prices = getWebElement().findElement(byPrice).findElements(By.className("price"));
    	if(prices.size() > 1) {
    		return getWebElement().findElement(byPrice).findElement(By.className("actualPrice")).getText();
    	} else if (prices.size() > 0) {
    		return prices.get(0).getText();
    	}
    	return null;
    }
    
    public String getType() {
    	return getWebElement().getAttribute("data-automation");
    }

}

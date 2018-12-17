package com.automation.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

//card-price className precioLista
//strikeout-price-card className descuent
//title-on-ship-image className titleCrucer, primer palabra noches
//button[aria-label='Show itinerary'] css mostrar los dias crucero
//select-sailing-button className select Cruise

public class Cruises extends BaseElement {

    private By bySelectButton = By.className("select-sailing-button");
    private By byDiscount = By.className("strikeout-price-card");
    private By byListPrice = By.className("card-price");
    private By byTitle = By.className("title-on-ship-image");
    private By byDeparting = By.className("subtitle-on-ship-image");
    private By byItinerary = By.cssSelector("button[aria-label='Show itinerary']");
    
    public Cruises(WebElement element, WebDriverWait wdWait){
        super(element, wdWait);
    }

    public WebElement getSelectButton() {
        return getWebElement().findElement(bySelectButton);
    }

    public WebElement getBaggageDetail() {
        List<WebElement> detailsButton = getWebElement().findElements(By.className("basic-economy-tray"));
        List<WebElement> detailsLink =  getWebElement().findElements(By.cssSelector("a[data-test-id='flight-details-link']"));
        if(detailsButton.size() > 0){
            return detailsButton.get(0);
        } else if(detailsLink.size()>0){
            return detailsLink.get(0);
        }
        return null;
    }
    
    public Boolean hasDiscount() {
    	List<WebElement> discounts = getWebElement().findElements(byDiscount);
    	return discounts.size() > 0;
    }
    
    public WebElement getListPrice() {
    	return getWebElement().findElement(byListPrice);
    }

    public WebElement getDiscount() {
    	return getWebElement().findElement(byDiscount);
    }
    
    public WebElement getItinerary() {
    	return getWebElement().findElement(byItinerary);
    }
    
    public WebElement getTitle() {
    	return getWebElement().findElement(byTitle);
    }
    
    public WebElement getDeparting() {
    	return getWebElement().findElement(byDeparting);
    }
    
    public Integer getDays() {
    	return Integer.parseInt(getTitle().getText().split(" ")[0]);
    }
    
    public Integer getPrice(String price) {
    	String[] prices = price.substring(1).split(",");
    	String final_price="";
    	for(String p:prices) {
    		final_price += p;
    	}
    	return Integer.parseInt(final_price);
    }
    
    public Integer getDiscountValue() {
    	if(!hasDiscount()) {
    		return 0;
    	}
    	Integer listPrice = getPrice(getListPrice().getText());
    	Integer discountPrice = getPrice(getDiscount().getText());	
    	return (discountPrice * 10)/listPrice;
    }

    public static Comparator<Cruises> CruiseDiscountComparator = (c1, c2) -> {
        Integer CruiseDuration1 = c1.getDiscountValue();
        Integer CruiseDuration2 = c2.getDiscountValue();

        //ascending order
        return CruiseDuration2.compareTo(CruiseDuration1);
    };

}

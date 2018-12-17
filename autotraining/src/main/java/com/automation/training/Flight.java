package com.automation.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Flight extends BaseElement {

    private static final String timeFormat = "hh:mma";
    private By bySelectButton = By.cssSelector("button[data-test-id='select-button']");
    
    public Flight(WebElement element, WebDriverWait wdWait){
        super(element, wdWait);
    }

    public WebElement getSelectButton() {
    	getWait().until(ExpectedConditions.elementToBeClickable(getWebElement().findElement(bySelectButton)));
        return getWebElement().findElement(bySelectButton);
    }

    public WebElement getSelectFareButton() {
    	WebElement parent = getWebElement().findElement(By.xpath(".."));
    	if(parent.findElements(bySelectButton).size() >1) {
    		return parent.findElements(bySelectButton).get(1);
    	}
    	return getWebElement().findElements(bySelectButton).get(0);
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

    public Long getDuration(){
        WebElement departure = getWebElement().findElement(By.cssSelector(("span[data-test-id='departure-time']")));
        WebElement arrival = getWebElement().findElement(By.cssSelector(("span[data-test-id='arrival-time']")));
        if (departure != null && arrival != null) try {
            Date dep_time = new SimpleDateFormat(timeFormat, Locale.ENGLISH).parse(departure.getText());
            Date arr_time = new SimpleDateFormat(timeFormat, Locale.ENGLISH).parse(arrival.getText());

            if (getWebElement().findElements(By.cssSelector(("span[data-test-id='arrives-next-day']"))).size() > 0) {
                Calendar extra = Calendar.getInstance();
                extra.setTime(arr_time);
                extra.add(Calendar.DATE, 1);
                return extra.getTime().getTime() - dep_time.getTime();
            }
            return arr_time.getTime() - dep_time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Comparator<Flight> FlighDurationComparator = (f1, f2) -> {
        Long FlightDuration1 = f1.getDuration();
        Long FlightDuration2 = f2.getDuration();

        //ascending order
        return FlightDuration2.compareTo(FlightDuration1);

    };

}

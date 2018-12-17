package com.automation.training;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {
	private WebElement webElement;
    private WebDriverWait wait;
    
	public BaseElement(WebElement element, WebDriverWait wdWait){
        webElement = element;
        wait = wdWait;
    }

    public WebElement getWebElement(){
        return webElement;
    }
    
    public WebDriverWait getWait(){
        return wait;
    }
}

package com.automation.training.pages;


import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver pDriver){
        PageFactory.initElements(pDriver, this);
        wait = new WebDriverWait(pDriver, 30);
        driver = pDriver;
    }

    public WebDriverWait getWait(){
        return wait;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void dispose(){
        if (driver != null){
            driver.quit();
        }
    }
    
    public String switchTab() {
    	String actual =  getDriver().getWindowHandle();
        Set<String> s1 = getDriver().getWindowHandles();
        for(String window:s1){
            if(!window.equals(actual)){
                return window;
            }
        }
        return actual;
    }
    
    public void closePopup() {
    	String actual =  getDriver().getWindowHandle();
    	String new_win = switchTab();
    	if(!new_win.equals(actual)) {
    		getDriver().switchTo().window(new_win);
        	getDriver().close();
        	getDriver().switchTo().window(actual);
        }
    }

}

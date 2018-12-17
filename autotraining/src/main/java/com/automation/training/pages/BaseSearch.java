package com.automation.training.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseSearch extends BasePage {

    public BaseSearch(WebDriver driver){
        super(driver);
    }

    @FindBy(className = "gcw-submit")
    private WebElement searchButton;
    
    public void clickSearchButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

}
package com.automation.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyDriver {

    private WebDriver driver;

    public MyDriver(String browser) {
        switch (browser){
            case "firefox":
            	System.setProperty("webdriver.gecko.driver", "C:\\Users\\juan.legorburu\\Downloads\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver =  new ChromeDriver();
                break;
            default:
                break;

        }
    }

    public WebDriver getDriver(){
        return this.driver;
    }
}

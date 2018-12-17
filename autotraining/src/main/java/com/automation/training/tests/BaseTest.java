package com.automation.training.tests;

import com.automation.training.MyDriver;
import com.automation.training.pages.TravelHome;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    MyDriver myDriver;

    private TravelHome travelHome;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeSuite(String browser) {
        myDriver = new MyDriver(browser);
        travelHome = new TravelHome(myDriver.getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void afterSuite() {
        travelHome.dispose();
    }
    
    public TravelHome getTravelHome(){
        return travelHome;
    }

}
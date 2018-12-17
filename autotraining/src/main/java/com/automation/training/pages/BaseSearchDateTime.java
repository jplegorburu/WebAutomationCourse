package com.automation.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public abstract class BaseSearchDateTime extends BaseSearch {
    public BaseSearchDateTime(WebDriver driver){
        super(driver);
    }

    private static final String dateFormatDP = "dd MMM yyyy";
    private static final String dateFormatInput = "MM/dd/yyyy";

    @FindBy(className = "datepicker-prev")
    private WebElement prev;

    @FindBy(className = "datepicker-next")
    private WebElement next;

    @FindBy(className = "datepicker-cal-month-header")
    private WebElement curDate;

    @FindBy(className = "datepicker-day-number")
    private List< WebElement > dates;

    public void setDate(String date) {
        LocalDate dDate = convertToLocalDateViaInstant(getDate(date, dateFormatInput));
        long diff = this.getDateDifferenceInMonths(date);

        WebElement arrow = diff >= 0 ? next : prev;
        diff = Math.abs(diff);

        //click the arrows
        for (int i = 0; i < diff; i++)
            arrow.click();

        //select the date
        getDriver().findElement(By.cssSelector("button[data-year='"+dDate.getYear()+"']" +
                "[data-month='"+(dDate.getMonthValue()-1)+"']" +
                "[data-day='"+dDate.getDayOfMonth()+"']")).click();

    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private long getDateDifferenceInMonths(String date){

        Date curDate = getDate("01 "+this.getCurrentMonthFromDatePicker(), dateFormatDP);
        Date toDate = getDate(date, dateFormatInput);
        return YearMonth.from(convertToLocalDateViaInstant(curDate)).until(convertToLocalDateViaInstant(toDate), ChronoUnit.MONTHS);

    }

    private Date getDate(String date, String format){
        try{
            Date d = new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getCurrentMonthFromDatePicker() {
        return this.curDate.getText();
    }
}

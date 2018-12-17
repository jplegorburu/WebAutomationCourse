package com.automation.training;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class DatePicker {

    private static final String dateFormatDP = "dd MMM yyyy";
    private static final String dateFormatInput = "dd/mm/yyyy";

    @FindBy(className = "datepicker-prev")
    private WebElement prev;

    @FindBy(className = "datepicker-next")
    private WebElement next;

    @FindBy(className = "datepicker-cal-month-header")
    private WebElement curDate;

    @FindBy(className = "a.ui-state-default")
    private List< WebElement > dates;

    public void setDate(String date) {

        long diff = this.getDateDifferenceInMonths(date);
        int day = this.getDay(date);

        WebElement arrow = diff >= 0 ? next : prev;
        diff = Math.abs(diff);

        //click the arrows
        for (int i = 1; i < diff; i++)
            arrow.click();

        //select the date
        dates.stream()
                .filter(ele -> Integer.parseInt(ele.getText()) == day)
                .findFirst()
                .ifPresent(ele -> ele.click());

    }

    private int getDay(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatInput);
        LocalDate dpToDate = LocalDate.parse(date, dtf);
        return dpToDate.getDayOfMonth();
    }

    private long getDateDifferenceInMonths(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatDP);
        LocalDate dpCurDate = LocalDate.parse("01 " + this.getCurrentMonthFromDatePicker(), dtf);
        LocalDate dpToDate = LocalDate.parse(date, dtf);
        return YearMonth.from(dpCurDate).until(dpToDate, ChronoUnit.MONTHS);
    }

    private String getCurrentMonthFromDatePicker() {
        return this.curDate.getText();
    }

}
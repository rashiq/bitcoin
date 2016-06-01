package com.bitcoin.presentation.view.chart;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthUtils {

  public static List<String> getMonthSpan(long start, long end) {
    int startMonth, endMonth, startYear, endYear;
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(start * 1000L);
    startMonth = c.get(Calendar.MONTH);
    startYear = c.get(Calendar.YEAR);
    c.setTimeInMillis(end * 1000L);
    endMonth = c.get(Calendar.MONTH);
    endYear = c.get(Calendar.YEAR);

    int yearDiff = endYear - startYear;
    int monthDiff = 12 * yearDiff + (startMonth - endMonth);
    String[] monthNames = new DateFormatSymbols().getShortMonths();
    List<String> months = new ArrayList<>();
    int month = startMonth;
    for (int i = 0; i <= monthDiff; i++) {
      months.add(monthNames[month]);
      month = month == Calendar.DECEMBER ? 0 : ++month;
    }
    return months;
  }
}

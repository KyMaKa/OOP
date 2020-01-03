package ru.nsu.fit.g18214.shatalov;

import java.security.InvalidParameterException;

public class Extension {
  Calendar gen = new Calendar();

  public String yearType(int year) {
    return gen.yearType(year);
  }

  public String dayName(int day, int month, int year) {
    return gen.weekday(day,month,year);
  }

  public String monthName(int day, int month, int year) {
    return gen.Month[month];
  }

  public String whatDayWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "dayName");
  }

  public String whatDateWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "date");
  }

  public String dateDifference(int day, int month, int year, int day2, int month2, int year2) {
    return gen.differenceDate(day,month,year, day2, month2, year2, "date");
  }

  public String dateDifferenceInDays(int day, int month, int year, int day2, int month2, int year2) {
    return gen.differenceDate(day,month,year, day2, month2, year2, "day");
  }

  public String whatMonthWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "month");
  }

  public String whatYearWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day, month, year, daysPassed, "year");
  }

  public String whatDateWillBeWeeks(int day, int month, int year, int weeksPassed) {
    return gen.whatDayWillBe(day, month, year, weeksPassed * 7, "date");
  }

  public String whatDayWillBeWeeks(int day, int month, int year, int weeksPassed) {
    return gen.whatDayWillBe(day, month, year, weeksPassed * 7, "day");
  }

  public String findNearest(int day, int month, int year, int dayToFind, String dayToFindName) {
    return gen.whenFirst(day, month, year, dayToFind, dayToFindName);
  }

  public static void main(String[] args) {
    Extension ex = new Extension();
    System.out.println(ex.findNearest(3, 1, 2020, 13, "Friday"));
  }
}

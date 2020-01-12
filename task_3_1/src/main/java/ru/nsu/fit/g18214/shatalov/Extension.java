package ru.nsu.fit.g18214.shatalov;

public class Extension {
  Calendar gen = new Calendar();

  /**
   * Calculate type of year (leap of default).
   * @param year - decimal representation of year.
   * @return string that says type of year (all in lower case).
   */
  public String yearType(int year) {
    return gen.yearType(year);
  }

  /**
   * Calculates name of day in week.
   * @param day - decimal representation of number of day in month.
   * @param month - decimal representation of number of month in year.
   * @param year - decimal representation of year.
   * @return string with name of day in week (first letter in upper case).
   */
  public String dayName(int day, int month, int year) {
    return gen.weekday(day,month,year);
  }

  /**
   * .
   * @param month - decimal representation of number in year.
   * @return - name of month (first letter in upper case).
   */
  public String monthName(int month) {
    return Calendar.Month[month - 1];
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param daysPassed - number of days passed since given date.
   * @return string with name of the day in week (first letter in upper case).
   */
  public String whatDayWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "dayName");
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param daysPassed - number of days passed since given date.
   * @return string with full date (format: dd/mm/yyyy, dd and mm may contain single character).
   */
  public String whatDateWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "date");
  }

  /**
   * Calculate number of days or days, months and year between two dates.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param day2 - decimal number of day in mouth.
   * @param month2 - decimal number of month in year.
   * @param year2 - decimal representation of year.
   * First three parameters (day1, month1, year1) its a date before ->
   * -> date of second three parameters (day2, month2, year2)
   * @return string with number of days, months and years between two dates ->
   * -> (format: dd/mm/yyyy, dd and mm may contain single character.
   */
  public String dateDifference(int day, int month, int year, int day2, int month2, int year2) {
    return gen.differenceDate(day,month,year, day2, month2, year2, "date");
  }

  /**
   * Calculate number of days or days, months and year between two dates.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param day2 - decimal number of day in mouth.
   * @param month2 - decimal number of month in year.
   * @param year2 - decimal representation of year.
   * First three parameters (day1, month1, year1) its a date before ->
   * -> date of second three parameters (day2, month2, year2)
   * @return string with number of days between two dates.
   */
  public String dateDifferenceInDays(int day, int month, int year, int day2, int month2, int year2) {
    return gen.differenceDate(day,month,year, day2, month2, year2, "day");
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param daysPassed - number of days passed since given date.
   * @return string with name of the month (first letter in upper case).
   */
  public String whatMonthWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day,month,year, daysPassed, "month");
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param daysPassed - number of days passed since given date.
   * @return string with number of year (ex: 2020).
   */
  public String whatYearWillBe(int day, int month, int year, int daysPassed) {
    return gen.whatDayWillBe(day, month, year, daysPassed, "year");
  }
  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param weeksPassed - number of weeks passed since given date.
   * @return string with full date (format: dd/mm/yyyy, dd and mm may contain single character).
   */
  public String whatDateWillBeWeeks(int day, int month, int year, int weeksPassed) {
    return gen.whatDayWillBe(day, month, year, weeksPassed * 7, "date");
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param weeksPassed - number of days passed since given date.
   * @return string with name of the day in week (first letter in upper case).
   */
  public String whatDayWillBeWeeks(int day, int month, int year, int weeksPassed) {
    return gen.whatDayWillBe(day, month, year, weeksPassed * 7, "dayName");
  }

  /**
   * Calculates nearest date with given number of day in month and name of day in week.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param dayToFind - number of day in month for search.
   * @param dayToFindName - name of the day of week.
   * @return string with full date (format: dd/mm/yyyy, dd and mm may contain single character).
   */
  public String findNearest(int day, int month, int year, int dayToFind, String dayToFindName) {
    return gen.whenFirst(day, month, year, dayToFind, dayToFindName);
  }
}

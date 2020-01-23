package ru.nsu.fit.g18214.shatalov;

import java.security.InvalidParameterException;

public class Calendar {

  protected static final String[] Month = {
      "January", "February",
      "March", "April",
      "May", "June",
      "July", "August",
      "September", "October",
      "November", "December"
  };
  int febDays;
  /**
   * Calculate type of year (Leap of default).
   * @param year - decimal representation of year.
   * @return string that says type of year.
   */
  protected String yearType(int year){
    if (year % 4 != 0) {
      return "default";
    }
    if (year % 100 == 0 && year % 400 != 0) {
      return "default";
    }
    else {
      return "leap";
    }
  }

  /**
   * Calculate what day of week will be or was on given date.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @return string with name of day of week.
   */
  protected String weekday(int day, int month, int year) {
    String dayName;
    if (month < 1 || month > 12) {
      throw new InvalidParameterException("Incorrect month");
    }
    if (month < 3) {
      --year;
      month += 10;
    } else
      month -= 2;
    int weekDay = ((day + 31 * month / 12 + year + year / 4 - year / 100 + year / 400) % 7);
    switch (weekDay) {
      case (1):
        dayName = "Monday";
        break;
      case (2):
        dayName = "Tuesday";
        break;
      case (3):
        dayName = "Wednesday";
        break;
      case (4):
        dayName = "Thursday";
        break;
      case (5):
        dayName = "Friday";
        break;
      case (6):
        dayName = "Saturday";
        break;
      case (0):
        dayName = "Sunday";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + weekDay);
    }
    return dayName;
  }

  protected String countDate(int day, int month, int year) {
    if (month == 12 && day > 31) {
      month = 1;
      day = 1;
      year++;
      if (yearType(year) == "leap") {
        febDays = 29;
      } else {
        febDays = 28;
      }
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
      month++;
      day = 1;
    } else {
      if (month == 2 && day > febDays) {
        month++;
        day = 1;
      } else {
        if (day > 31) {
          month++;
          day = 1;
        }
      }
    }
    return day + "/" + month + "/" + year;
  }

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param dayPassed - number of days passed since given date.
   * @param whatReturn - accepts string with what type of information method will return. (month, year, date, dayName)
   * @return string with name of month, year, full date or name of the day in week.
   */
  protected String whatDayWillBe(int day, int month, int year, int dayPassed, String whatReturn) {
    if (day < 0 || day > 31 || month < 0 || month > 12 || dayPassed < 0) {
      throw new InvalidParameterException("Invalid date");
    }
    if (yearType(year) == "leap") {
      febDays = 29;
    } else {
      febDays = 28;
    }
    while (dayPassed-- != 0) {
      day++;
      String tmp = countDate(day, month, year);
      Integer[] arr = toInt(tmp);
      day = arr[0];
      month = arr[1];
      year = arr[2];
    }

    if (whatReturn == "date") {
      return day + "/" + month + "/" + year;
    } else {
      if (whatReturn == "dayName") {
        return weekday(day, month, year);
      } else {
        if (whatReturn == "month") {
          return Month[month - 1];
        } else {
          if (whatReturn == "year") {
            return "" + year;
          } else {
            throw new InvalidParameterException("Invalid return parameter");
          }
        }
      }
    }
  }

  /**
   * Calculate number of days or days, months and year between two dates.
   * @param day1 - decimal number of day in mouth.
   * @param month1 - decimal number of month in year.
   * @param year1 - decimal representation of year.
   * @param day2 - decimal number of day in mouth.
   * @param month2 - decimal number of month in year.
   * @param year2 - decimal representation of year.
   * First three parameters (day1, month1, year1) its a date before ->
   * -> date of second three parameters (day2, month2, year2)
   * @param whatReturn - accepts string with what type of information method will return. (date, day)
   * @return string with number of days between two dates or number of days, months and years between two dates.
   */
  protected String differenceDate(int day1, int month1, int year1, int day2, int month2, int year2, String whatReturn) {
    if (day1 < 0 || day1 > 31 || month1 < 0 || month1 > 12
        || day2 < 0 || day2 > 31 || month2 < 0 || month2 > 12 || year1 > year2) {
      throw new InvalidParameterException("Invalid date");
    }
    int dayPassed = 0;
    /* int daysB = 0, monthsB = 0, yearsB = 0;*/
    if (yearType(year1) == "leap") {
      febDays = 29;
    } else {
      febDays = 28;
    }
    while (true) {
      if ((year1 == year2) && (month1 == month2) && (day1 == day2)) {
        break;
      }
      day1++;
      dayPassed++;
      String tmp = countDate(day1, month1, year1);
      Integer[] arr = toInt(tmp);
      day1 = arr[0];
      month1 = arr[1];
      year1 = arr[2];
    }

    if (whatReturn == "day") {
      return "" + dayPassed;
    } else {
      if (whatReturn == "date") {
        return whatDayWillBe(0, 0, 0, dayPassed, "date");
      } else {
        throw new InvalidParameterException("Invalid return parameter");
      }
    }
  }

  /**
   * Calculates nearest date with given number of day in month and name of day in week.
   * @param day1 - decimal number of day in mouth.
   * @param month1 - decimal number of month in year.
   * @param year1 - decimal representation of year.
   * @param day2 - number of day in month for search.
   * @param dayName - name of the day of week.
   * @return full date.
   */
  protected String whenFirst(int day1, int month1, int year1, int day2, String dayName) {
    if (day1 < 0 || day1 > 31 || month1 < 0 || month1 > 12 || day2 < 0) {
      throw new InvalidParameterException("Invalid date");
    }
    if (yearType(year1) == "leap") {
      febDays = 29;
    } else {
      febDays = 28;
    }
    while (true) {
      if ((day1 == day2) && (weekday(day1, month1, year1) == dayName)) {
        break;
      }
      day1++;
      String tmp = countDate(day1, month1, year1);
      Integer[] arr = toInt(tmp);
      day1 = arr[0];
      month1 = arr[1];
      year1 = arr[2];
    }
    return day1 +"/" + month1 + "/" + year1;
  }

  protected Integer[] toInt(String date) {
    String hlp;
    hlp = date.substring(0, date.indexOf("/"));
    int day1 = Integer.parseInt(hlp);
    hlp = date.substring(date.indexOf("/") + 1, date.indexOf("/", date.indexOf("/") + 1));
    int month1 = Integer.parseInt(hlp);
    hlp = date.substring(date.indexOf("/", date.indexOf("/") + 1) + 1);
    int year1 = Integer.parseInt(hlp);
    Integer[] arr = {day1, month1, year1};
    return arr;
  }

  protected String toString(int day, int month, int year) {
    return day + "/" + month + "/" + year;
  }
}

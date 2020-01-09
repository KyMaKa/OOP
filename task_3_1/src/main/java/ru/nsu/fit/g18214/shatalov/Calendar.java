package ru.nsu.fit.g18214.shatalov;

import java.security.InvalidParameterException;

public class Calendar {

  public static final String[] Month = {
      "January", "February",
      "March", "April",
      "May", "June",
      "July", "August",
      "September", "October",
      "November", "December"
  };

  /**
   * Calculate type of year (Leap of default).
   * @param year - decimal representation of year.
   * @return string that says type of year.
   */
  public String yearType(int year){
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
  public String weekday(int day, int month, int year) {
    String dayName;
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

  /**
   * Calculate what day will be after given number of days passed.
   * @param day - decimal number of day in mouth.
   * @param month - decimal number of month in year.
   * @param year - decimal representation of year.
   * @param dayPassed - number of days passed since given date.
   * @param whatReturn - accepts string with what type of information method will return. (month, year, date, dayName)
   * @return string with name of month, year, full date or name of the day in week.
   */
  public String whatDayWillBe(int day, int month, int year, int dayPassed, String whatReturn) {
    int febDays;
    if (yearType(year) == "leap") {
      febDays = 29;
    } else {
      febDays = 28;
    }
    while (dayPassed-- != 0) {
      day++;
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
    }
    /*System.out.print(day + ":" + month + ":" + year);
    System.out.println();*/
    if (whatReturn == "date") {
      return day + ":" + month + ":" + year;
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
  public String differenceDate(int day1, int month1, int year1, int day2, int month2, int year2, String whatReturn) {
    int febDays;
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
      if (month1 == 12 && day1 > 31) {
        month1 = 1;
        day1 = 1;
        year1++;
        if (yearType(year1) == "leap") {
          febDays = 29;
        } else {
          febDays = 28;
        }
      }
      if ((month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) && day1 > 30) {
        month1++;
        day1 = 1;
      } else {
        if (month1 == 2 && day1 > febDays) {
          month1++;
          day1 = 1;
        } else {
          if (day1 > 31) {
            month1++;
            day1 = 1;
          }
        }
      }
    }
    /*System.out.println(whatDayWillBe(0,0,0, dayPassed, "date"));
    System.out.print(daysB + ":" + monthsB + ":" + yearsB);
    System.out.println();*/
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
  public String whenFirst(int day1, int month1, int year1, int day2, String dayName) {
    int febDays;
    /* int daysB = 0, monthsB = 0, yearsB = 0;*/
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
      if (month1 == 12 && day1 > 31) {
        month1 = 1;
        day1 = 1;
        year1++;
        if (yearType(year1) == "leap") {
          febDays = 29;
        } else {
          febDays = 28;
        }
      }
      if ((month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) && day1 > 30) {
        month1++;
        day1 = 1;
      } else {
        if (month1 == 2 && day1 > febDays) {
          month1++;
          day1 = 1;
        } else {
          if (day1 > 31) {
            month1++;
            day1 = 1;
          }
        }
      }
    }
    return day1 +":" + month1 + ":" + year1;
  }
}

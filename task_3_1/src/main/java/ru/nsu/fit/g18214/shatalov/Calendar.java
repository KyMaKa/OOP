package ru.nsu.fit.g18214.shatalov;

import java.security.InvalidParameterException;

public class Calendar {

/*
  public static final int January = 1;
  public static final int February = 2;
  public static final int March = 3;
  public static final int April = 4;
  public static final int May = 5;
  public static final int June = 6;
  public static final int July = 7;
  public static final int August = 8;
  public static final int September = 9;
  public static final int October = 10;
  public static final int November = 11;
  public static final int December = 12;
*/

  public static final String[] Month = {
      "January", "February",
      "March", "April",
      "May", "June",
      "July", "August",
      "September", "October",
      "November", "December"
  };

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
          return Month[month];
        } else {
          if (whatReturn == "year") {
            return Month[year];
          } else {
            throw new InvalidParameterException("Invalid return parameter");
          }
        }
      }
    }
  }

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

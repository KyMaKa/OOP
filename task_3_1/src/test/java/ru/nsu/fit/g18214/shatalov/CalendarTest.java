package ru.nsu.fit.g18214.shatalov;

import org.junit.Assert;
import org.junit.Test;

public class CalendarTest {
  Extension cal = new Extension();

  @Test
  public void testYearType() {
    Assert.assertEquals("leap", cal.yearType(2020));
  }

  @Test
  public void testDayName() {
    Assert.assertEquals("Thursday", cal.dayName(9, 1, 2020));
  }

  @Test
  public void testMonthName() {
    Assert.assertEquals("January", cal.monthName(9, 1, 2020));
  }

  @Test
  public void testWhatDayWillBe() {
    Assert.assertEquals("Tuesday", cal.whatDayWillBe(9, 1, 2020, 2000));
  }

  @Test
  public void testWhatDateWillBe() {
    Assert.assertEquals("1:7:2025", cal.whatDateWillBe(9,1,2020, 2000));
  }

  @Test
  public void testDateDiff() {
    Assert.assertEquals(cal.whatDateWillBe(0, 0, 0, 27273), cal.dateDifference(9, 5, 1945, 9, 1, 2020));
  }

  @Test
  public void testDateDiffDays() {
    Assert.assertEquals("27273", cal.dateDifferenceInDays(9,5,1945,9,1,2020));
  }

  @Test
  public void testWhatMonthWillBe() {
    Assert.assertEquals("July", cal.whatMonthWillBe(9, 1, 2020, 2000));
  }

  @Test
  public void testWhatYearWillBe() {
    Assert.assertEquals("2025", cal.whatYearWillBe(9, 1, 2020, 2000));
  }

  @Test
  public void testWhatDateWillBeWeeks() {
    Assert.assertEquals("28:5:2020", cal.whatDateWillBeWeeks(9, 1, 2020, 20));
  }

  @Test
  public void testWhatDayWillBeWeeks() {
    Assert.assertEquals("Thursday", cal.whatDayWillBeWeeks(9, 1, 2020, 20));
  }

  @Test
  public void testFindNearest() {
    Assert.assertEquals("13:3:2020",
        cal.findNearest(9, 1, 2020, 13, "Friday"));
  }
}

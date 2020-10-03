package ru.shatalov.cft_test_task;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test
  public void addition_isCorrect() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void readTest() throws IOException {
    ReadJson readJson = new ReadJson();
    readJson.readCurrencyList();
    System.out.println("--");
    /*readJson.currencies.forEach(curr -> {
      System.out.println(curr.getCharCode());
    });*/
    System.out.println(readJson.currencies.get(3).getCharCode());
    System.out.println("--");
  }
}
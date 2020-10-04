package ru.shatalov.cft_test_task;

//Используется jackson просто потому что я уже знаком с ним и использовал ранее.
//Является одним из популярнейших инструментов для работы с JSON файлами.
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadFactory;

public class ReadJson implements ThreadFactory {

  final protected String source = "https://www.cbr-xml-daily.ru/daily_json.js";
  private final ObjectMapper objectMapper = new ObjectMapper();
  protected ArrayList<Currency> currencies = new ArrayList<>();

  /**
   * Reads and stores info about each currency in Currency.class ->
   * -> and creates list with this classes.
   * @throws IOException if can't access source.
   */
  protected void readCurrencyList() throws IOException {
    objectMapper.configure(
        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    URL url = new URL(source);

    JsonNode valute = objectMapper.readTree(url).get("Valute"); //get field with currencies

    Iterator<JsonNode> iter = valute.iterator(); //get iterator to navigate inside JsonNode

    while (iter.hasNext()) { //while have next currency -> move and store info
      valute = iter.next();
      Currency currency = new Currency();
      currency.setNumCode(valute.get("NumCode").asInt());
      currency.setCharCode(valute.get("CharCode").asText());
      currency.setNominal(valute.get("Nominal").asInt());
      currency.setName(valute.get("Name").asText());
      currency.setValue(valute.get("Value").asDouble());
      this.currencies.add(currency);
    }
  }

  public ArrayList<Currency> getCurrencies() {
    return this.currencies;
  }

  //Creates new thread.
  public Thread newThread(Runnable r) {
    Thread thread = new Thread(r);
    thread.setPriority(5);
    return thread;
  }

}

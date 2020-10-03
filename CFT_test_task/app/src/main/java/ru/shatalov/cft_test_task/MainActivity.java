package ru.shatalov.cft_test_task;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  private TextView message;
  //protected static BlockingDeque<Currency> currencies = new LinkedBlockingDeque<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    message = findViewById(R.id.textView);
    try {
      showCurrencies();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }



  private void showCurrencies() throws InterruptedException {
    StringBuilder text = new StringBuilder();
    ReadJson readJson = new ReadJson();

    Runnable runnable = () -> {
      try {
        readJson.readCurrencyList();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };

    Thread thread = readJson.newThread(runnable);
    thread.start();
    thread.join(); //make sure that thread execution is finished
    for (Currency currency : readJson.currencies) {
      text.append(" ").append(currency.getCharCode());
    }
    message.setText(text);
    //System.out.println(currencies.get(2).getCharCode());
  }
}
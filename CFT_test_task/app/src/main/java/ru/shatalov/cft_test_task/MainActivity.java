package ru.shatalov.cft_test_task;

import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  //private TextView message;
  private String[] listItems;
  private ReadJson readJson;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //message = findViewById(R.id.textView);


    try {
      showCurrencies();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Currency[] currencies = new Currency[readJson.currencies.size()];
    currencies = readJson.getCurrencies().toArray(currencies);

    MyListAdapter adapter = new MyListAdapter(this, currencies, listItems);
    ListView listView = findViewById(R.id.listView);
    listView.setAdapter(adapter);

  }



  private void showCurrencies() throws InterruptedException {
    readJson = new ReadJson();
    ArrayList<String> arrayList = new ArrayList<>();

    //create thread to access source file and get info form it
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
      arrayList.add(currency.getCharCode());
    }
    listItems = new String[arrayList.size()];
    listItems = arrayList.toArray(listItems);
    //message.setText("Currencies");
  }
}
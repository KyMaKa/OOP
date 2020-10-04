package ru.shatalov.cft_test_task;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private String[] listItems;
  private ReadJson readJson;
  private Context context;
  protected static Currency[] currencies;


  /**
   * Creates first and main window of application.
   * Calls method showCurrencies() that creates starts new thread ->
   * -> to access daily_json.js file by URL.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView message = findViewById(R.id.textView);
    message.setText("Name");

    try {
      showCurrencies();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    currencies = new Currency[readJson.currencies.size()];
    currencies = readJson.getCurrencies().toArray(currencies);


    //Renders custom ListView
    MyListAdapter adapter = new MyListAdapter(this, currencies, listItems);
    ListView listView = findViewById(R.id.listView);
    listView.setAdapter(adapter);
    context = this;

    //Registers tap on some list item and creates custom activity as dialog
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent activityDialog = new Intent(context, ActivityDialog.class);
        activityDialog.putExtra("position", position);
        startActivity(activityDialog, savedInstanceState);

      }
    });
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
  }
}
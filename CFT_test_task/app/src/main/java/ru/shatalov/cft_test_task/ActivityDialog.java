package ru.shatalov.cft_test_task;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class ActivityDialog extends AppCompatActivity {

  //boolean isOn = false;
  EditText valueRight;
  TextView valueLeft;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.convertion_dialog);

    valueRight = findViewById(R.id.valueRight);
    valueLeft = findViewById(R.id.valueLeft);
    valueRight.setInputType(InputType.TYPE_CLASS_NUMBER);
    //valueLeft.setInputType(InputType.TYPE_NULL);

    setTitle("  Conversion");
    Intent intent = getIntent();
    int position = intent.getIntExtra("position",0);
    setCurrency(position);

    Button convert = findViewById(R.id.convertButton);

    convert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculateValue(position);
      }
    });

  }

  public void setCurrency(int position) {

    TextView left = findViewById(R.id.currencyLeft);
    TextView right = findViewById(R.id.currencyRight);
    String[] val = getResources().getStringArray(R.array.array_currencies);

    right.setText("RUB");
    left.setText(val[position]);

  }

  private void calculateValue(int position) {
    System.out.println(valueRight.getText().toString());
    double vR = Double.parseDouble(valueRight.getText().toString());
    valueLeft.setText(vR / (MainActivity.currencies[position].getValue() / MainActivity.currencies[position].getNominal()) + "");
  }
}
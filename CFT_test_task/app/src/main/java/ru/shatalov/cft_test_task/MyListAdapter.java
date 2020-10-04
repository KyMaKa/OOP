package ru.shatalov.cft_test_task;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {

  private final Activity context;
  private final Currency[] currencies;

  /**
   * Creates custom adapter for ListView.
   */
  public MyListAdapter(Activity context, Currency[] currencies, String[] currencyChar) {
    super(context, R.layout.mylist, currencyChar);
    this.context = context;
    this.currencies = currencies;
  }
  public View getView(int position, View view, ViewGroup parent) {
    LayoutInflater inflater = context.getLayoutInflater();
    View rowView = inflater.inflate(R.layout.mylist, null, true);

    TextView titleChar = rowView.findViewById(R.id.currencyChar);
    TextView titleName =  rowView.findViewById(R.id.currencyName);
    TextView titleValue = rowView.findViewById(R.id.currencyValue);
    TextView titleNominal = rowView.findViewById(R.id.currencyNominal);

    titleChar.setText(currencies[position].getCharCode());
    titleName.setText(currencies[position].getName());
    titleValue.setText(currencies[position].getValue() + "");
    titleNominal.setText("x" + currencies[position].getNominal());

    return rowView;
  }

}

package ru.shatalov.cft_test_task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import org.w3c.dom.Text;

public class ConversionDialog extends DialogFragment {
  boolean switchOn = false;
  View view;

  @NonNull
  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public Dialog onCreateDialog(Bundle savedInstance) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = requireActivity().getLayoutInflater();
    view = inflater.inflate(R.layout.convertion_dialog,null, true);

    builder.setView(view);
    builder.setPositiveButton("Convert", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        //clicked convert

      }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        //clicked cancel
        dialog.cancel();
      }
    });

    AlertDialog dialog = builder.create();

    return dialog;
  }

  public void switchCurrency(Switch aSwitch) {
    if (aSwitch.isChecked()) {
      switchOn = true;
    }
  }

  public void setCurrency(int position) {

    TextView left = view.findViewById(R.id.currencyLeft);
    TextView right = view.findViewById(R.id.currencyRight);
    String[] val = getResources().getStringArray(R.array.array_currencies);

    right.setText("RUB");
    left.setText(val[position]);

  }
}

package com.example.infoapps;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SkelettGrosse extends Activity {

	EditText alterTxt, schenkelTxt;
	Button go;
	RadioGroup geschlecht;
	RadioButton mann, frau;
	TextView outputTxt;
	float grosse, schenkel;
	int alter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skelettgrosse);

		Initialize();

		KillOtherRadio();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (alterTxt.length() > 0 && schenkelTxt.length() > 0) {

					schenkel = Float.parseFloat(schenkelTxt.getText()
							.toString());
					alter = Integer.parseInt(alterTxt.getText().toString());

					if (mann.isChecked()) {
						grosse = (float) (69.089 + 2.238 * schenkel);

					}
					if (frau.isChecked()) {
						grosse = (float) (61.412 + 2.317 * schenkel);

					}
					if (Integer.parseInt(alterTxt.getText().toString()) > 30) {
						grosse = (float) (grosse - ((alter - 30) * 0.06));
					}
					DecimalFormat df = new DecimalFormat("###");
					double ausgabeLong = (double) grosse;
					String la = df.format(ausgabeLong);
					outputTxt.setText("Grosse: " + df.format(ausgabeLong)
							+ "cm");

					// outputTxt.setText(Float.toString(grosse) + "cm");
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(schenkelTxt.getWindowToken(),
							0);
				}
			}
		});
	}

	private void KillOtherRadio() {
		// TODO Auto-generated method stub
		mann.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frau.setChecked(false);
			}
		});
		frau.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mann.setChecked(false);
			}
		});
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		alterTxt = (EditText) findViewById(R.id.skelettAlter);
		schenkelTxt = (EditText) findViewById(R.id.skelettOberschenkel);
		go = (Button) findViewById(R.id.skelettGo);
		geschlecht = (RadioGroup) findViewById(R.id.skelettGeschlecht);
		mann = (RadioButton) findViewById(R.id.skelettMann);
		frau = (RadioButton) findViewById(R.id.skelettFrau);
		outputTxt = (TextView) findViewById(R.id.skelettOutput);
	}

}

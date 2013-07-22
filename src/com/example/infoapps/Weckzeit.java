package com.example.infoapps;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class Weckzeit extends Activity {

	EditText jetztStdTxt, jetztMinTxt, schlafStdTxt, schlafMinTxt;
	TimePicker timePicker;
	TextView finalTime;
	int jetztStunde, jetztMinute, schlafStunde, schlafMinute, totalStunde,
			totalMin;
	Button go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weckzeit);
		Initialize();

		PrintTimeToET();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (jetztMinTxt.length() > 0 && jetztMinTxt.length() > 0
						&& schlafStdTxt.length() > 0
						&& schlafMinTxt.length() > 0) {
					go.setText("Go");
					go.setTextColor(Color.BLACK);

					GetAllInfo();

					Calculation();

					String totalMinStr;

					if (totalMin < 10)
						totalMinStr = "0" + Integer.toString(totalMin);
					else
						totalMinStr = Integer.toString(totalMin);

					finalTime.setText(Integer.toString(totalStunde) + "  :  "
							+ totalMinStr);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(schlafMinTxt.getWindowToken(),
							0);
				}

				else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
			}
		
		});

	}

	private void Calculation() {
		// TODO Auto-generated method stub
		int calStd = jetztStunde + schlafStunde;
		int calMin = jetztMinute + schlafMinute;

		totalMin = calMin % 60;
		totalStunde = calStd % 24 + calMin / 60;

	}

	private void GetAllInfo() {
		// TODO Auto-generated method stub
		jetztStunde = Integer.parseInt(jetztStdTxt.getText().toString());
		jetztMinute = Integer.parseInt(jetztMinTxt.getText().toString());

		schlafStunde = Integer.parseInt(schlafStdTxt.getText().toString());
		schlafMinute = Integer.parseInt(schlafMinTxt.getText().toString());

	}

	private void PrintTimeToET() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		jetztStdTxt.setText(Integer.toString(c.get(Calendar.HOUR_OF_DAY)));
		jetztMinTxt.setText(Integer.toString(c.get(Calendar.MINUTE)));

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		jetztStdTxt = (EditText) findViewById(R.id.weckJetztStund);
		jetztMinTxt = (EditText) findViewById(R.id.weckJetztMin);
		schlafStdTxt = (EditText) findViewById(R.id.weckSchlafStd);
		schlafMinTxt = (EditText) findViewById(R.id.weckSchlafMin);
		finalTime = (TextView) findViewById(R.id.weckFinalTime);
		go = (Button) findViewById(R.id.weckGo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weckzeit, menu);
		return true;
	}

}

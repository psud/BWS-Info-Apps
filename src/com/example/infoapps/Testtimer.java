package com.example.infoapps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Testtimer extends Activity {

	TextView hTextView;
	Button hButton, hButtonStop;
	private Handler mHandler = new Handler();
	private int nCounter = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testtimer);
		hTextView = (TextView) findViewById(R.id.idTextView);
		hButton = (Button) findViewById(R.id.idButton);
		hButton.setOnClickListener(mButtonStartListener);
		hButtonStop = (Button) findViewById(R.id.idButtonStop);
		hButtonStop.setOnClickListener(mButtonStopListener);
	} // end onCreate

	OnClickListener mButtonStartListener = new OnClickListener() {
		public void onClick(View v) {
			try {
				mHandler.removeCallbacks(hMyTimeTask);
				// Parameters
				// r The Runnable that will be executed.
				// delayMillis The delay (in milliseconds) until the Runnable
				// will be executed.
				mHandler.postDelayed(hMyTimeTask, 1000); // delay 1 second
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	private Runnable hMyTimeTask = new Runnable() {
		public void run() {
			nCounter++;
			hTextView.setText("Hallo from thread counter: " + nCounter);
		}
	};
	
	OnClickListener mButtonStopListener = new OnClickListener() {
		public void onClick(View v) {
			mHandler.removeCallbacks(hMyTimeTask);

		}
	};
}

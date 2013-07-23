package com.example.infoapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WaagerechterWurf extends Activity{

	EditText hoheTxt, zeitTxt, speedTxt;
	TextView output, outputXY;
	Button go;
	float hohe, zeit, speed;
	float x, y;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waagerechterwurf);
		
		
		Initialize();
		
		go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues();
				
				Calculate();
				
				Output();
				
			}
		});
		
		
		
	}

	protected void Output() {
		// TODO Auto-generated method stub
		output.setText(Float.toString(x) + "    " + Float.toString(y));
		outputXY.setText("X      Y");
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(speedTxt.getWindowToken(), 0);
	}

	private void GetValues() {
		// TODO Auto-generated method stub
		hohe = Float.parseFloat(hoheTxt.getText().toString());
		zeit = Float.parseFloat(zeitTxt.getText().toString());
		speed = Float.parseFloat(speedTxt.getText().toString());
	}

	private void Calculate() {
		// TODO Auto-generated method stub
		 y = (float) (hohe - 0.5 * 9.81 * zeit  * zeit);
		 x = speed * zeit;
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		hoheTxt = (EditText) findViewById(R.id.waagwurfHohe);
		zeitTxt = (EditText) findViewById(R.id.waagwurfZeit);
		speedTxt = (EditText) findViewById(R.id.waagwurfSpeed);
		output = (TextView) findViewById(R.id.waagwurfTvoutput);
		go = (Button) findViewById(R.id.waagwurfGo);
		outputXY = (TextView) findViewById(R.id.waagwurfTvoutputXY);
		
	}


}

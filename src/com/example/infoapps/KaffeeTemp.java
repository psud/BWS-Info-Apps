package com.example.infoapps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KaffeeTemp extends Activity {

	EditText kaffTempTxt, zimmTempTxt;
	Button go;
	TextView outputTxt;
	String output;
	float kaffTemp, zimmerTemp;
	int min;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaffeetemp);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (kaffTempTxt.length() > 0 && zimmTempTxt.length() > 0) {
					go.setText("Go");
					go.setTextColor(Color.BLACK);

					output = "";
					min = 0;

					kaffTemp = Float.parseFloat(kaffTempTxt.getText()
							.toString());
					zimmerTemp = Float.parseFloat(zimmTempTxt.getText()
							.toString());

					if (kaffTemp > zimmerTemp + 0.1) {
						while (kaffTemp >= zimmerTemp + 0.1) {
							output += "Nach " + Integer.toString(min)
									+ " min ist Kaffee "
									+ Float.toString(kaffTemp) + " Grad warm\n";
							;
							kaffTemp = kaffTemp
									- ((kaffTemp - zimmerTemp) / 10);
							min = min + 1;
						}
						outputTxt.setText(output.substring(0,
								output.length() - 1));
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(
								zimmTempTxt.getWindowToken(), 0);
					} else
						outputTxt.setText("Kaffeetemp muss hoeher als Zimmertemp sein");

				} else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
			}
		});

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		kaffTempTxt = (EditText) findViewById(R.id.kaffeetempTemp);
		zimmTempTxt = (EditText) findViewById(R.id.kaffeetempZimmer);
		go = (Button) findViewById(R.id.kaffeetempGo);
		outputTxt = (TextView) findViewById(R.id.kaffeetempOutput);
	}

}

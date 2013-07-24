package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuInflater;
import android.view.MenuItem;
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
						outputTxt
								.setText("Kaffeetemp muss hoeher als Zimmertemp sein");

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

	// //////////Show Aufgabenstellung
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.aufgabe, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String aufgabeNum = "Blatt 2 Aufgabe 2";
	    String realClassName = this.getClass().getName().substring(21);
	   String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Eine Tasse Kaffee hat eine Temperatur von 85°C. Die Zimmertemperatur beträgt 21°C."
					+ "In jeder Minute verringert sich die Temperatur des Kaffees um ein Zehntel der Differenz zwischen beiden"
					+ "Temperaturen. Schreiben Sie ein Programm, dass die Kaffeetemperatur nach 1, 2, 3, ... Minuten ausgibt, bis der"
					+ "Unterschied weniger als ein 1°C beträgt.";
			d.setTitle(aufgabe);
			tvAufgabe.setText(aufgabe + " - "+ aufgabeNum +"\n\n" + aufgabeText);
			d.setContentView(tvAufgabe);
			d.show();
			break;

		case R.id.bug:		
			Bundle sendClassName = new Bundle();
			sendClassName.putString("bugClass",realClassName);
			sendClassName.putString("bugNum", aufgabeNum);
			Intent bugSend = new Intent(this, BugSubmit.class); 
			bugSend.putExtras(sendClassName);
			startActivity(bugSend);
			
			break;
		
		case R.id.code:			
				Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
	            myWebLink.setData(Uri.parse("https://github.com/psud/BWS-Info-Apps/blob/master/src/com/example/infoapps/"+realClassName+".java"));
	                startActivity(myWebLink);
				break;
				}
		return false;

	}
}

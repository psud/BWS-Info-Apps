package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BusFahrtkosten extends Activity {

	EditText distanzTxt, teilnehmerTxt;
	Button go;
	TextView outputTxt;
	int teilnehmer, freiFahrer;
	float km, preisPKm, PreisNormal, RabattTotal, vergPreis, special1st,
			DurschnittK, DurschnittKRabatt, nachRabatt;
	String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busfahrtkosten);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (distanzTxt.length()>0&&teilnehmerTxt.length()>0){
					go.setText("Go");
					go.setTextColor(Color.BLACK);
				// TODO Auto-generated method stub
				GetInfo();

				Calculate();

				output = "Preis: " + Float.toString(PreisNormal) + "€\n";

				CalculateMore10();

				Output();

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(teilnehmerTxt.getWindowToken(), 0);
				}
				else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
			}
		});
	}

	protected void CalculateMore10() {
		// TODO Auto-generated method stub
		// Rabatt ausrechnen mit if (nur wenn über/gleich 10 Personen)
		freiFahrer = teilnehmer / 10;
		RabattTotal = freiFahrer * preisPKm * km;
		if (freiFahrer >= 1) {
			output += "Freifahrer: " + Integer.toString(freiFahrer) + "\n";
			output += "Hierdurch gespart: " + Float.toString(RabattTotal)
					+ "€\n";
			output += "halber Preis für Fahrtleiter\n";
			output += "Hierdurch gespart: " + Float.toString(special1st)
					+ "€\n";
			output += "\nVergünstigster Preis: " + Float.toString(nachRabatt)
					+ "€\n";
		}
	}

	protected void Output() {
		// TODO Auto-generated method stub
		outputTxt.setText(output);
	}

	protected void Calculate() {
		// TODO Auto-generated method stub
		preisPKm = (float) 0.1;
		special1st = (float) (0.5 * km * preisPKm);
		PreisNormal = teilnehmer * km * preisPKm;
		freiFahrer = teilnehmer / 10;
		RabattTotal = freiFahrer * preisPKm * km;
		vergPreis = PreisNormal - RabattTotal;
		nachRabatt = vergPreis - special1st;

	}

	protected void GetInfo() {
		// TODO Auto-generated method stub
		km = Float.parseFloat(distanzTxt.getText().toString());
		teilnehmer = Integer.parseInt(teilnehmerTxt.getText().toString());

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		distanzTxt = (EditText) findViewById(R.id.buskostenKilometer);
		teilnehmerTxt = (EditText) findViewById(R.id.buskostenTeilnehmer);
		go = (Button) findViewById(R.id.buskostenGo);
		outputTxt = (TextView) findViewById(R.id.buskostenTvOutput);
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
		String aufgabeNum = "Blatt 1 Aufgabe 4";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Ein Busunternehmen berechnet für eine Fahrt pro Person und Kilometer einen Preis von 0,10 €. Jeder 10. Teilnehmer bekommt eine Freifahrt. Dem Fahrtleiter wird der halbe Preis berechnet.\n"
					+ "Entwerfen Sie ein Programm, welches für n Teilnehmer die Gesamtfahrtkosten und die mittleren Einzelfahrtkosten ermittelt"
					+ "\nHinweis: für int n = 5; gilt:\n    n/3 -> 1 (ganzzahliger Anteil der Division\n    n%3 -> 2 (Rest der ganzzahligen Division";
			d.setTitle(aufgabe);
			tvAufgabe.setText(aufgabe + " - "+ aufgabeNum +"\n\n" + aufgabeText);
			d.setContentView(tvAufgabe);
			d.show();
			break;

		case R.id.bug:
			Bundle sendClassName = new Bundle();
			sendClassName.putString("bugClass", realClassName);
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

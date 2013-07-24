package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BusFahrtkosten extends Activity{

	EditText distanzTxt, teilnehmerTxt;
	Button go;
	TextView outputTxt;
	int teilnehmer, freiFahrer;
	float km, preisPKm, PreisNormal,RabattTotal, vergPreis, special1st, DurschnittK, DurschnittKRabatt, nachRabatt;
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
				// TODO Auto-generated method stub
				GetInfo();
				
				Calculate();
				
				output = "Preis: "+ Float.toString(PreisNormal) +"€\n";
				
				CalculateMore10();
				
				Output();
				
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				 imm.hideSoftInputFromWindow(teilnehmerTxt.getWindowToken(), 0);
			}
		});
	}

	protected void CalculateMore10() {
		// TODO Auto-generated method stub
		//Rabatt ausrechnen mit if (nur wenn über/gleich 10 Personen)
        freiFahrer = teilnehmer / 10;
        RabattTotal = freiFahrer * preisPKm * km;
        if (freiFahrer >= 1) {
            	output += "Freifahrer: " +Integer.toString(freiFahrer)+"\n";
                output += "Hierdurch gespart: " + Float.toString(RabattTotal)+ "€\n";    
                output += "halber Preis für Fahrtleiter\n";
                output += "Hierdurch gespart: " + Float.toString(special1st)+ "€\n";
                output += "\nVergünstigster Preis: " + Float.toString(nachRabatt)+ "€\n"; 
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
			switch (item.getItemId()) {
			case (R.id.aufgabe):
				Dialog d = new Dialog(this, 0);
				TextView tvAufgabe = new TextView(this);
				d.setTitle("Bus Fahrtkosten");
				String aufgabeNum = "Blatt 1 Aufgabe 4";
				String aufgabeText = "Ein Busunternehmen berechnet für eine Fahrt pro Person und Kilometer einen Preis von 0,10 €. Jeder 10. Teilnehmer bekommt eine Freifahrt. Dem Fahrtleiter wird der halbe Preis berechnet.\n" +
						"Entwerfen Sie ein Programm, welches für n Teilnehmer die Gesamtfahrtkosten und die mittleren Einzelfahrtkosten ermittelt" +
						"\nHinweis: für int n = 5; gilt:\n    n/3 -> 1 (ganzzahliger Anteil der Division\n    n%3 -> 2 (Rest der ganzzahligen Division";
						tvAufgabe.setText(aufgabeNum + "\n\n" + aufgabeText);
				d.setContentView(tvAufgabe);
				d.show();

				break;
			}
			return false;

		}
}

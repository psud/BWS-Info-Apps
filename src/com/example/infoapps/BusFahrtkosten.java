package com.example.infoapps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

}

package com.example.infoapps;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Wechselgeld extends Activity {

	EditText gegebenTxt, preisTxt;
	TextView outputTxt;
	Button go;

	float preis, eingabe, ausgabeGeld;

	String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wechselgeld);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (gegebenTxt.length() > 0 && preisTxt.length() > 0) {
					go.setText("Rueckgeld");
					go.setTextColor(Color.BLACK);

					output = "";

					GetInputInfo();

					Calculate();

					outputTxt.setText(output);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(gegebenTxt.getWindowToken(), 0);
				} else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
			}
		});
	}

	protected void Calculate() {
		// TODO Auto-generated method stub
		ausgabeGeld = eingabe - preis;
		String la;
		if (eingabe < preis) {
			output += "Noch zu geben: " + Float.toString(preis - eingabe);
		} else if (ausgabeGeld > 9.99) {
			output += "Eingeworfene Betrag ist zu gross \nmaximales Rueckgeld: 9.99";
		} else {
			DecimalFormat df = new DecimalFormat("#.##");
			double ausgabeLong = (double) ausgabeGeld;

			int zweiE = 0, einE = 0, funfzig = 0, zwanzig = 0, zen = 0, funf = 0, zwei = 0, eins = 0;
			if (Float.toString(ausgabeGeld).length() == 3) {
				output += "Wechselgeld: " + Float.toString(ausgabeGeld)
						+ "0€\n";
			}

			 else{

			
			la = df.format(ausgabeLong);
			// output += "Wechselgeld: " +Float.toString(ausgabeGeld) + "€\n";
			output += "Wechselgeld: " + la + "€\n";
			ausgabeGeld = Float.parseFloat(la);
			 }
			while (ausgabeGeld >= 2.00) {
				zweiE++;
				ausgabeGeld = (float) (ausgabeGeld - 2.00);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (zweiE > 0)
				output += Integer.toString(zweiE) + "x  2 Euro\n";
			// //output += Float.toString(ausgabeGeld) + "€\n";
			while (ausgabeGeld >= 1.00) {
				einE++;
				ausgabeGeld = (float) (ausgabeGeld - 1.00);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (einE > 0)
				output += Integer.toString(einE) + "x  1 Euro\n";
			// output += Float.toString(ausgabeGeld) + "€\n";
			while (ausgabeGeld >= 0.50) {
				funfzig++;
				ausgabeGeld = (float) (ausgabeGeld - 0.50);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (funfzig > 0)
				output += Integer.toString(funfzig) + "x  50 Cent\n";
			// output += Float.toString(ausgabeGeld) + "€\n";
			while (ausgabeGeld >= 0.20) {
				zwanzig++;
				ausgabeGeld = (float) (ausgabeGeld - 0.20);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (zwanzig > 0)
				output += Integer.toString(zwanzig) + "x  20 Cent\n";
			// output += Float.toString(ausgabeGeld) + "€\n";
			while (ausgabeGeld >= 0.10) {
				zen++;
				ausgabeGeld = (float) (ausgabeGeld - 0.10);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (zen > 0)
				output += Integer.toString(zen) + "x  10 Cent\n";
			while (ausgabeGeld >= 0.05) {
				funf++;
				ausgabeGeld = (float) (ausgabeGeld - 0.05);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (funf > 0)
				output += Integer.toString(funf) + "x  5 Cent\n";
			while (ausgabeGeld >= 0.02) {
				zwei++;
				ausgabeGeld = (float) (ausgabeGeld - 0.02);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (zwei > 0)
				output += Integer.toString(zwei) + "x  2 Cent\n";
			while (ausgabeGeld >= 0.01) {
				eins++;
				ausgabeGeld = (float) (ausgabeGeld - 0.01);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (eins > 0)
				output += Integer.toString(eins) + "x  1 Cent\n";

		}
	}

	protected void GetInputInfo() {
		// TODO Auto-generated method stub
		preis = Float.parseFloat(preisTxt.getText().toString());
		eingabe = Float.parseFloat(gegebenTxt.getText().toString());
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		gegebenTxt = (EditText) findViewById(R.id.wechselgeldGegeben);
		preisTxt = (EditText) findViewById(R.id.wechselgeldPreis);
		go = (Button) findViewById(R.id.wechselgeldGo);
		outputTxt = (TextView) findViewById(R.id.wechselgeldTvOutput);
	}

}

package com.example.infoapps;

import java.text.DecimalFormat;

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
						+ "0�\n";
			}

			else {

				la = df.format(ausgabeLong);
				// output += "Wechselgeld: " +Float.toString(ausgabeGeld) +
				// "�\n";
				output += "Wechselgeld: " + la + "�\n";
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
			// //output += Float.toString(ausgabeGeld) + "�\n";
			while (ausgabeGeld >= 1.00) {
				einE++;
				ausgabeGeld = (float) (ausgabeGeld - 1.00);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			}
			if (einE > 0)
				output += Integer.toString(einE) + "x  1 Euro\n";
			// output += Float.toString(ausgabeGeld) + "�\n";
			while (ausgabeGeld >= 0.50) {
				funfzig++;
				ausgabeGeld = (float) (ausgabeGeld - 0.50);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			//	output +=  "           Got It 0.50         "   + Float.parseFloat(la)+"\n";

			}
			if (funfzig > 0)
				output += Integer.toString(funfzig) + "x  50 Cent\n";
			// output += Float.toString(ausgabeGeld) + "�\n";
			while (ausgabeGeld >= 0.20) {
				zwanzig++;
				ausgabeGeld = (float) (ausgabeGeld - 0.20);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			//	output +=  "           Got It 0.20        "   + Float.parseFloat(la)+"\n";

			}
			if (zwanzig > 0)
				output += Integer.toString(zwanzig) + "x  20 Cent\n";
			// output += Float.toString(ausgabeGeld) + "�\n";
			while (ausgabeGeld >= 0.10) {
				zen++;
				ausgabeGeld = (float) (ausgabeGeld - 0.10);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			//	output +=  "           Got It 0.10         "   + Float.parseFloat(la)+"\n";
			}
			if (zen > 0)
				output += Integer.toString(zen) + "x  10 Cent\n";
			while (ausgabeGeld >= 0.05) {
				funf++;
				ausgabeGeld = (float) (ausgabeGeld - 0.05);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			//	output +=  "           Got It 0.05      "   + Float.parseFloat(la)+"\n";
			}
			if (funf > 0)
				output += Integer.toString(funf) + "x  5 Cent\n";
			while (ausgabeGeld > 0.0199) {
				zwei++;
				ausgabeGeld = (float) (ausgabeGeld - 0.02);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
			//	output +=  "           Got It 0.02       "   + Float.parseFloat(la)+"\n";
			}
			if (zwei > 0)
			output += Integer.toString(zwei) + "x  2 Cent\n";
			while (ausgabeGeld > 0.0099) {
				eins++;
				ausgabeGeld = (float) (ausgabeGeld - 0.01);
				ausgabeLong = (double) ausgabeGeld;
				la = df.format(ausgabeLong);
				ausgabeGeld = Float.parseFloat(la);
				//output +=  "           Got It 0.01         "   + Float.parseFloat(la)+"\n";
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
		String aufgabeNum = "Blatt 2 Aufgabe 1";
	    String realClassName = this.getClass().getName().substring(21);
	   String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Ein Fahrscheinautomat gibt Wechselgeld bis zu 9,99 � (in M�nzen) zur�ck und kommt dabei mit m�glichst "
					+ "wenig M�nzen aus. (Beispiel: bei einem Fahrpreis von 12,30 � und Eingabe eines 20 �-Scheins werden eine drei "
					+ "2-Eurost�cke, ein Eurost�ck, ein 50 Centst�ck, und ein 20 Centst�ck zur�ckgegeben). Schreiben Sie ein "
					+ "Programm, dass dieses Verhalten nachahmt.";
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

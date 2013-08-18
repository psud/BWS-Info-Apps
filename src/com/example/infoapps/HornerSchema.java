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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class HornerSchema extends Activity {

	NumberPicker koeffizientTxt;
	Button go, neu;
	GridView tabelleTxt;
	TextView fktOutTxt;
	String fktOut;
	EditText[] eingabeA = new EditText[10];
	TextView[] eingabeTv = new TextView[10];
	String[] tabelle = new String[42];
	float[] eingaben = new float[10];
	int koeffizient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hornerschema);

		Initialize();

		koeffizientTxt.setMaxValue(9);
		koeffizientTxt.setMinValue(0);
		koeffizientTxt.setValue(4);

		fktOutTxt.setVisibility(View.INVISIBLE);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, tabelle);

		// InputMethodManager imm =
		// (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(koeffizientTxt.getWindowToken(), 0);

		neu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				koeffizient = koeffizientTxt.getValue();

				for (int p = 0; p < koeffizient + 1; p++) {
					eingabeA[p].setVisibility(View.VISIBLE);
					eingabeTv[p].setVisibility(View.VISIBLE);
				}
				for (int i = koeffizient + 1; i < 10; i++) {
					eingabeA[i].setVisibility(View.GONE);
					eingabeTv[i].setVisibility(View.GONE);
				}

			}
		});

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean allK = true;
				for (int d = 0; d <= koeffizient; d++) {
					if (eingabeA[d].length() == 0) {
						allK = false;
						eingabeA[d].setText("0");
						eingabeA[d].setHint("Nummer eingeben");
						eingabeTv[d].setTextColor(Color.RED);
					}
				}
				if (allK == true) {
					for (int f = 0; f < 10; f++) {
						eingabeA[f].setHint("");
						eingabeTv[f].setTextColor(Color.BLACK);
					}

					for (int k = 0; k <= koeffizient; k++) {
						eingaben[k] = Float.parseFloat(eingabeA[k].getText()
								.toString());
					}

					float xWert = 5;
					float schrittW = (float) 0.5;
					int tabelleSpot = 0;

					boolean notDone = true;

					while (notDone == true) {
						float ergebnis = 0;
						for (int h = koeffizient; h > 0; h--) {
							ergebnis = (ergebnis + eingaben[h]) * xWert;
							eingabeA[h].setText(Float.toString(eingaben[h]));
						}
						ergebnis = ergebnis + eingaben[0];

						tabelle[tabelleSpot] = Float.toString(xWert);
						tabelleSpot++;
						tabelle[tabelleSpot] = Float.toString(ergebnis);
						tabelleSpot++;

						xWert = xWert - schrittW;

						if (xWert < -5)
							notDone = false;
					}

					tabelleTxt.setAdapter(adapter);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(eingabeA[0].getWindowToken(), 0);

					FunktionsGleichung();

				} else
					go.setText("Nummern eingeben");
			}
		});

	}

	protected void FunktionsGleichung() {
		// TODO Auto-generated method stub
		fktOut = "f(x) = ";
		for (int g = koeffizient; g >= 0; g--) {

			if (g == 1 && eingaben[g]!= 1 && eingaben[g]!= 0)
				fktOut += (" +" + Float.toString(eingaben[g]) + "x");
			
			else if (g == koeffizient) {
				 if (eingaben[g] == 1)
					fktOut += (" x^" + Integer.toString(g));
				else if (eingaben[g] == 0)
					fktOut += ("");
				else if (eingaben[g] == -1)
					fktOut += (" -x^" + Integer.toString(g));
				else if (eingaben[g] < -1)
					fktOut += (" " + Float.toString(eingaben[g]) + "x^" + Integer
							.toString(g));
				else if (eingaben[g] > 1)
					fktOut += (" " + Float.toString(eingaben[g]) + "x^" + Integer
							.toString(g));
			} else if (g == 0) {
				if (eingaben[g] == 1)
					fktOut += (" 1");
				else if (eingaben[g] == 0)
					fktOut += ("");
				else if (eingaben[g] == -1)
					fktOut += (" -1");
				else if (eingaben[g] < -1)
					fktOut += (" " + Float.toString(eingaben[g]));
				else if (eingaben[g] > 1)
					fktOut += (" +" + Float.toString(eingaben[g]));

			} else {
				if (eingaben[g] == 1)
					fktOut += (" +x^" + Integer.toString(g));
				else if (eingaben[g] == 0)
					fktOut += ("");
				else if (eingaben[g] == -1)
					fktOut += (" -x^" + Integer.toString(g));
				else if (eingaben[g] < -1)
					fktOut += (" " + Float.toString(eingaben[g]) + "x^" + Integer
							.toString(g));
				else if (eingaben[g] > 1)
					fktOut += (" +" + Float.toString(eingaben[g]) + "x^" + Integer
							.toString(g));
			}
		}
		fktOutTxt.setVisibility(View.VISIBLE);
		fktOutTxt.setText(fktOut);
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		koeffizientTxt = (NumberPicker) findViewById(R.id.hornerNumPicker);
		go = (Button) findViewById(R.id.hornerGo);
		neu = (Button) findViewById(R.id.hornerNeu);

		tabelleTxt = (GridView) findViewById(R.id.hornerTabelleOut);

		fktOutTxt = (TextView) findViewById(R.id.hornerFktGleichungOut);

		eingabeA[0] = (EditText) findViewById(R.id.hornerNum0);
		eingabeA[1] = (EditText) findViewById(R.id.hornerNum1);
		eingabeA[2] = (EditText) findViewById(R.id.hornerNum2);
		eingabeA[3] = (EditText) findViewById(R.id.hornerNum3);
		eingabeA[4] = (EditText) findViewById(R.id.hornerNum4);
		eingabeA[5] = (EditText) findViewById(R.id.hornerNum5);
		eingabeA[6] = (EditText) findViewById(R.id.hornerNum6);
		eingabeA[7] = (EditText) findViewById(R.id.hornerNum7);
		eingabeA[8] = (EditText) findViewById(R.id.hornerNum8);
		eingabeA[9] = (EditText) findViewById(R.id.hornerNum9);

		eingabeTv[0] = (TextView) findViewById(R.id.hornerTvA0);
		eingabeTv[1] = (TextView) findViewById(R.id.hornerTvA1);
		eingabeTv[2] = (TextView) findViewById(R.id.hornerTvA2);
		eingabeTv[3] = (TextView) findViewById(R.id.hornerTvA3);
		eingabeTv[4] = (TextView) findViewById(R.id.hornerTvA4);
		eingabeTv[5] = (TextView) findViewById(R.id.hornerTvA5);
		eingabeTv[6] = (TextView) findViewById(R.id.hornerTvA6);
		eingabeTv[7] = (TextView) findViewById(R.id.hornerTvA7);
		eingabeTv[8] = (TextView) findViewById(R.id.hornerTvA8);
		eingabeTv[9] = (TextView) findViewById(R.id.hornerTvA9);

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
		String aufgabeNum = "Blatt 7 Aufgabe 3";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Die Funktionswerte von ganzrationalen Funktionen (Polynome) lassen sich vorteilhaft durch das Horner Schema "
					+ "berechnen. Formulieren sie eine Funktion Horner und ein geeignetes Testprogramm. \n\n"
					+ "Beispiel: Berechne den Funktionswert an der Stelle x=2 der Funktion\n"
					+ "f(x) 3x^4 2x^2 1,5x 5";
			d.setTitle(aufgabe);
			tvAufgabe.setText(aufgabe + " - " + aufgabeNum + "\n\n"
					+ aufgabeText);
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
			myWebLink
					.setData(Uri
							.parse("https://github.com/psud/BWS-Info-Apps/blob/master/src/com/example/infoapps/"
									+ realClassName + ".java"));
			startActivity(myWebLink);
			break;
		}
		return false;

	}

}

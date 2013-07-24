package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ZahlenBildungsgesetz extends Activity {

	Button go;
	TextView outWordTxt, outLangTxt;
	EditText inputTxt;
	String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zahllenbildungsgesetz);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ausgabeLaenge = "", ausgabeWort = "";
				boolean cont = true;
				if (inputTxt.length() > 0) {
					input = inputTxt.getText().toString();
					if (input.length() == 1
							&& input.substring(0, 1).equalsIgnoreCase("4")) {
						ausgabeWort = "vier";
						ausgabeLaenge = "4";
					} else {
						String number = input;
						while (cont) {

							number = Compute(number);
							ausgabeWort += number + "\n";
							ausgabeLaenge += Integer.toString(number.length())
									+ "\n";

							if (number.length() == 4) {
								cont = false;
								ausgabeWort += "vier";
								ausgabeLaenge += Integer.toString(number
										.length());
							}
							number = Integer.toString(number.length());
						}
					}
					outWordTxt.setText(ausgabeWort);
					outLangTxt.setText(ausgabeLaenge);
				}
			}
		});
	}

	protected String Compute(String anfangsString) {
		// TODO Auto-generated method stub
		String ausgabeString = "";

		int laenge = anfangsString.length();

		int extra = Math.abs(laenge % 3 - 3);

		if (extra == 1)
			anfangsString = "0" + anfangsString;
		else if (extra == 2)
			anfangsString = "00" + anfangsString;
		// inputTxt.setText(anfangsString);

		int durchlauf = anfangsString.length() / 3;

		String[] BigNumberSingle = { "", "tausend", "million", "milliarde",
				"billion", "billiarde", "trillion", "trilliarde",
				"quadrillion", "quadrilliarde", "quintillion", "quintilliarde",
				"sextillion", "sextilliarde", "septillion", "septilliarde",
				"oktillion", "oktilliarde", "nonillion", "nonilliarde",
				"dezillion", "dezilliarde", "undezillion", "undezilliarde",
				"dodezillion", "dodezilliarde", "tredezillion",
				"tredezilliarde", "quattuordezillion" };
		String[] BigNumberMulti = { "", "tausend", "millionen", "milliarden",
				"billionen", "billiarden", "trillionen", "trilliarden",
				"quadrillionen", "quadrilliarden", "quintillionen",
				"quintilliarden", "sextillionen", "sextilliarden",
				"septillionen", "septilliarden", "oktillionen", "oktilliarden",
				"nonillionen", "nonilliarden", "dezillionen", "dezilliarden",
				"undezillionen", "undezilliarden", "dodezillionen",
				"dodezilliarden ", "tredezillionen", "tredezilliarden",
				"quattuordezillionen" };
		String spaceString;

		if (laenge > 87)
			ausgabeString = "Lang";
		else {
			String ubergabeString;
			int namenStelle = durchlauf;
			for (int p = 0; p < durchlauf; p++) {
				namenStelle--;

				int anfang = p * 3;
				ubergabeString = anfangsString.substring(anfang, anfang + 3);
				// if (namenStelle == 1)
				// inputTxt.setText(ubergabeString);
				int ubergabeInt = Integer.parseInt(ubergabeString);
				ausgabeString += BisTausend(ubergabeString, namenStelle,
						ausgabeString);

				if (ubergabeInt == 1) {
					ausgabeString += BigNumberSingle[namenStelle];
				} else {
					ausgabeString += BigNumberMulti[namenStelle];
				}
			}
		}

		return ausgabeString;
	}

	private String BisTausend(String zahlen, int durchgang, String totalStr) {
		// TODO Auto-generated method stub
		String wort = "";
		// int durchgang = 0;
		String zehnerString = zahlen.substring(1, 3);
		String hunderterString = zahlen.substring(0, 1);

		int hunderterInt = Integer.parseInt(hunderterString);
		int zehnerInt = Integer.parseInt(zehnerString);
		int zehnerProzentInt = zehnerInt % 10;
		int zehnerIntStelle = zehnerInt / 10;
		int einerStelleInt = zehnerInt % 10;

		String[] einerArray = { "null", "ein", "zwei", "drei", "vier", "fünf",
				"sechs", "sieben", "acht", "neun", "zehn", "elf", "zwölf",
				"dreizehn", "vierzehn", "fünfzehn", "sechszehn", "siebzehn",
				"achtzehn", "neunzehn" };
		String[] zehnerArray = { "zwanzig", "dreißig", "vierzig", "fünfzig",
				"sechzig", "siebzig", "achtzig", "neunzig" };
		String[] hunderterArray = { "", "ein", "zwei", "drei", "vier", "fünf",
				"sechs", "sieben", "acht", "neun" };

		if (hunderterInt > 0) {
			wort += hunderterArray[hunderterInt];
			wort += "hundert";
		}

		if (zehnerInt == 0 && hunderterInt == 0 && durchgang == 0)
			wort += "null"; // oder einerArray[0];

		if (zehnerInt == 1 && durchgang == 0)
			wort += "eins";

		else if (hunderterInt == 0 && zehnerInt == 1 && durchgang > 1)
			wort += "eine";

		else if (zehnerInt >= 1 && zehnerInt < 20) {
			wort += einerArray[zehnerInt];
		} else if (zehnerInt > 19 && hunderterInt >= 0 && zehnerProzentInt == 0) {
			wort += zehnerArray[zehnerIntStelle - 2];
		} else if (zehnerInt > 19 && hunderterInt >= 0) {
			wort += hunderterArray[einerStelleInt];
			wort += "und";
			wort += zehnerArray[zehnerIntStelle - 2];
		}
		return wort;
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		go = (Button) findViewById(R.id.zahlbildGo);
		outWordTxt = (TextView) findViewById(R.id.zahlbildTextWort);
		outLangTxt = (TextView) findViewById(R.id.zahlbildTextLength);
		inputTxt = (EditText) findViewById(R.id.zahlbildInput);
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
		String aufgabeNum = "Blatt 8 Aufgabe 2b";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Die Folge „einundzwanzig, dreizehn, acht, vier“ liegt ein einfaches Bildungsgesetz zugrunde. Versuchen "
					+ "Sie es herauszubekommen und schreiben Sie ein Programm, dass weitere Folgen dieser Art erzeugt";
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

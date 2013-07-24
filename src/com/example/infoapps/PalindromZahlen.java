package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PalindromZahlen extends Activity {

	String input, output, outputSatz;
	EditText inputTxt;
	Button go;
	TextView outputTxt, outputSatzTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.palindromzahlen);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				output = "";
				Compute();

			}
		});

	}

	protected void Compute() {
		// TODO Auto-generated method stub
		int wiederholungen = 0;
		String umdreh, total = "", totalUmdrehStr;
		outputSatz = "";
		input = inputTxt.getText().toString();
		String start = input;
		boolean cont = true;

		umdreh = new StringBuffer(input).reverse().toString();

		if (input.equalsIgnoreCase(umdreh)) {
			output += input;
			outputSatz += "Nach 0 Inversionen wird die Zahl " + input
					+ " zu einer " + Integer.toString(input.length())
					+ " stelligen Palindromzahl";
			cont = false;
		}

		while (cont == true) {
			wiederholungen++;
			umdreh = new StringBuffer(input).reverse().toString();
			String strich = "";
			for (int i = 0; i < input.length(); i++)
				strich += "--";
			if (strich.length() > 45)
				strich = strich.substring(0, 44);
			output += input + "\n+ " + umdreh + "\n" + strich + "\n";

			total = Addition(input, umdreh);

			totalUmdrehStr = new StringBuffer(total).reverse().toString();

			if (total.equalsIgnoreCase(totalUmdrehStr)) {
				cont = false;
				output += total + "\n";
				outputSatz += "\nNach " + Integer.toString(wiederholungen)
						+ " Inversionen wird die Zahl " + start + " ein "
						+ Integer.toString(total.length())
						+ "-stelliges Palindrom.";

			}
			if (wiederholungen > 299) {
				cont = false;
				output += total + "\n";
				outputSatz += "kein Palindromergebnis nach 300 Inversionen bei einer "
						+ Integer.toString(total.length())
						+ "-stelliges Palindrom";
			}

			input = total;

		}

		outputTxt.setText(output);
		outputSatzTxt.setText(outputSatz);
	}

	private String Addition(String num1, String num2) {
		// TODO Auto-generated method stub
		String ergebnis = "";

		int laenge = num1.length();
		int originalInt, umkehrInt, summe, sinnInt = 0, lastZahl;

		String originalZahl, umkehrStringZahl;

		// originalInt = Integer.parseInt(num1.substring(1, 1));
		laenge--;
		for (int i = laenge; i >= 0; i--) {
			// ergebnis = Integer.toString(laenge);
			originalZahl = num1.substring(i, i + 1);
			umkehrStringZahl = num2.substring(i, i + 1);
			originalInt = Integer.parseInt(originalZahl);
			umkehrInt = Integer.parseInt(umkehrStringZahl);

			summe = originalInt + umkehrInt + sinnInt;

			sinnInt = summe / 10;
			lastZahl = summe % 10;

			ergebnis = Integer.toString(lastZahl) + ergebnis;

		}
		if (sinnInt == 1)
			ergebnis = "1" + ergebnis;

		return ergebnis;
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		inputTxt = (EditText) findViewById(R.id.palzahlInput);
		go = (Button) findViewById(R.id.palzahlGo);
		outputTxt = (TextView) findViewById(R.id.palzahlOut);
		outputSatzTxt = (TextView) findViewById(R.id.palzahlOutSatz);
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
		String aufgabeNum = "Blatt 8 Aufgabe 1d";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Durch wiederholte Addition von Zahl und Kehrzahl lassen sich Palindrom erzeugen (z. B. 165 + 561->726 "
					+ "+ 627->1353 + 3531 = 4884). Schreiben Sie ein entsprechendes Programm (Internethinweis: 196-Problem)."+
					"\n\nNote: Die App macht immer maximal 300 Inversionen";
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

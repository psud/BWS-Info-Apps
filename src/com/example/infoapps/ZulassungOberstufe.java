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

public class ZulassungOberstufe extends Activity {

	EditText englischTxt, deutschTxt, matheTxt, schwerTxt;
	Button go;
	TextView outputTxt;
	int deutsch, englisch, mathe, schwer;
	String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zulassungoberstufe);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (englischTxt.length() > 0 && deutschTxt.length() > 0
						&& matheTxt.length() > 0 && schwerTxt.length() > 0) {
					
					go.setText("Go");
					go.setTextColor(Color.BLACK);

					GetValues();

					CheckIfPossible();

					outputTxt.setText(output);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(schwerTxt.getWindowToken(), 0);
				}
				else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
			}
		});
	}

	protected void CheckIfPossible() {
		// TODO Auto-generated method stub
		int fail = 0, plus7 = 0, plus10 = 0;
		boolean zugelassen = false;

		if (deutsch < 5)
			fail++;
		else if (deutsch > 9)
			plus10++;
		else if (deutsch > 6)
			plus7++;
		if (englisch < 5)
			fail++;
		else if (englisch > 9)
			plus10++;
		else if (englisch > 6)
			plus7++;
		if (mathe < 5)
			fail++;
		else if (mathe > 9)
			plus10++;
		else if (mathe > 6)
			plus7++;
		if (schwer < 5)
			fail++;
		else if (schwer > 9)
			plus10++;
		else if (schwer > 6)
			plus7++;
		if (mathe == 0 || deutsch == 0 || englisch == 0 || schwer == 0)
			fail = 2;

		if (fail > 1)
			zugelassen = false;
		else if ((fail == 1 && plus7 > 1) || (fail == 1 && plus10 > 0))
			zugelassen = true;
		else if (fail == 0)
			zugelassen = true;

		if (zugelassen == true)
			output = "Sie sind zugelassen";
		else
			output = "Sie sind NICHT zugelassen";

	}

	protected void GetValues() {
		// TODO Auto-generated method stub
		deutsch = Integer.parseInt(deutschTxt.getText().toString());
		englisch = Integer.parseInt(englischTxt.getText().toString());
		mathe = Integer.parseInt(matheTxt.getText().toString());
		schwer = Integer.parseInt(schwerTxt.getText().toString());

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		englischTxt = (EditText) findViewById(R.id.zulassungEnglisch);
		deutschTxt = (EditText) findViewById(R.id.zulassungDeutsch);
		matheTxt = (EditText) findViewById(R.id.zulassungMathe);
		schwerTxt = (EditText) findViewById(R.id.zulassungSchwer);
		go = (Button) findViewById(R.id.zulassungGo);
		outputTxt = (TextView) findViewById(R.id.zulassungOutput);
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
		String aufgabeNum = "Blatt 3 Aufgabe 5";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "F�r die Zulassung zur Jahrgangsstufe 12 des Beruflichen Gymnasiums darf u.a. h�chstens eines der F�cher "
					+ "Deutsch, Englisch, Mathematik oder das Schwerpunktfach mit weniger als 5 Punkten abgeschlossen sein. In "
					+ "diesem Fall m�ssen unter den genannten F�chern zwei mit mindestens 7 Punkten oder eines mit mindestens 10 "
					+ "Punkten als Ausgleich sein.";
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

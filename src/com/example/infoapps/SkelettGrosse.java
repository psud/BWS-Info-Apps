package com.example.infoapps;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SkelettGrosse extends Activity {

	EditText alterTxt, schenkelTxt;
	Button go;
	RadioGroup geschlecht;
	RadioButton mann, frau;
	TextView outputTxt;
	float grosse, schenkel;
	int alter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skelettgrosse);

		Initialize();

		KillOtherRadio();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (alterTxt.length() > 0 && schenkelTxt.length() > 0) {

					schenkel = Float.parseFloat(schenkelTxt.getText()
							.toString());
					alter = Integer.parseInt(alterTxt.getText().toString());

					if (mann.isChecked()) {
						grosse = (float) (69.089 + 2.238 * schenkel);

					}
					if (frau.isChecked()) {
						grosse = (float) (61.412 + 2.317 * schenkel);

					}
					if (Integer.parseInt(alterTxt.getText().toString()) > 30) {
						grosse = (float) (grosse - ((alter - 30) * 0.06));
					}
					DecimalFormat df = new DecimalFormat("###");
					double ausgabeLong = (double) grosse;
					String la = df.format(ausgabeLong);
					outputTxt.setText("Grosse: " + df.format(ausgabeLong)
							+ "cm");

					// outputTxt.setText(Float.toString(grosse) + "cm");
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(schenkelTxt.getWindowToken(), 0);
				}
			}
		});
	}

	private void KillOtherRadio() {
		// TODO Auto-generated method stub
		mann.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frau.setChecked(false);
			}
		});
		frau.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mann.setChecked(false);
			}
		});
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		alterTxt = (EditText) findViewById(R.id.skelettAlter);
		schenkelTxt = (EditText) findViewById(R.id.skelettOberschenkel);
		go = (Button) findViewById(R.id.skelettGo);
		geschlecht = (RadioGroup) findViewById(R.id.skelettGeschlecht);
		mann = (RadioButton) findViewById(R.id.skelettMann);
		frau = (RadioButton) findViewById(R.id.skelettFrau);
		outputTxt = (TextView) findViewById(R.id.skelettOutput);
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
		String aufgabeNum = "Blatt 3 Aufgabe 1";
	    String realClassName = this.getClass().getName().substring(21);
	   String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Bei Skelettfunden schließt man aus der Länge von Knochen auf die Körpergröße; und zwar gilt (als statistischer "
					+ "Mittelwert) in cm:\n"
					+ "   Körpergröße = 69.089 + 2.238 * Oberschenkellänge bei Männern\n"
					+ "   und\n"
					+ "   Körpergröße = 61.412 + 2.317 * Oberschenkellänge bei Frauen\n."
					+ "Ab dem 30. Lebensjahr nimmt die Körpergröße um 0.06 cm pro Jahr ab.\n\n"
					+ "Schreiben Sie ein Programm, das aus Oberschenkellänge, Alter und Geschlecht die Körpergröße berechnet.";
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

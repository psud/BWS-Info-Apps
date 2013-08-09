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
import android.widget.Toast;

public class WaagerechterWurf extends Activity {

	EditText hoheTxt, zeitTxt, speedTxt;
	TextView output, outputXY;
	Button go;
	float hohe, zeit, speed;
	float x, y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waagerechterwurf);

		Initialize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (hoheTxt.length()>0 && zeitTxt.length()>0 && speedTxt.length()>0){
					go.setText("Go");
					go.setTextColor(Color.BLACK);
				// TODO Auto-generated method stub
				GetValues();

				Calculate();

				Output();
				}
				else{
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}
 
			}
		});

	}

	protected void Output() {
		// TODO Auto-generated method stub
		output.setText(Float.toString(x) + "    " + Float.toString(y));
		outputXY.setText("X      Y");
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(speedTxt.getWindowToken(), 0);
	}

	private void GetValues() {
		// TODO Auto-generated method stub
		hohe = Float.parseFloat(hoheTxt.getText().toString());
		zeit = Float.parseFloat(zeitTxt.getText().toString());
		speed = Float.parseFloat(speedTxt.getText().toString());
	}

	private void Calculate() {
		// TODO Auto-generated method stub
		y = (float) (hohe - 0.5 * 9.81 * zeit * zeit);
		x = speed * zeit;
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		hoheTxt = (EditText) findViewById(R.id.waagwurfHohe);
		zeitTxt = (EditText) findViewById(R.id.waagwurfZeit);
		speedTxt = (EditText) findViewById(R.id.waagwurfSpeed);
		output = (TextView) findViewById(R.id.waagwurfTvoutput);
		go = (Button) findViewById(R.id.waagwurfGo);
		outputXY = (TextView) findViewById(R.id.waagwurfTvoutputXY);

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
		String aufgabeNum = "Blatt 1 Aufgabe 2";
		String aufgabe = this.getTitle().toString();
		String realClassName = this.getClass().getName().substring(21);
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Ein Körper wird horizontal aus der Höhe h mit der Anfangsgeschwindigkeit v0 abgeworfen. Er führt - bei Vernachlässigung des Luftwiderstandes - eine gleichförmige Bewegung in horizontaler und eine gleichmäßig beschleunigte Bewegung in vertikaler Richtung aus.\nx(t)=v0*t y(t) = h-½*g*t2   g = 9,81 m/s2\nEin von Ihnen zu schreibendes Programm soll bei gegebener Höhe h und Anfangsgeschwindigkeit v0 für eine eingegebene Zeit t die Koordinaten (x,y) des Körpers berechnen.";
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
			myWebLink.setData(Uri.parse("https://github.com/psud/BWS-Info-Apps/blob/master/src/com/example/infoapps/"
									+ realClassName + ".java"));
			startActivity(myWebLink);
			break;
		}
		return false;

	}
}

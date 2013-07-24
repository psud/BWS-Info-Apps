package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
				// TODO Auto-generated method stub
				GetValues();

				Calculate();

				Output();

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
		String aufgabe = "Waagerechter Wurf";
	    String aufgabeNum = "Blatt 1 Aufgabe 2";
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);	
			String aufgabeText = "Ein K�rper wird horizontal aus der H�he h mit der Anfangsgeschwindigkeit v0 abgeworfen. Er f�hrt - bei Vernachl�ssigung des Luftwiderstandes - eine gleichf�rmige Bewegung in horizontaler und eine gleichm��ig beschleunigte Bewegung in vertikaler Richtung aus.\nx(t)=v0*t y(t) = h-�*g*t2   g = 9,81 m/s2\nEin von Ihnen zu schreibendes Programm soll bei gegebener H�he h und Anfangsgeschwindigkeit v0 f�r eine eingegebene Zeit t die Koordinaten (x,y) des K�rpers berechnen.";
			d.setTitle(aufgabe);
			tvAufgabe.setText(aufgabeNum + "\n\n" + aufgabeText);
			d.setContentView(tvAufgabe);
			d.show();
			break;

		case R.id.bug:		
			Bundle sendClassName = new Bundle();
			sendClassName.putString("bugClass",aufgabe + "  ---  "+ aufgabeNum );
			Intent bugSend = new Intent(WaagerechterWurf.this, BugSubmit.class);   //"com.example.infoapps.BUG"
			bugSend.putExtras(sendClassName);
			startActivity(bugSend);
			break;
		}
		return false;

	}
}

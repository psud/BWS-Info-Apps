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

public class Funktionsgleichung extends Activity {

	EditText x1T, y1T, x2T, y2T;
	TextView outputT;
	float x1, y1, x2, y2, m, b;
	Button go;
	String output;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.funktionsgleichung);
		
		Initialize();
		
		go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (x1T.length()>0 &&y1T.length()>0 &&x2T.length()>0 &&y2T.length()>0 ){
					
					Convert();
					
					Calculate();
					
					Output();
				}
				else 
					go.setText("Alle Felder Ausfuellen");
			}
		});
	}

	protected void Output() {
		// TODO Auto-generated method stub
		if (m == 0)
	        output = "1f(x) = " + Float.toString(m);
	    else if (x1 == x2)
	    	output = "Die Gerade ist keine Funktion sondern eine Relation.\ng: x = "+ Float.toString(x1);
	    else if (b == 0 && m == 1)
	        output = "2f(x) = x";
	    else if (b == 0 || b == -0)
	         output = "3f(x) = " + Float.toString(m)+"x";
	    else if (m == 1 && b > 0)
	        output = "4f(x) = x + " +Float.toString(b);
	    else if (m == 1 && b < 0)
	        output = "5f(x) = "+Float.toString(b);
	    else if (b < 0)
	        output = "6f(x) = "+Float.toString(m)+"x "+ Float.toString(b);
	    else if (b > 0)
	        output = "7f(x) = "+Float.toString(m)+"x + "+Float.toString(b);
		
		outputT.setText(output);
	}

	protected void Calculate() {
		// TODO Auto-generated method stub
		 m = (y1 - y2)/(x1 - x2);
		 x1T.setText(Float.toString(m));
		 b = 3- (m*2);
		 x2T.setText(Float.toString(b));
	}

	protected void Convert() {
		// TODO Auto-generated method stub
		x1 = Float.parseFloat(x1T.getText().toString());
		y1 = Float.parseFloat(y1T.getText().toString());
		x2 = Float.parseFloat(x2T.getText().toString());
		y2 = Float.parseFloat(y2T.getText().toString());
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		x1T = (EditText) findViewById(R.id.funktiongX1);
		y1T = (EditText) findViewById(R.id.funktiongY1);
		x2T = (EditText) findViewById(R.id.funktiongX2);
		y2T = (EditText) findViewById(R.id.funktiongY2);
		go = (Button) findViewById(R.id.funktiongGo);
		outputT = (TextView) findViewById(R.id.funktiongOutput);
		
		
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

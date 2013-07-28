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

public class DreieckTester2 extends Activity {

	EditText aTxt, bTxt, cTxt;
	Button go;
	TextView outputTxt;
	float a, b, c;
	String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dreiecktester);

		Initizlize();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (aTxt.length() > 0 && bTxt.length() > 0 && cTxt.length() > 0) {
					GetValues();

					boolean possible = TestIfPossible();

					if (possible == true) {
						output = "Konstruirbar";
						TestWhatIs();
						
					}
					else
						output = "Nicht Konstruirbar";
					outputTxt.setText(output);
				}

			}

		});
	}

	protected void TestWhatIs() {
		// TODO Auto-generated method stub
		// Gleichseitig
		if (a == b || a == c || b == c)
			output += " und Gleichschenklig";
		if ((a == b && b == c) || (a == b && c == a) || (b == c && c == a))
			output += " und Gleichseitig";
		
		Sortierer();
		

	}

	private void Sortierer() {
		// TODO Auto-generated method stub
		float tNum;
		float gr;
		if(a>b&&a>c){
		       gr=a;
		      tNum = b *b +c *c;
		      if (tNum == gr*gr)
		    	  output += " und Rechtwinkelig";
		   }
		   else if(b>a&&b>c){
		       gr=b;
		       tNum = a *a +c *c;
			      if (tNum == gr*gr)
			    	  output += " und Rechtwinkelig";
		   }
		   else if(c>b&&c>a){
		       gr=c;
		       tNum = a *a +b *b;
			      if (tNum == gr*gr)
			    	  output += " und Rechtwinkelig";
		   }
	}

	protected boolean TestIfPossible() {
		// TODO Auto-generated method stub
		boolean rueck = false;
		if (a == 0 || b == 0 || c == 0)
			rueck = false;

		else {
			if (a == b && a + b > c || b == c && b + c > a || a == c
					&& a + c > b)
				rueck = true;

			else {
				if (a > b && a > c) {
					if (a < b + c)
						rueck = true;
					else
						rueck = false;
				} else {
					if (b > a && b > c) {
						if (b < a + c)
							rueck = true;
						else
							rueck = false;
					} else {
						if (c > a && c > b) {
							if (c < a + b)
								rueck = true;
							else
								rueck = false;
						}
					}
				}
			}
		}
		return rueck;
	}

	protected void GetValues() {
		// TODO Auto-generated method stub
		a = Float.parseFloat(aTxt.getText().toString());
		b = Float.parseFloat(bTxt.getText().toString());
		c = Float.parseFloat(cTxt.getText().toString());
	}

	private void Initizlize() {
		// TODO Auto-generated method stub
		aTxt = (EditText) findViewById(R.id.dreieckA);
		bTxt = (EditText) findViewById(R.id.dreieckB);
		cTxt = (EditText) findViewById(R.id.dreieckC);
		go = (Button) findViewById(R.id.dreieckGo);
		outputTxt = (TextView) findViewById(R.id.dreieckOutput);
	}

	
	
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
		String aufgabeNum = "Blatt 6 Aufgabe 2&3";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Bei einem Dreieck ist die Summe der Längen zweier Seiten größer als die der dritten Seite. "
					+ "Ein Programm ist zu schreiben, das prüft, ob drei gegebene Zahlen die Bedingung erfüllen, also die Seiten eines "
					+ "Dreiecks bilden können.\n\n "
					+ "Nach der Eingabe von drei Längen soll das zu schreibende Programm entscheiden, ob aus den Längen ein "
					+ "Dreieck konstruiert werden kann. Wenn ja soll noch untersucht werden, ob das Dreieck gleichschenklig, "
					+ "gleichseitig oder rechtwinklig ist.";
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

	}//
}

package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Palindrome extends Activity {

	ImageButton go1, go2;
	EditText input1, input2;
	String input1Org;
	String input1Back;

	// doIt

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.palindrome);

		Initialize();

		go1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input1Org = input1.getText().toString();
				input1Org = input1Org.toLowerCase();
				input1Back = new StringBuffer(input1Org).reverse().toString();
				if (input1Org.equalsIgnoreCase(input1Back)) {
					go1.setImageResource(R.drawable.palin_check);
				} else
					go1.setImageResource(R.drawable.lottox);
			}

		});
		go2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input1Org = input1.getText().toString();
				input1Org = input1Org.toLowerCase();
				input1Org = input1Org.replaceAll("[^A-Za-z0-9]", "");
				input1Back = new StringBuffer(input1Org).reverse().toString();
				if (input1Org.equalsIgnoreCase(input1Back)) {
					go2.setImageResource(R.drawable.palin_check);
				} else
					go2.setImageResource(R.drawable.lottox);
			}
		});

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		input1 = (EditText) findViewById(R.id.palinInput);
		go1 = (ImageButton) findViewById(R.id.palinGo1);
		go2 = (ImageButton) findViewById(R.id.palinGo2);
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
		String aufgabeNum = "Blatt 8 Aufgabe 1a,c";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "a) Ein Palindrom ist eine Zeichenkette die vorwärts und rückwärts gelesen gleich ist (z.B. 'OTTO'). Entwerfen (Struktogramm) und codieren Sie ein Programm, dass ein Wort auf seine Palindromeigenschaft prüft. Die Prüfung soll unabhängig von Groß- oder Kleinbuchstaben sein, also z.B. 'Anna' als Palindrom erkennen. \n\n"
					+ "b) Die Palindrom Prüfung kann auch in der Weise erfolgen, dass der erste und der letzte, der zweite und der vorletzte, der dritte und der drittletzte usw. Buchstabe miteinander verglichen werden und die Prüfung bricht, wenn sie nicht übereinstimmen. Damit braucht die Schleife nur bis zur Wortmitte zu laufen. Programmieren Sie dieses Verfahre\n\n"
					+ "c) Erweitern sie die Palindrom Prüfung auf Sätze. Dabei sollen Leerzeichen und Interpunktionszeichnen keine Rolle spielen. Testen sie ihr Programm an den Sätzen „Madam, I'm Adam“; „Roma tibi subito motibus ibit amor“ und „ein Neger mit Gazelle zagt im Regen nie“. Wer findet den längsten “Palindromsatz”?";

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

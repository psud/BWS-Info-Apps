package com.example.infoapps;

import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Weckzeit extends Activity {

	EditText jetztStdTxt, jetztMinTxt, schlafStdTxt, schlafMinTxt;
	TimePicker timePicker;
	TextView finalTime;
	int jetztStunde, jetztMinute, schlafStunde, schlafMinute, totalStunde,
			totalMin;
	Button go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weckzeit);
		Initialize();

		PrintTimeToET();

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (jetztMinTxt.length() > 0 && jetztMinTxt.length() > 0
						&& schlafStdTxt.length() > 0
						&& schlafMinTxt.length() > 0) {
					go.setText("Go");
					go.setTextColor(Color.BLACK);

					GetAllInfo();

					Calculation();

					String totalMinStr;

					if (totalMin < 10)
						totalMinStr = "0" + Integer.toString(totalMin);
					else
						totalMinStr = Integer.toString(totalMin);

					finalTime.setText(Integer.toString(totalStunde) + "  :  "
							+ totalMinStr);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(schlafMinTxt.getWindowToken(),
							0);
				}

				else {
					go.setText("Alle Felder ausfuellen");
					go.setTextColor(Color.RED);
				}

			}

		});

		// TODO Auto-generated method stub

		// Dialog d = new Dialog(this);
		// d.setTitle("Weckzeit");
		// TextView tvAufgabe = new TextView(this);
		// tvAufgabe.setText("Blatt 1 Aufgabe 7\nAuf welche Zeit müssen Sie ihren Wecker stellen, wenn Sie um 23:23 Uhr genau 5 Stunden und 48 Minuten schlafen wollen (5:11 Uhr)? Entwerfen Sie einen Rechenweg, wählen Sie mind. 3 “kritische” Beispiele und codieren Sie ein entsprechendes Programm");
		// d.setContentView(tvAufgabe);
		// d.show();
		// //Toast.makeText(getApplicationContext(), "msg msg",
		// Toast.LENGTH_SHORT).show();
		// for (int i = 0; i < 3; i++){
		// Toast.makeText(getApplicationContext(),
		// "Blatt 1 Aufgabe 7\nAuf welche Zeit müssen Sie ihren Wecker stellen, wenn Sie um 23:23 Uhr genau 5 Stunden und 48 Minuten schlafen wollen (5:11 Uhr)? Entwerfen Sie einen Rechenweg, wählen Sie mind. 3 “kritische” Beispiele und codieren Sie ein entsprechendes Programm",
		// Toast.LENGTH_LONG).show();
		// }

	}

	private void Calculation() {
		// TODO Auto-generated method stub
		int calStd = jetztStunde + schlafStunde;
		int calMin = jetztMinute + schlafMinute;

		totalMin = calMin % 60;
		totalStunde = calStd % 24 + calMin / 60;

	}

	private void GetAllInfo() {
		// TODO Auto-generated method stub
		jetztStunde = Integer.parseInt(jetztStdTxt.getText().toString());
		jetztMinute = Integer.parseInt(jetztMinTxt.getText().toString());

		schlafStunde = Integer.parseInt(schlafStdTxt.getText().toString());
		schlafMinute = Integer.parseInt(schlafMinTxt.getText().toString());

	}

	private void PrintTimeToET() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		jetztStdTxt.setText(Integer.toString(c.get(Calendar.HOUR_OF_DAY)));
		jetztMinTxt.setText(Integer.toString(c.get(Calendar.MINUTE)));

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		jetztStdTxt = (EditText) findViewById(R.id.weckJetztStund);
		jetztMinTxt = (EditText) findViewById(R.id.weckJetztMin);
		schlafStdTxt = (EditText) findViewById(R.id.weckSchlafStd);
		schlafMinTxt = (EditText) findViewById(R.id.weckSchlafMin);
		finalTime = (TextView) findViewById(R.id.weckFinalTime);
		go = (Button) findViewById(R.id.weckGo);

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
	    String aufgabeNum = "Blatt 1 Aufgabe 7";
	    String realClassName = this.getClass().getName().substring(21);
	   String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);		
			String aufgabeText = "Auf welche Zeit müssen Sie ihren Wecker stellen, wenn Sie um 23:23 Uhr genau 5 Stunden und 48 Minuten schlafen wollen (5:11 Uhr)? Entwerfen Sie einen Rechenweg, wählen Sie mind. 3 “kritische” Beispiele und codieren Sie ein entsprechendes Programm";
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

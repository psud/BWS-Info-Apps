package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends ListActivity {
	String classes[] = { "Weckzeit", "WaagerechterWurf", "BusFahrtkosten",
			"Wechselgeld", "KaffeeTemp", "SkelettGrosse", "Funktionsgleichung",
			"ZulassungOberstufe", "Taschenrechner", "ZahlenRaten",
			"DreieckTester2", "Bisektionsverfahren", "LottoZahlen",
			"HornerSchema", "ZahlenInWorte", "Wetter", "Stringfunktionen",
			"Palindrome", "PalindromZahlen", "ZahlenBildungsgesetz", "Eieruhr" };

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		Class ourClass;
		try {
			ourClass = Class.forName("com.example.infoapps." + cheese);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this,
				android.R.layout.simple_list_item_1, classes));
		 
		SharedPreferences showAboutP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean showAbout = showAboutP.getBoolean("menuFirstOpen", true);
		if (showAbout){
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Hi, \ndanke das ihr euch die App runtergeladen habt. \nErstens mal bevor ihr mich alle Hobbylos nennt, ich hab die Info Programme in Java nachprogrammiert, weil ich in den Sommerferien die Weisheitszaehne rausgenommen bekommen hab und deswegen zwei Wochen nichts machen durfte (ausser mich auf der Couch langweilen) und da ich eh Java bzw Android programmation lernen wollte dachte ich mir, dass die C++ Info programme auch gut waeren zum Java lernen \n\nAlso für die neuen 11er die DV als Schwerpunkt haben, ihr könnt ja die App hier benutzen um eure Ergebnisse zu vergleichen wenn ihr wollt. Es sollte alles stimmen aber ich versicher euch nichts. Falls aber was nicht stimmt bitte bitte schreibt mir ne Mail. Entweder über den Bug Report Button von jedem Programmchen oder einfach als Mail an apps@patsud.com .  ";
			d.setTitle("About Me");
		tvAufgabe.setText(aufgabeText);
		d.setContentView(tvAufgabe);
		d.show();
		}
		SharedPreferences.Editor mEditor = showAboutP.edit();
		mEditor.putBoolean("menuFirstOpen", false).commit();
			
	}
	
	

		@Override
		public boolean onCreateOptionsMenu(android.view.Menu menu) {
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu);
			MenuInflater blowUp = getMenuInflater();
			blowUp.inflate(R.menu.about, menu);
			
			return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			String aufgabeNum = "Blatt 6 Aufgabe 1";
			String realClassName = this.getClass().getName().substring(21);
			String aufgabe = this.getTitle().toString();
			switch (item.getItemId()) {
			case (R.id.aboutMenu):
				Dialog d = new Dialog(this, 0);
				TextView tvAufgabe = new TextView(this);
				String aufgabeText = "Hi, \ndanke das ihr euch die App runtergeladen habt. \nErstens mal bevor ihr mich alle Hobbylos nennt, ich hab die Info Programme in Java nachprogrammiert, weil ich in den Sommerferien die Weisheitszaehne rausgenommen bekommen hab und deswegen zwei Wochen nichts machen durfte (ausser mich auf der Couch langweilen) und da ich eh Java bzw Android programmation lernen wollte dachte ich mir, dass die C++ Info programme auch gut waeren zum Java lernen \n\nAlso für die neuen 11er die DV als Schwerpunkt haben, ihr könnt ja die App hier benutzen um eure Ergebnisse zu vergleichen wenn ihr wollt. Es sollte alles stimmen aber ich versicher euch nichts. Falls aber was nicht stimmt bitte bitte schreibt mir ne Mail. Entweder über den Bug Report Button von jedem Programmchen oder einfach als Mail an apps@patsud.com .  ";
				d.setTitle("About Me");
			tvAufgabe.setText(aufgabeText);
			d.setContentView(tvAufgabe);
			d.show();
				break;
			}
			return false;
		}
}

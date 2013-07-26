package com.example.infoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Wetter extends Activity implements OnClickListener {

	SeekBar bar;
	RadioButton nord, ost, sud, west;
	TextView druck;
	ImageView sonne, regen, verand, compass;
	int lDruck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wetter);

		Initialize();

		nord.setOnClickListener(this);
		ost.setOnClickListener(this);
		sud.setOnClickListener(this);
		west.setOnClickListener(this);

		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Compute();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

				AllOff();
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				lDruck = progress;
				druck.setText(Integer.toString(progress) + "mBar");

			}
		});
		compass.setOnTouchListener(new OnSwipeTouchListener() {
		
		    public void onSwipeTop() {
		    	allCompOff();
		        nord.setChecked(true);
		        AllOff();
				Compute();
		    }
		    public void onSwipeRight() {
		    	allCompOff();
		        ost.setChecked(true);
		        AllOff();
				Compute();
		    }
		    public void onSwipeLeft() {
		    	allCompOff();
		    	west.setChecked(true);
		        AllOff();
				Compute();
		    }
		    public void onSwipeBottom() {
		    	allCompOff();
		        sud.setChecked(true);
		        AllOff();
				Compute();
		    }
		});

	}

	protected void AllOff() {
		// TODO Auto-generated method stub
		sonne.setAlpha(30);
		regen.setAlpha(30);
		verand.setAlpha(30);
	}

	protected void Compute() {
		// TODO Auto-generated method stub
		if (nord.isChecked() || ost.isChecked()) {
			if (lDruck > 750)
				verand.setAlpha(255);
			else
				sonne.setAlpha(255);
		}
		if (sud.isChecked()) {
			if (lDruck > 750)
				regen.setAlpha(255);
			else
				sonne.setAlpha(255);
		}
		if (west.isChecked()) {
			if (lDruck > 750)
				regen.setAlpha(255);
			else
				verand.setAlpha(255);
		}

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		nord = (RadioButton) findViewById(R.id.wetterNord);
		ost = (RadioButton) findViewById(R.id.wetterOst);
		sud = (RadioButton) findViewById(R.id.wetterSued);
		west = (RadioButton) findViewById(R.id.wetterWest);
		druck = (TextView) findViewById(R.id.wetterLuftdruck);
		bar = (SeekBar) findViewById(R.id.wetterBar);
		sonne = (ImageView) findViewById(R.id.wetterSonne);
		verand = (ImageView) findViewById(R.id.wetterVerand);
		regen = (ImageView) findViewById(R.id.wetterRegen);
		compass = (ImageView) findViewById(R.id.wetterCompass);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		allCompOff();
		switch (v.getId()) {
		case R.id.wetterNord:
			nord.setChecked(true);
			AllOff();
			Compute();
			break;
		case R.id.wetterOst:
			ost.setChecked(true);
			AllOff();
			Compute();
			break;
		case R.id.wetterSued:
			sud.setChecked(true);
			AllOff();
			Compute();
			break;
		case R.id.wetterWest:
			west.setChecked(true);;
			AllOff();
			Compute();
			break;
		}
	}

	private void allCompOff() {
		// TODO Auto-generated method stub
		nord.setChecked(false);
		ost.setChecked(false);
		sud.setChecked(false);
		west.setChecked(false);
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
		String aufgabeNum = "Blatt 3 Aufgabe 4";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Das Wetter in Lügenland richtet sich nach einer einfachen Regel. Diese läßt sich in folgender Tabelle darstellen: Nicht anzeigbar \n"
					+ "Nach Eingabe von Luftdruck und Windrichtung soll eine Wettervorhersage ausgegeben werden.";
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

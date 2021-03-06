package com.example.infoapps;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ZahlenRaten extends Activity implements OnGestureListener {

	ImageView up, down;
	EditText numberTxt;
	Button go;
	int randomNum, userNum;
	Random random;

	int num;
	private GestureDetector gDetector;

	// private final GestureDetector gestureDetector = new GestureDetector(new
	// GestureListener());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		random = new Random();
		randomNum = random.nextInt(100);

		setContentView(R.layout.zahlenraten);
		gDetector = new GestureDetector(this);
		Initialize();

		up.setAlpha(30);
		down.setAlpha(30);

		/*
		 * go.setOnTouchListener(new OnSwipeTouchListener() {
		 * 
		 * public void onSwipeTop() { num =
		 * Integer.parseInt(numberTxt.getText().toString()); num += 5;
		 * numberTxt.setText(Integer.toString(num)); Check();
		 * 
		 * } public void onSwipeRight() { num =
		 * Integer.parseInt(numberTxt.getText().toString()); num++;
		 * numberTxt.setText(Integer.toString(num)); Check(); } public void
		 * onSwipeLeft() { num =
		 * Integer.parseInt(numberTxt.getText().toString()); num--;
		 * numberTxt.setText(Integer.toString(num)); Check(); } public void
		 * onSwipeBottom() { num =
		 * Integer.parseInt(numberTxt.getText().toString()); num -= 5;
		 * numberTxt.setText(Integer.toString(num)); Check();
		 * 
		 * } });
		 */
		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Check();
				// numberTxt.setText("got it");

			}
		});

	}

	protected void Check() {
		// TODO Auto-generated method stub
		if (go.getText().toString().contentEquals("Check")) {
			userNum = Integer.parseInt(numberTxt.getText().toString());
			if (userNum < randomNum) {
				up.setAlpha(255);
				down.setAlpha(30);
			} else if (userNum > randomNum) {
				up.setAlpha(30);
				down.setAlpha(255);
			} else {
				up.setAlpha(255);
				down.setAlpha(255);
				numberTxt.setText("Geschafft. Nummer war "
						+ Integer.toString(randomNum));
				go.setText("Nochmal");
			}
		} else {
			randomNum = random.nextInt(100);
			go.setText("Check");
			numberTxt.setText("");
		}
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		up = (ImageView) findViewById(R.id.zahlenrUp);
		down = (ImageView) findViewById(R.id.zahlenrDown);
		go = (Button) findViewById(R.id.zahlenrGo);
		numberTxt = (EditText) findViewById(R.id.zahlenrNumber);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gDetector.onTouchEvent(me);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getRawY() < e2.getRawY()) {
			if (numberTxt.length() > 0) {
				num = Integer.parseInt(numberTxt.getText().toString());
				num -= 5;
				numberTxt.setText(Integer.toString(num));
				Check();
			} else
				numberTxt.setText("0");

			if (Integer.parseInt(numberTxt.getText().toString()) < 0)
				numberTxt.setText("0");
			
		} else if (e1.getRawY() > e2.getRawY()) {
			if (numberTxt.length() > 0) {
				num = Integer.parseInt(numberTxt.getText().toString());
				num += 5;
				numberTxt.setText(Integer.toString(num));
				Check();
			} else
				numberTxt.setText("100");

			if (Integer.parseInt(numberTxt.getText().toString()) > 100)
				numberTxt.setText("100");

		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
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
		String aufgabeNum = "Blatt 6 Aufgabe 1";
		String realClassName = this.getClass().getName().substring(21);
		String aufgabe = this.getTitle().toString();
		switch (item.getItemId()) {
		case (R.id.aufgabe):
			Dialog d = new Dialog(this, 0);
			TextView tvAufgabe = new TextView(this);
			String aufgabeText = "Eine vom Computer zuf�llig gew�hlte Zahl soll erraten werden. Nach jedem Rateversuch gibt der Computer 'zu' gro� "
					+ "oder 'zu klein' aus. Schreiben Sie ein Programm f�r dieses Spiel.";
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

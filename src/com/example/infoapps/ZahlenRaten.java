package com.example.infoapps;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ZahlenRaten extends Activity {

	ImageView up, down;
	EditText numberTxt;
	Button go;
	int randomNum, userNum;
	Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		random = new Random();
		randomNum = random.nextInt(100);

		setContentView(R.layout.zahlenraten);

		Initialize();

		up.setAlpha(30);
		down.setAlpha(30);

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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
				}
				else {
					randomNum = random.nextInt(100);
					go.setText("Check");
					numberTxt.setText("");
				}

			}
		});

	}

	private void Initialize() {
		// TODO Auto-generated method stub
		up = (ImageView) findViewById(R.id.zahlenrUp);
		down = (ImageView) findViewById(R.id.zahlenrDown);
		go = (Button) findViewById(R.id.zahlenrGo);
		numberTxt = (EditText) findViewById(R.id.zahlenrNumber);
	}

}

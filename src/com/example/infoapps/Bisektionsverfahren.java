package com.example.infoapps;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Bisektionsverfahren extends Activity {

	EditText ppTxt, pnTxt;
	Button go, neu;
	TextView outputTxt;
	NumberPicker koeffizientTxt;
	RadioGroup rGroup;
	RadioButton rb1, rb2;
	float PP, PN;
	int koeffizient;

	EditText[] eingabeA = new EditText[10];
	TextView[] eingabeTv = new TextView[10];

	float[] eingaben = new float[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bisektionsverfahren);

		Initialize();

		neu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				koeffizient = koeffizientTxt.getValue();

				for (int p = 0; p < koeffizient + 1; p++) {
					eingabeA[p].setVisibility(View.VISIBLE);
					eingabeTv[p].setVisibility(View.VISIBLE);
				}
				for (int i = koeffizient + 1; i < 10; i++) {
					eingabeA[i].setVisibility(View.GONE);
					eingabeTv[i].setVisibility(View.GONE);
				}

			}
		});

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ppTxt.length() > 0 && pnTxt.length() > 0) {
					GetValues();

					koeffizient = koeffizientTxt.getValue();

					CheckIfAllGood();

					float PPWert, PNWert, mitte, mitteWert;
					int i = 0, versuche = 0;

					while (i == 0) {
						versuche++; // Just for fun
						mitte = (PP + PN) / 2;

						PNWert = Fkt(PN);
						PPWert = Fkt(PP);
						mitteWert = Fkt(mitte);

						if (PNWert * PPWert <= 0) {
							NewDot(mitte, PPWert, PNWert, mitteWert);

							if (mitteWert <= 0.001 && mitteWert >= -0.001) {
								i = 1;
								outputTxt.setText("Nullstelle bei "
										+ Float.toString(mitte));

							} else
								i = 0;
						} else {
							outputTxt
									.setText("In diesem Bereich gibt es keine Nullstellen.");
							i = 1;
						}
					}

				}

			}

			protected void NewDot(float mitte, float y1, float y2,
					float mitteWert) {
				// TODO Auto-generated method stub
				if (y1 * mitteWert >= 0)
					PP = mitte;

				if (y2 * mitteWert > 0)
					PN = mitte;

			}

			protected float Fkt(float xWert) {
				// TODO Auto-generated method stub
				float ergebnis = 0;
				for (int h = koeffizient; h > 0; h--) {
					ergebnis = (ergebnis + eingaben[h]) * xWert;
					eingabeA[h].setText(Float.toString(eingaben[h]));
				}
				ergebnis = ergebnis + eingaben[0];
				return ergebnis;
			}

			private void GetValues() {
				// TODO Auto-generated method stub
				PP = Float.parseFloat(ppTxt.getText().toString());
				PN = Float.parseFloat(pnTxt.getText().toString());
			}
		});
	}

	protected void CheckIfAllGood() {
		// TODO Auto-generated method stub
		boolean allK = true;
		for (int d = 0; d <= koeffizient; d++) {
			if (eingabeA[d].length() == 0) {
				allK = false;
				eingabeA[d].setText("0");
				eingabeA[d].setHint("Nummer eingeben");
				eingabeTv[d].setTextColor(Color.RED);
			}
		}
		if (allK == true) {
			for (int f = 0; f < 10; f++) {
				eingabeA[f].setHint("");
				eingabeTv[f].setTextColor(Color.BLACK);
			}

			for (int k = 0; k <= koeffizient; k++) {
				eingaben[k] = Float
						.parseFloat(eingabeA[k].getText().toString());
			}
		}
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		ppTxt = (EditText) findViewById(R.id.bisekPP);
		pnTxt = (EditText) findViewById(R.id.bisekPN);

		go = (Button) findViewById(R.id.bisekGo);
		neu = (Button) findViewById(R.id.bisekNeu);
		outputTxt = (TextView) findViewById(R.id.bisekOutput);		

		koeffizientTxt = (NumberPicker) findViewById(R.id.bisektionNumPicker);

		eingabeA[0] = (EditText) findViewById(R.id.bisektionNum0);
		eingabeA[1] = (EditText) findViewById(R.id.bisektionNum1);
		eingabeA[2] = (EditText) findViewById(R.id.bisektionNum2);
		eingabeA[3] = (EditText) findViewById(R.id.bisektionNum3);
		eingabeA[4] = (EditText) findViewById(R.id.bisektionNum4);
		eingabeA[5] = (EditText) findViewById(R.id.bisektionNum5);
		eingabeA[6] = (EditText) findViewById(R.id.bisektionNum6);
		eingabeA[7] = (EditText) findViewById(R.id.bisektionNum7);
		eingabeA[8] = (EditText) findViewById(R.id.bisektionNum8);
		eingabeA[9] = (EditText) findViewById(R.id.bisektionNum9);

		eingabeTv[0] = (TextView) findViewById(R.id.bisektionA0);
		eingabeTv[1] = (TextView) findViewById(R.id.bisektionA1);
		eingabeTv[2] = (TextView) findViewById(R.id.bisektionA2);
		eingabeTv[3] = (TextView) findViewById(R.id.bisektionA3);
		eingabeTv[4] = (TextView) findViewById(R.id.bisektionA4);
		eingabeTv[5] = (TextView) findViewById(R.id.bisektionA5);
		eingabeTv[6] = (TextView) findViewById(R.id.bisektionA6);
		eingabeTv[7] = (TextView) findViewById(R.id.bisektionA7);
		eingabeTv[8] = (TextView) findViewById(R.id.bisektionA8);
		eingabeTv[9] = (TextView) findViewById(R.id.bisektionA9);
		
		
		koeffizientTxt.setMaxValue(9);
		koeffizientTxt.setMinValue(0);
		koeffizientTxt.setValue(4);

	}

}

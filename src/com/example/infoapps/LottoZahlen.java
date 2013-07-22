package com.example.infoapps;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class LottoZahlen extends Activity {

	GridView gridView;
	EditText testOutput;
	Button go;
	NumberPicker numPick1, numPick2, numPick3, numPick4, numPick5, numPick6;
	TextView tvFix;
	String[] numbers = new String[49];
	Integer[] finNums = new Integer[6];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottozahlen);

		Init();

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);
		gridView.setAdapter(adapter);

		final String testString;

		go.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				numbers[5] = "x";
				for (int i = 0; i < 49; i++) {
					numbers[i] = Integer.toString(i + 1);
				}
				Random random = new Random();
				for (int p = 0; p < 6; p++) {
					int thenum = random.nextInt(49);
					if (numbers[thenum].equals("X"))
						p--;
					else {
						numbers[thenum] = "X";
						finNums[p] = thenum;

					}
				}

				Arrays.sort(finNums);

				gridView.setAdapter(adapter);
				String testOutput = null;
				int found = 0;
				for (int o = 0; o < 6; o++) {
					testOutput += Integer.toString(finNums[o]) + ", ";
					Integer nD = finNums[o] + 1;

					found++;
					if (found == 1) {
						numPick1.setMaxValue(nD);
						numPick1.setMinValue(nD);
					} else if (found == 2) {
						numPick2.setMaxValue(nD);
						numPick2.setMinValue(nD);
					} else if (found == 3) {
						numPick3.setMaxValue(nD);
						numPick3.setMinValue(nD);
					} else if (found == 4) {
						numPick4.setMaxValue(nD);
						numPick4.setMinValue(nD);
					} else if (found == 5) {
						numPick5.setMaxValue(nD);
						numPick5.setMinValue(nD);
					} else if (found == 6) {
						numPick6.setMaxValue(nD);
						numPick6.setMinValue(nD);

					}
				}
				tvFix.setText(".");
			}
		});

	}

	private void Init() {
		// TODO Auto-generated method stub
		numPick1 = (NumberPicker) findViewById(R.id.lottoNumPicker1);
		numPick2 = (NumberPicker) findViewById(R.id.lottoNumPicker2);
		numPick3 = (NumberPicker) findViewById(R.id.lottoNumPicker3);
		numPick4 = (NumberPicker) findViewById(R.id.lottoNumPicker4);
		numPick5 = (NumberPicker) findViewById(R.id.lottoNumPicker5);
		numPick6 = (NumberPicker) findViewById(R.id.lottoNumPicker6);
		gridView = (GridView) findViewById(R.id.lottoGridView);
		tvFix = (TextView) findViewById(R.id.lottoTvFix);
		go = (Button) findViewById(R.id.lottoGo);

		for (int i = 0; i < 49; i++) {
			numbers[i] = Integer.toString(i + 1);
		}
	}

}

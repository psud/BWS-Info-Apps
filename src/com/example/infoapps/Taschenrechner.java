package com.example.infoapps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Taschenrechner extends Activity{

	Button go;
	EditText num1Txt, num2Txt;
	RadioButton rbPlus, rbMinus, rbMal, rbTeilt;
	TextView outputTxt;
	
	float num1, num2, total;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taschenrechner);
		
		Initialize();
		
		go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (num1Txt.length()>0 && num2Txt.length()>0){
					
					GetValues();
					
					if (rbPlus.isChecked())
						total = num1 + num2;
					if (rbMinus.isChecked())
						total = num1 - num2;
					if (rbMal.isChecked())
						total = num1 * num2;
					if (rbTeilt.isChecked())
						total = num1 / num2;
					
					outputTxt.setText(Float.toString(total));
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					 imm.hideSoftInputFromWindow(num2Txt.getWindowToken(), 0);
				}
				
				
			}
		});
	}

	protected void GetValues() {
		// TODO Auto-generated method stub
		num1 = Float.parseFloat(num1Txt.getText().toString());
		num2 = Float.parseFloat(num2Txt.getText().toString());
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		
		num1Txt = (EditText) findViewById(R.id.taschenr1stNum);
		num2Txt = (EditText) findViewById(R.id.taschenr2ndNum);
		go = 	(Button) findViewById(R.id.taschenrGo);
		outputTxt = (TextView) findViewById(R.id.taschenrOutput);
		rbPlus = (RadioButton) findViewById(R.id.taschenrRadioPlus);
		rbMinus = (RadioButton) findViewById(R.id.taschenrRadioMinus);
		rbMal = (RadioButton) findViewById(R.id.taschenrRadioMal);
		rbTeilt = (RadioButton) findViewById(R.id.taschenrRadioGeteilt);
	}

}

package com.example.infoapps;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
}

package com.example.infoapps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DreieckTester extends Activity {
	
	EditText aTxt, bTxt, cTxt;
	Button go;
	TextView outputTxt;
	float a, b, c;
	boolean rueck = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dreiecktester);
		
		Initialize();
		
		go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (aTxt.length()>0&&bTxt.length()>0&&cTxt.length()>0){
					GetValues();
					
					TestIfWorks();
					
					Output();
				}
			}
		});
		
	}

	protected void Output() {
		// TODO Auto-generated method stub
		if (rueck == true){
			outputTxt.setText("Dreieck ist Konstruirbar");
		}
		else
			outputTxt.setText("Dreieck ist NICHT Konstruirbar");
	}

	protected void TestIfWorks() {
		// TODO Auto-generated method stub
		if (a == 0 || b == 0 || c == 0)
	        rueck = false;

	    else {
	        if (a == b && a+b > c||
	            b == c && b+c > a||
	            a == c && a+c > b)
	            rueck = true;

	        else {
	            if (a>b && a>c){
	                if (a<b+c)
	                    rueck=true;
	                else
	                    rueck=false;
	            }
	            else {
	                if (b>a && b>c){
	                    if (b<a+c)
	                       rueck=true;
	                    else
	                        rueck=false;}

	                else{
	                    if (c>a && c>b){
	                        if (c<a+b)
	                           rueck=true;
	                        else
	                           rueck=false;
	                    }
	                }
	            }
	        }
	    }
	}

	protected void GetValues() {
		// TODO Auto-generated method stub
		a = Float.parseFloat(aTxt.getText().toString());
		b = Float.parseFloat(bTxt.getText().toString());
		c = Float.parseFloat(cTxt.getText().toString());
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		aTxt = (EditText) findViewById(R.id.dreieckA);
		bTxt = (EditText) findViewById(R.id.dreieckB);
		cTxt = (EditText) findViewById(R.id.dreieckC);
		go = (Button) findViewById(R.id.dreieckGo);
		outputTxt = (TextView) findViewById(R.id.dreieckOutput);
		
	}

}

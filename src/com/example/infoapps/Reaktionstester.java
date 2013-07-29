package com.example.infoapps;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reaktionstester extends Activity {

	Button reaktionButton;
	TextView rTime;
	long start, stop;
	boolean alreadyDone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reaktionstester);
		
		reaktionButton = (Button) findViewById(R.id.reaktionButton);
		rTime = (TextView) findViewById(R.id.reaktionTime);
		reaktionButton.setBackgroundColor(Color.GRAY);
		
		reaktionButton.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	reaktionbButton.setTextColor(Color.RED);
				if(reaktionButton.getText().toString().equalsIgnoreCase("Bereit")){
					reaktionButton.setTextColor(Color.GREEN);
					reaktionButton.setText("Wait...");
					Random waitTime = new Random();
					try {
						Thread.sleep(waitTime.nextInt(4000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}finally{reaktionButton.setTextColor(Color.WHITE);
				reaktionButton.setBackgroundColor(Color.RED);
					reaktionButton.setText("CLICK!");
					start= System.currentTimeMillis();
					alreadyDone = false;
					
					}
				}
				//else if(reaktionButton.getText().toString().equalsIgnoreCase("Wait...")){
					//reaktionButton.setTextColor(Color.WHITE);
					//reaktionButton.setBackgroundColor(Color.RED);
					//reaktionButton.setText("CLICK!");
				
				else if(reaktionButton.getText().toString().equalsIgnoreCase("CLICK!")){
					reaktionButton.setTextColor(Color.WHITE);
					reaktionButton.setBackgroundColor(Color.GRAY);
					reaktionButton.setText("Nochmal");
					stop=System.currentTimeMillis();
					
					if (start != 0&&alreadyDone == false){
						long result = stop - start;
						
						alreadyDone = true;

						rTime.setText(Integer.toString((int)result)+" msec");
					}
				}}
		});
		
	}
}

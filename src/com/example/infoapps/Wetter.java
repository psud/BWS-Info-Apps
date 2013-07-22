package com.example.infoapps;

import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.SeekBar;

public class Wetter extends Activity{
	
	AnalogClock clock;
	SeekBar bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wetter);
		
		clock = (AnalogClock) findViewById(R.id.wetterClock);
		bar = (SeekBar) findViewById(R.id.wetterBar);
		
		
	}
}

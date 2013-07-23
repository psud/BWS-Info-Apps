package com.example.infoapps;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Wetter extends Activity implements OnClickListener {

	SeekBar bar;
	RadioButton nord, ost, sud, west;
	TextView druck;
	ImageView sonne, regen, verand;
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
				druck.setText(Integer.toString(progress)+ "mBar");

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
		if(nord.isChecked()||ost.isChecked()){
			if(lDruck > 750)
				verand.setAlpha(255);
			else
				sonne.setAlpha(255);
		}
		if(sud.isChecked()){
			if(lDruck > 750)
				regen.setAlpha(255);
			else
				sonne.setAlpha(255);
		}
		if(west.isChecked()){
			if(lDruck > 750)
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
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.wetterNord:
			ost.setChecked(false);
			sud.setChecked(false);
			west.setChecked(false);
			AllOff();
			Compute();
			break;
		case R.id.wetterOst:
			nord.setChecked(false);
			sud.setChecked(false);
			west.setChecked(false);
			AllOff();
			Compute();
			break;
		case R.id.wetterSued:
			ost.setChecked(false);
			nord.setChecked(false);
			west.setChecked(false);
			AllOff();
			Compute();
			break;
		case R.id.wetterWest:
			ost.setChecked(false);
			sud.setChecked(false);
			nord.setChecked(false);
			AllOff();
			Compute();
			break;
		}
	}
}

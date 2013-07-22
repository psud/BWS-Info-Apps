package com.example.infoapps;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ZahlenSPrefs extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		
	}
}

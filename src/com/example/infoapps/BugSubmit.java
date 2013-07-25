package com.example.infoapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BugSubmit extends Activity{
	
	Button send;
	EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bugsubmit);
	
		
		send = (Button) findViewById(R.id.bugSend);
		text = (EditText) findViewById(R.id.bugText);
		
		Bundle getClass = getIntent().getExtras();;
		final String ClassName = getClass.getString("bugClass");
		final String aufgabeNum = getClass.getString("bugNum");
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userText = text.getText().toString();
				String finalText = "";
				finalText += "Bug Report in "+ClassName+ " --- "+aufgabeNum
						+ "\n\nUser Comment:\n"+ userText;
				
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				String emailAdress[]={"p.sudhaus2@gmail.com"};
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAdress);
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bug "+ClassName);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, finalText);
				startActivity(emailIntent);
			}
		});
		
			
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}

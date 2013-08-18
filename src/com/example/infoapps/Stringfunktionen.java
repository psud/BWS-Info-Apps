package com.example.infoapps;

import java.math.RoundingMode;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Stringfunktionen extends Activity implements OnClickListener {

	Button laengeGo, posGo, buchGo, teilGo, loschGo, einfugGo, formatGo,
			grosGo;
	Button felderFullen;
	EditText laengeIn, laengeOut; // laenge
	EditText posIn, posQuelle, posOut; // position
	EditText buchIn, buchPos, buchOut; // buchstabe
	EditText teilIn, teilVon, teilBis, teilOut; // Teilstring
	EditText loschIn, loschVon, loschBis, loschOut; // loschen
	EditText einfugIn, einfugQuelle, einfugAb, einfugOut; // einfugen
	EditText formatIn, formatGenau, formatOut; // formatieren
	EditText grosIn, grosIn2, grosOut; // position

	String laengeInS, laengeOutS;
	String posInS, posQuelleS, posOutS; // position
	String buchInS, buchPosS, buchOutS; // buchstabe
	String teilInS, teilVonS, teilBisS, teilOutS; // Teilstring
	String loschInS, loschVonS, loschBisS, loschOutS; // loschen
	String einfugQuelleS, einfugAbS, einfugOutS; // einfugen
	String formatInS, formatGenauS; // formatieren
	String grosInS, grosIn2S, grosOutS; // position

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stringfunktionen);

		Initialize();
		felderFullen.setOnClickListener(this);
		laengeGo.setOnClickListener(this);
		posGo.setOnClickListener(this);
		buchGo.setOnClickListener(this);
		teilGo.setOnClickListener(this);
		loschGo.setOnClickListener(this);
		einfugGo.setOnClickListener(this);	
		formatGo.setOnClickListener(this);
		grosGo.setOnClickListener(this);

	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.strfktFelderAusfullen:
			FillFields();
			break;
		case R.id.strfktLangeGo:
			laengeOut.setText(Integer.toString(laengeIn.length()));
			break;
		case R.id.strfktPositionGo:
			posOut.setText(Integer.toString(posIn.getText().toString().indexOf(posQuelle.getText().toString())));
			break;
		case R.id.strfktBuchstabeGo:
			buchOut.setText(buchIn.getText().toString().substring(Integer.parseInt(buchPos.getText().toString()), Integer.parseInt(buchPos.getText().toString())+1));
			break;
		case R.id.strfktTeilstringGo:
			teilOut.setText(teilIn.getText().toString().substring(Integer.parseInt(teilVon.getText().toString()), Integer.parseInt(teilBis.getText().toString())+1));
			break;
		case R.id.strfktLoschGo:
			loschOut.setText(loschIn.getText().toString().replaceAll(loschIn.getText().toString().substring(Integer.parseInt(loschVon.getText().toString()), Integer.parseInt(loschBis.getText().toString())+1), ""));
			break;
		case R.id.strfktEinfugGo:	
			einfugOut.setText(einfugIn.getText().toString().substring(0, Integer.parseInt(einfugAb.getText().toString())) +einfugQuelle.getText().toString()+ einfugIn.getText().toString().substring( Integer.parseInt(einfugAb.getText().toString()), einfugIn.getText().toString().length()));
			break;
		case R.id.strfktFormGo:
			String kk = formatIn.getText().toString();
			double number = Double.parseDouble(kk);
			NumberFormat fmt = NumberFormat.getNumberInstance();
			fmt.setMaximumFractionDigits(Integer.parseInt(formatGenau.getText().toString()));
			fmt.setRoundingMode(RoundingMode.HALF_UP);
			kk = fmt.format(number);
			formatOut.setText(kk);
			break;
		case R.id.strfktGrosGo:
			int look = grosIn.getText().toString().compareTo(grosIn2.getText().toString());
			if (look == 0)
				grosOut.setText("gleich");
			if (look == -1)
				grosOut.setText("Nein");
			if (look == 1)
				grosOut.setText("Ja");
			
			
			break;
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void FillFields() {
		// TODO Auto-generated method stub
		laengeIn.setText("Teststring");
		posIn.setText("Teststring");
		posQuelle.setText("t");
		buchIn.setText("Teststring");
		buchPos.setText("4");
		teilIn.setText("Teststring");
		teilVon.setText("2");	
		teilBis.setText("4");	
		loschIn.setText("Teststring");
		loschVon.setText("2");	
		loschBis.setText("4");	
		einfugIn.setText("Teststring");
		einfugQuelle.setText("BWS");	
		einfugAb.setText("4");	
		formatIn.setText("12.3456789012345");
		formatGenau.setText("4");
		grosIn.setText("ABC");
		grosIn2.setText("ABD");
		
	}



	private void Initialize() {
		// TODO Auto-generated method stub
		laengeGo = (Button) findViewById(R.id.strfktLangeGo);
		posGo = (Button) findViewById(R.id.strfktPositionGo);
		buchGo = (Button) findViewById(R.id.strfktBuchstabeGo);
		teilGo = (Button) findViewById(R.id.strfktTeilstringGo);
		loschGo = (Button) findViewById(R.id.strfktLoschGo);
		einfugGo = (Button) findViewById(R.id.strfktEinfugGo);
		formatGo = (Button) findViewById(R.id.strfktFormGo);
		grosGo = (Button) findViewById(R.id.strfktGrosGo);
		felderFullen = (Button) findViewById(R.id.strfktFelderAusfullen);
		
		laengeIn = (EditText) findViewById(R.id.strfktLangeInput);
		laengeOut = (EditText) findViewById(R.id.strfktLangeOutput);
		posIn = (EditText) findViewById(R.id.strfktPositionInput);
		posQuelle = (EditText) findViewById(R.id.strfktPositionQuelle);
		posOut = (EditText) findViewById(R.id.strfktPositionOutput);
		buchIn = (EditText) findViewById(R.id.strfktBuchstabeInput);
		buchPos = (EditText) findViewById(R.id.strfktBuchstabePosition);
		buchOut = (EditText) findViewById(R.id.strfktBuchstabeOutput);
		teilIn = (EditText) findViewById(R.id.strfktTeilstringInput);
		teilVon = (EditText) findViewById(R.id.strfktTeilstringVon);
		teilBis = (EditText) findViewById(R.id.strfktTeilstringBis);
		teilOut = (EditText) findViewById(R.id.strfktTeilstringOutput);
		loschIn = (EditText) findViewById(R.id.strfktLoschInput);
		loschVon = (EditText) findViewById(R.id.strfktLoschVon);
		loschBis = (EditText) findViewById(R.id.strfktLoschBis);
		loschOut = (EditText) findViewById(R.id.strfktLoschOutput);
		einfugIn= (EditText) findViewById(R.id.strfktEinfugInput);
		einfugQuelle = (EditText) findViewById(R.id.strfktEinfugQuelle);
		einfugAb = (EditText) findViewById(R.id.strfktEinfugAb);
		einfugOut = (EditText) findViewById(R.id.strfktEinfugOutput);
		formatIn = (EditText) findViewById(R.id.strfktFormInput);
		formatGenau = (EditText) findViewById(R.id.strfktFormGenau);
		formatOut = (EditText) findViewById(R.id.strfktFormOutput);
		grosIn = (EditText) findViewById(R.id.strfktGrosI1);
		grosIn2 = (EditText) findViewById(R.id.strfktGrosI2);
		grosOut = (EditText) findViewById(R.id.strfktGrosOutput);
		

	}
	// //////////Show Aufgabenstellung
		@Override
		public boolean onCreateOptionsMenu(android.view.Menu menu) {
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu);
			MenuInflater blowUp = getMenuInflater();
			blowUp.inflate(R.menu.aufgabe, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		    String aufgabeNum = "Aufgabenfeld Zeichenketten";
		    String realClassName = this.getClass().getName().substring(21);
		   String aufgabe = this.getTitle().toString();
			switch (item.getItemId()) {
			case (R.id.aufgabe):
				Dialog d = new Dialog(this, 0);
				TextView tvAufgabe = new TextView(this);		
				String aufgabeText = "Keine Aufgabenstellung";
				d.setTitle(aufgabe);
				tvAufgabe.setText(aufgabe + " - "+ aufgabeNum +"\n\n" + aufgabeText);
				d.setContentView(tvAufgabe);
				d.show();

				break;

			case R.id.bug:		
				Bundle sendClassName = new Bundle();
				sendClassName.putString("bugClass",realClassName);
				sendClassName.putString("bugNum", aufgabeNum);
				Intent bugSend = new Intent(this, BugSubmit.class); 
				bugSend.putExtras(sendClassName);
				startActivity(bugSend);		
				break;
			
			case R.id.code:			
					Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
		            myWebLink.setData(Uri.parse("https://github.com/psud/BWS-Info-Apps/blob/master/src/com/example/infoapps/"+realClassName+".java"));
		                startActivity(myWebLink);
					break;
					}
			return false;
		}
}

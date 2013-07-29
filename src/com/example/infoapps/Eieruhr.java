package com.example.infoapps;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class Eieruhr extends Activity {

	TimePicker set;
	TextView out;
	Button go;
	Boolean timerOn = true;
	int setMin, setHour;
	int showshowhour;
	final Handler handler = new Handler();
	int nCounter = 0;
	private Handler mHandler = new Handler();

	int testInt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eieruhr);

		Initialize();

		set.setIs24HourView(true);
		set.setCurrentHour(0);
		set.setCurrentMinute(0);

		updateDisplay(12, 05);
		set.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				updateDisplay(hourOfDay, minute);
				setMin = minute;
				setHour = hourOfDay;
			}
		});

		int waitTime = 200;
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// StartTimer();
				// out.setTextSize(40);

				// final int time = 200;

				// MyTimerTask myTask = new MyTimerTask();
				// Timer myTimer = new Timer();
				// public void schedule (TimerTask task, long delay, long
				// period)
				// Schedule a task for repeated fixed-delay execution after a
				// specific delay.
				//
				// Parameters
				// task the task to schedule.
				// delay amount of time in milliseconds before first execution.
				// period amount of time in milliseconds between subsequent
				// executions.

				// myTimer.schedule(myTask, 0, 1000);

				/*
				 * int minuteShow = setMin*1000; int hourShow = setHour*1000;
				 * 
				 * 
				 * 
				 * 
				 * new CountDownTimer(hourShow, 10000) { public void onTick(long
				 * millisUntilFinished) { showshowhour = (int)
				 * millisUntilFinished/1000; }
				 * 
				 * public void onFinish() { out.setText("Finished"); }
				 * }.start();
				 * 
				 * new CountDownTimer(minuteShow, 10) { public void onTick(long
				 * millisUntilFinished) { updateDisplay(showshowhour, (int)
				 * millisUntilFinished/1000); }
				 * 
				 * public void onFinish() { out.setText("Finished"); }
				 * }.start();
				 */

				/*final int time = 500;
				
				Thread updateThread = new Thread(){
					public void run(){
						while (testInt < 10) {
					
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} finally {
								testInt++;
								out.setText(Integer.toString(testInt));
							}
						
						}
					}
				};
				updateThread.setDaemon(true);
				updateThread.start();
				
				while (!shell.isDisposed()) {
			        if (!display.readAndDispatch()) {
			            display.sleep();
			        }
			    }*/
				//while (testInt < 10) {
					// Thread timer = new Thread() {
					// // public void run() {
					// timer.sleep(1000);
					// testInt++;
					// out.setText(Integer.toString(testInt));
					// }
					// };

					
			//	}
				
				while (nCounter <10){
				mHandler.removeCallbacks(hMyTimeTask);
				mHandler.postDelayed(hMyTimeTask, 1000);
				}
			}
		});
		

	}private Runnable hMyTimeTask = new Runnable() {
		   public void run() {
			   nCounter++;
		    	   out.setText("Hallo from thread counter: " + Integer.toString(nCounter));
		   }
		}; 

	protected void StartTimer() {
		// TODO Auto-generated method stub
		TimerTask mTimerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						nCounter++;
						out.setText("Coutenr: " + nCounter);
					}
				});
			}
		};
	}

	void myTask() {
		out.setText("Okay");

	}

	class MyTimerTask extends TimerTask {
		public void run() {
			// ERROR
			out.setText("Impossible");
			// how update TextView in link below
			// http://android.okhelp.cz/timer-task-timertask-run-cancel-android-example/

			System.out.println("");
		}
	}

	private void updateDisplay(int hourOfDay, int minute) {
		String minStr;
		if (minute < 10)
			minStr = "0" + Integer.toString(minute);
		else
			minStr = Integer.toString(minute);
		String outStr = Integer.toString(hourOfDay) + "  :  " + minStr;
		out.setText(outStr);
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		set = (TimePicker) findViewById(R.id.eiuTimerSet);
		out = (TextView) findViewById(R.id.eiuTimerText);
		go = (Button) findViewById(R.id.eiuTimerGo);
	}
}

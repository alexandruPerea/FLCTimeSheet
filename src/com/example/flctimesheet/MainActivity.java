package com.example.flctimesheet;

import java.util.Date;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b = (Button) findViewById(R.id.button1);
		
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AsyncCallWS task = new AsyncCallWS();
				task.execute();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class AsyncCallWS extends AsyncTask<String,Void,Void>{

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Date from = new Date();
			from.UTC(2013, 9, 27, 17, 0, 0);
			Date to = new Date();
			to.UTC(2013, 9, 27, 17, 10, 0);
			WebService.invokeWS("SR!", from, to, "9gag", "insertIntoTimeSheet");
			return null;
		}
		
	}
}

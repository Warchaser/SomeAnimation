package com.example.someanimation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	private FloatButtonL mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeView();
	}

	private void initializeView()
	{
		mButton = (FloatButtonL) findViewById(R.id.btn);
		
		mButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				mButton.startRotateAnimation();
				Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy()
	{
		if(null != mButton)
		{
			mButton.removeAllElements();
			mButton = null;
		}

		super.onDestroy();

		System.exit(0);
	}
}

package com.example.someanimation;

import android.os.Bundle;
import android.app.Activity;
import android.os.Debug;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.example.someanimation.App.APP;
import com.example.someanimation.absOnes.AbsRotateButtonL;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity 
{

	private RotateButtonL mButton;
	private boolean mIsRotated = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//
		RefWatcher refWatcher = APP.getRefWatcher(this);
		refWatcher.watch(this);

//		File heapDumpFile = new File("heapdump.hprof");
//
//		try
//		{
//			Debug.dumpHprofData(heapDumpFile.getAbsolutePath());
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}

		initializeView();
	}

	private void initializeView()
	{
		mButton = (RotateButtonL) findViewById(R.id.btn);
		
		mButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
//				mButton.startDefaultRotateAnimation();
				mButton.setAbsAnimation(new AbsRotateButtonL()
				{
					@Override
					public void startRotateAnimation()
					{
						RotateAnimation animation;

						if(!mIsRotated)
						{
							animation = new RotateAnimation(0f, 45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
							mIsRotated = true;
						}
						else
						{
							animation = new RotateAnimation(45f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
							mIsRotated = false;
						}

						animation.setAnimationListener(new Animation.AnimationListener()
						{
							@Override
							public void onAnimationStart(Animation animation)
							{
								mButton.setClickable(false);
							}

							@Override
							public void onAnimationEnd(Animation animation)
							{
								mButton.setClickable(true);
							}

							@Override
							public void onAnimationRepeat(Animation animation)
							{

							}
						});

						animation.setDuration(500);

						animation.setFillAfter(true);

						mButton.startAnimation(animation);
					}
				});
				mButton.startCustomAnimation();
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

	private AbsRotateButtonL animationClass = new AbsRotateButtonL()
	{
		@Override
		public void startRotateAnimation()
		{
			RotateAnimation animation;

			if(!mIsRotated)
			{
				animation = new RotateAnimation(0f, 45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				mIsRotated = true;
			}
			else
			{
				animation = new RotateAnimation(45f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				mIsRotated = false;
			}

			animation.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{
					mButton.setClickable(false);
				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mButton.setClickable(true);
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{

				}
			});

			animation.setDuration(500);

			animation.setFillAfter(true);

			mButton.startAnimation(animation);
		}
	};

	@Override
	protected void onDestroy()
	{

		if(null != animationClass)
		{
			animationClass = null;
		}

		if(null != mButton)
		{
			mButton.removeAllElements();
			mButton = null;
		}
		super.onDestroy();

//		System.exit(0);
	}
}

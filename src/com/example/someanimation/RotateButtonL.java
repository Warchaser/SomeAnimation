package com.example.someanimation;

import android.content.Context;
import android.widget.ImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;


public class RotateButtonL extends ImageButton implements OnClickListener, AnimationListener
{
	private boolean mIsTurned = false;
	private float m2Degree = 45f;
	
	private AnimationListener mAnimationListener;
	
	public RotateButtonL(Context context)
	{
		super(context);
		this.setOnClickListener(this);
	}
	
	public RotateButtonL(Context context, AttributeSet attrs)
	{
        super(context, attrs);
		this.setOnClickListener(this);
    }

	public RotateButtonL(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		this.setOnClickListener(this);
    }

	@Override
	public final void onClick(View v)
	{
		this.startRotateAnimation();
	}
	
	public void setAnimationListener(AnimationListener animationListener)
	{
		if(animationListener != null)
		{
			this.mAnimationListener = animationListener;
		}
	}
	
	public final void startRotateAnimation()
	{
		RotateAnimation animation;
		
		if(!mIsTurned)
		{
			animation = new RotateAnimation(0f, m2Degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			mIsTurned = true;
		}
		else
		{
			animation = new RotateAnimation(m2Degree, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			mIsTurned = false;
		}
		
		if(mAnimationListener != null)
		{
			animation.setAnimationListener(this.mAnimationListener);
		}
		else
		{
			animation.setAnimationListener(this);
		}
		
		animation.setDuration(500);
		
		animation.setFillAfter(true);
		
		this.startAnimation(animation);
	}
	
	public void set2Degree(float toDegree)
	{
		this.m2Degree = toDegree;
	}
	
	public float get2Degree()
	{
		return this.m2Degree;
	}

	@Override
	public void onAnimationEnd(Animation animation)
	{
		this.setClickable(true);
		this.setFocusable(true);
	}

	@Override
	public void onAnimationRepeat(Animation animation)
	{
		
	}

	@Override
	public void onAnimationStart(Animation animation)
	{
		this.setClickable(false);
		this.setFocusable(false);
	}

	public final void removeAllElements()
	{
		if(mAnimationListener != null)
		{
			mAnimationListener = null;
		}
	}
}

package com.example.someanimation;

import android.content.Context;
import android.widget.ImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

import com.example.someanimation.absOnes.AbsRotateButtonL;


public class RotateButtonL extends ImageButton implements OnClickListener, AnimationListener
{
	private boolean mIsTurned = false;
	private float m2Degree = 45f;
	private int mDuration = 500;
	
	private AnimationListener mAnimationListener;

	private AbsRotateButtonL mAnimationClass;
	
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
		this.startDefaultRotateAnimation();
	}
	
	public void setAnimationListener(AnimationListener animationListener)
	{
		if(animationListener != null)
		{
			this.mAnimationListener = animationListener;
		}
	}

	public void setAbsAnimation(AbsRotateButtonL absRotateButtonL)
	{
		this.mAnimationClass = absRotateButtonL;
	}

	public final void startDefaultRotateAnimation()
	{
		this.absAnimation.startRotateAnimation();
	}

	public final void startCostumeAnimation()
	{
		if(mAnimationClass != null)
		{
			mAnimationClass.startRotateAnimation();
		}
	}
	
	public void set2Degree(float toDegree)
	{
		this.m2Degree = toDegree;
	}
	
	public float get2Degree()
	{
		return this.m2Degree;
	}

	public void set2Duration(int duration)
	{
		this.mDuration = duration;
	}

	public int getDuration()
	{
		return this.mDuration;
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

	private AbsRotateButtonL absAnimation = new AbsRotateButtonL()
	{
		@Override
		public void startRotateAnimation()
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
				animation.setAnimationListener(mAnimationListener);
			}
			else
			{
				animation.setAnimationListener(RotateButtonL.this);
			}

			animation.setDuration(mDuration);

			animation.setFillAfter(true);

			startAnimation(animation);
		}
	};
}

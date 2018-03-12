package com.usu.mapps.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.usu.mapps.R;

@SuppressLint("DrawAllocation")
public class RoundedImageView extends AppCompatImageView {
	public static final int RTYPE_ALL = 1;
	public static final int RTYPE_UPPER = 2;
	public static final int RTYPE_LOWER = 3;
	
	public static final float RAD_DEFAULT = 5f;
    /**
     * roundType
     * 	=1 if we make image with 4 rounded corners
     * 	=2 if we only round the image with two upper corners
     * 	=3 if we only round the image with two lower corners
     */
	public static float roundType = RTYPE_ALL;
    /**
     * radius: of the corner
     */
	public static float radius = RAD_DEFAULT;  

    public RoundedImageView(Context context){
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray arr=context.obtainStyledAttributes(attrs,
                	R.styleable.RoundedImageView,0,0);
            roundType=arr.getInt(R.styleable.RoundedImageView_roundType,
            					RTYPE_ALL);
            radius=arr.getFloat(R.styleable.RoundedImageView_radius,
            					RAD_DEFAULT);
            arr.recycle();
    }

    public RoundedImageView(Context context, AttributeSet attrs,
                            int defStyle){
        super(context,attrs,defStyle);
        TypedArray arr=context.obtainStyledAttributes(attrs,
            	R.styleable.RoundedImageView,0,0);
        roundType=arr.getInt(R.styleable.RoundedImageView_roundType,
        					RTYPE_ALL);
        radius=arr.getFloat(R.styleable.RoundedImageView_radius,
        					RAD_DEFAULT);
        arr.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath=new Path();
        RectF rect=new RectF(0,0,this.getWidth(),this.getHeight());
        if (roundType==RTYPE_ALL){
        	clipPath.addRoundRect(rect,radius,radius, Path.Direction.CW);
        }else if (roundType==RTYPE_UPPER){
            clipPath.addRoundRect(rect,
            				new float[]{radius,radius,radius,radius,0,0,0,0},
            				Path.Direction.CW);
        }
        try{
        	canvas.clipPath(clipPath);
        }catch(java.lang.UnsupportedOperationException e){
        	// lower OS version (before 4.2.x) doesn't support this function
        }
        super.onDraw(canvas);
    }
}

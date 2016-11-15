package com.anwesome.games.rotaingripplebutton;

import android.util.AttributeSet;
import android.widget.Button;
import android.view.*;
import android.graphics.*;
import android.content.Context;


/**
 * Created by anweshmishra on 15/11/16.
 */
public class RotatingRipple extends Button {
    private boolean shouldInvalidate = false;
    private OnClickListener onClickListener;
    private float scale = 0f,rotationDeg = 0,rotatingSpeed = 0,scaleSpeed = 0f;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public RotatingRipple(Context context) {
        super(context);
    }
    public RotatingRipple(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.argb(AppConstants.ALPHA_VALUE,AppConstants.RED_VALUE,AppConstants.GREEN_VALUE,AppConstants.BLUE_VALUE));
        rotationDeg+=rotatingSpeed;
        scale+=scaleSpeed;

        canvas.save();
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.scale(scale,scale);
        canvas.rotate(rotationDeg);
        canvas.drawRect(new RectF(-canvas.getWidth()/2,-canvas.getHeight()/2,canvas.getWidth()/2,canvas.getHeight()/2),paint);
        canvas.restore();
        paint.setTextSize(getTextSize());
        paint.setColor(getCurrentTextColor());
        float textMeasurement = paint.measureText(getText().toString());
        canvas.drawText(getText().toString(),canvas.getWidth()/2-textMeasurement/2,canvas.getHeight()/2,paint);
        if(shouldInvalidate) {
            try {
                Thread.sleep(AppConstants.DELAY);
                if(scale >= 1.0f && rotationDeg >= 360) {
                    rotationDeg = 0;
                    scale = 0;
                    rotatingSpeed = 0;
                    scaleSpeed = 0;
                    shouldInvalidate = false;
                    if(onClickListener != null) {
                        onClickListener.onClick(this);
                    }
                }
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            rotatingSpeed = AppConstants.MAX_ROTATING_SPEED;
            scaleSpeed = AppConstants.MAX_SCALE_SPEED;
            shouldInvalidate = true;
            postInvalidate();
        }
        return true;
    }
}

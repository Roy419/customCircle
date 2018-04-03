package com.cly.customview.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.cly.customview.DensityUtil;
import com.cly.customview.MyApplication;
import com.cly.customview.R;

/**
 * Created by admin on 2018/3/22.
 */

public class PieView extends View {
    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private float circleText=13;
    private int height;
    private int width ;

    private boolean startAnimation = true;
    private  boolean noLayoutRefresh=true;
    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         if(attrs !=null){
             TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieView, defStyleAttr, 0);
             for (int i = 0; i < typedArray.length(); i++) {
                 int index = typedArray.getIndex(i);
                 switch (index){
                     case R.styleable.PieView_textSize:
                         circleText = typedArray.getDimensionPixelSize(index,13);
                         break;
                 }
             }
         }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);

        if(noLayoutRefresh) {
            noLayoutRefresh=false;
            int modeH = MeasureSpec.getMode(heightMeasureSpec);
            int sizeH = MeasureSpec.getSize(heightMeasureSpec);
            if (modeW == MeasureSpec.EXACTLY) { //相当于设置了精确值 如 maniparent  149dp
                width = sizeW;
            } else {
                width = getMeasuredWidth() + getPaddingRight() + getPaddingLeft();

               if (modeW == MeasureSpec.AT_MOST) {
             /*if(mode == MeasureSpec.AT_MOST)*/
                   width = Math.min(width,sizeW);
                }
            }
            if (modeH == MeasureSpec.EXACTLY) {
                height = sizeH;
            } else  {

                height = getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
                if (modeH == MeasureSpec.AT_MOST) {
                    height = Math.min(height,sizeH);
                }

            }

            setMeasuredDimension(width, height);
            init();
        }
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.YELLOW);
        paint2.setStrokeCap(Paint.Cap.ROUND);//圆形
        paint2.setStrokeWidth(20);
        paint2.setStyle(Paint.Style.STROKE);

        paint3 = new Paint();
        paint3.setColor(Color.GRAY);
        paint3.setTextSize(circleText);

        height = height/2;
        width = width/2;
        if(startAnimation) {
            setValue(500,230);
           startAnimation = false;
        }
    }

    float mParent;
    String mContent="";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

         int r = width - DensityUtil.dip2px(MyApplication.context,20);
         if(height < width){
                 r = height -DensityUtil.dip2px(MyApplication.context,20);;
         }
        canvas.drawCircle(width, height,r,paint);
        RectF rectF = new RectF();
        rectF.set(width -r, height -r, width +r, height +r);
        canvas.drawArc(rectF,90,mParent,false,paint2);
        Rect rect = new Rect();
        paint3.getTextBounds(mContent,0,mContent.length(),rect);

        canvas.drawText(mContent, width -rect.width()/2, height +rect.height()/2,paint3);
        canvas.restore();

        /*
       Paint mPaint;
        Path  mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //画线
        mPaint.setStyle(Paint.Style.STROKE);    //画边框
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);

        //Lollipop      TODO 使用画路径的方式
        mPath.moveTo(180, 30);
        mPath.lineTo(320, 30);
        mPath.lineTo(370, 65);
        canvas.drawPath(mPath, mPaint);

        //KitKat
        mPath.moveTo(180, 600);
        mPath.lineTo(320, 600);
        mPath.lineTo(380, 560);
        canvas.drawPath(mPath, mPaint);

        //Marshmallow       TODO 使用画线的方式
        canvas.drawLine(750, 170, 790, 150, mPaint);
        canvas.drawLine(790, 150, 850, 150, mPaint);

        //Froyo
        canvas.drawLine(805, 320, 900, 320, mPaint);

        //Gingerbread
        mPath.moveTo(805, 340);
        mPath.lineTo(840, 340);
        mPath.lineTo(870, 360);
        mPath.lineTo(900, 360);
        canvas.drawPath(mPath, mPaint);

        //Ice Cream Sandwich
        mPath.moveTo(795, 390);
        mPath.lineTo(840, 390);
        mPath.lineTo(860, 420);
        mPath.lineTo(900, 420);
        canvas.drawPath(mPath, mPaint);

        //Jelly Bean
        mPath.moveTo(680, 550);
        mPath.lineTo(720, 570);
        mPath.lineTo(860, 570);
        canvas.drawPath(mPath, mPaint);

        //画文字   TODO 可以使用循环遍历的方式绘制
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(26);
        canvas.drawText("Lollipop", 80, 30, mPaint);
        canvas.drawText("KitKat", 100, 600, mPaint);
        canvas.drawText("Marshmallow", 860, 160, mPaint);
        canvas.drawText("Froyo", 910, 320, mPaint);
        canvas.drawText("Gingerbread", 910, 370, mPaint);
        canvas.drawText("Ice Cream Sandwich", 910, 430, mPaint);
        canvas.drawText("Jelly Bean", 870, 580, mPaint);*/
    }

    public void setTextSize(float textSize){
        this.circleText = textSize;
    }

    public void setCircleWidthAndHight(int width ,int height ,float d){
        this.width=DensityUtil.dip2px(MyApplication.context,width);
        this.height = DensityUtil.dip2px(MyApplication.context,height);
        setValue(500,d);
        invalidate();
    }

    /**
     * 设置当前值
     *
     * @param value
     */
    public void setValue(float value,float s) {

        float end = s;
        startAnimator(10, end, 2000);
    }
    private void startAnimator(float start, float end, long animTime) {
        ValueAnimator     mAnimator = new ValueAnimator();

          mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.setDuration(animTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mParent = (float) animation.getAnimatedValue();
                mContent=((int)mParent)+"度";
              /*  if (BuildConfig.DEBUG) {
                    Log.d("", "onAnimationUpdate: percent = " + mPercent
                            + ";currentAngle = " + (mSweepAngle * mPercent)
                            + ";value = " + mValue);
                }*/
                invalidate();
            }
        });
        mAnimator.start();
    }

}

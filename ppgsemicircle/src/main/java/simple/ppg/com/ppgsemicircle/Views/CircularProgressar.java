package simple.ppg.com.ppgsemicircle.Views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import simple.ppg.com.ppgsemicircle.R;


/**
 * Created by ppg on 16/9/2 19:28
 * ppg_77@qq.com
 * <p/>
 */
public class CircularProgressar extends View {


    // 默认宽高值
    private int defaultSize;

    // 距离圆环的值
    private int arcDistance;

    // view宽度
    private int width;

    // view高度
    private int height;

    // 默认Padding值
    private final static int defaultPadding = 10;

    //  圆环起始角度
    private final static float mStartAngle = 90f;

    // 圆环结束角度
    private final static float mEndAngle = 360f;

    //外层圆环画笔
    private Paint mMiddleArcPaint;


    //文本画笔
    private Paint RightTextPaint;

    //左边文字画笔
    private Paint LeftTextPaint;


    //进度圆环画笔
    private Paint mArcProgressPaint;

    //半径
    private int radius;

    //外层矩形
    private RectF mMiddleRect;

    //内层矩形
    private RectF mInnerRect;

    //进度矩形
    private RectF mMiddleProgressRect;

    // 最小数字
    private int mMinNum = 0;

    // 最大数字
    private int mMaxNum = 40;

    // 当前进度
    private float mCurrentAngle = 0f;

    //总进度
    private float mTotalAngle = 360f;

    //信用等级
    private String sesameLevel = "";


    //图片
    private Bitmap bitmap;

    //当前点的实际位置
    private float[] pos;

    //当前点的tangent值
    private float[] tan;

    //矩阵
    private Matrix matrix;

    //小圆点画笔
    private Paint mBitmapPaint;

    private Context mContext;
    private int circularSize;
    private int lineSize;
    private int backgroundLineColor;
    private int frontLineColor;
    private int maxWidth;
    private int maxHeight;
    private int circularLeftTextSize;
    private int circularLeftTextColor;
    private int circularRightTextSize;
    private int circularRightTextColor;
    private boolean showBitmap;
    private int circularcentreBitmap;
    private String circularLeftText;
    private String circularRightText;


    public CircularProgressar(Context context) {
        this(context, null);
        mContext = context;
    }


    public CircularProgressar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }


    public CircularProgressar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircularProgressar);
        circularSize = typedArray.getDimensionPixelSize(R.styleable.CircularProgressar_circularSize, dp2px(100));
        lineSize = typedArray.getDimensionPixelSize(R.styleable.CircularProgressar_circularlineSize, dp2px(3));
        backgroundLineColor = typedArray.getColor(R.styleable.CircularProgressar_circularbackgroundLineColor, getResources().getColor(android.R.color.darker_gray));
        frontLineColor = typedArray.getColor(R.styleable.CircularProgressar_circularfrontLineColor, getResources().getColor(android.R.color.holo_orange_dark));
        circularLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.CircularProgressar_circularLeftTextSize, dp2px(16));
        circularLeftTextColor = typedArray.getColor(R.styleable.CircularProgressar_circularLeftTextColor, getResources().getColor(android.R.color.darker_gray));
        circularRightTextSize = typedArray.getDimensionPixelSize(R.styleable.CircularProgressar_circularRightTextSize, dp2px(16));
        circularRightTextColor = typedArray.getColor(R.styleable.CircularProgressar_circularRightTextColor, getResources().getColor(android.R.color.darker_gray));
        circularcentreBitmap = typedArray.getResourceId(R.styleable.CircularProgressar_circularcentreBitmap, R.mipmap.icon_1);
        showBitmap = typedArray.getBoolean(R.styleable.CircularProgressar_circularshowBitmap, true);
        circularLeftText = typedArray.getString(R.styleable.CircularProgressar_circularLeftText);
        circularRightText = typedArray.getString(R.styleable.CircularProgressar_circularRightText);
        if (TextUtils.isEmpty(circularLeftText)) {
            circularLeftText = "";
        }
        if (TextUtils.isEmpty(circularRightText)) {
            circularRightText = "";
        }

        //外层圆环画笔
        mMiddleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMiddleArcPaint.setStrokeWidth(lineSize);
        mMiddleArcPaint.setColor(backgroundLineColor);
        mMiddleArcPaint.setStyle(Paint.Style.STROKE);
        mMiddleArcPaint.setAlpha(90);


        //右边文字画笔
        RightTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RightTextPaint.setTextSize(circularRightTextSize);
        RightTextPaint.setColor(circularRightTextColor);
        RightTextPaint.setTextAlign(Paint.Align.LEFT);

        //左边文字画笔
        LeftTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        LeftTextPaint.setColor(circularLeftTextColor);
        LeftTextPaint.setTextSize(circularLeftTextSize);
        LeftTextPaint.setTextAlign(Paint.Align.RIGHT);


        //外层进度画笔
        mArcProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcProgressPaint.setStrokeWidth(lineSize);
        mArcProgressPaint.setColor(frontLineColor);
        mArcProgressPaint.setStyle(Paint.Style.STROKE);
        mArcProgressPaint.setStrokeCap(Paint.Cap.ROUND);

        mBitmapPaint = new Paint();
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setAntiAlias(true);

        //初始化小圆点图片
        bitmap = BitmapFactory.decodeResource(getResources(), circularcentreBitmap);
        pos = new float[2];
        tan = new float[2];
        matrix = new Matrix();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int minimumWidth = getSuggestedMinimumWidth();
        final int minimumHeight = getSuggestedMinimumHeight();
        int computedWidth = resolveMeasured(widthMeasureSpec, minimumWidth);
        int computedHeight = resolveMeasured(heightMeasureSpec, minimumHeight);

        setMeasuredDimension(computedWidth, computedHeight);
    }

    private int resolveMeasured(int measureSpec, int desired) {
        int result = 0;
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                result = desired;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize, desired);
                break;
            case MeasureSpec.EXACTLY:
            default:
                result = specSize;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxWidth = w;
        maxHeight = h;
        width = circularSize;
        height = circularSize;
        radius = width / 2;
//        LT.ee("maxWidth=" + maxWidth);
//        LT.ee("maxHeight=" + maxHeight);
//        LT.ee("radius=" + radius);
//        LT.ee("circularSize=" + circularSize);
        mMiddleRect = new RectF((maxWidth / 2) - radius, (maxHeight / 2) - radius, (maxWidth / 2)
                + radius, (maxHeight / 2) + radius);

        mMiddleProgressRect = new RectF((maxWidth / 2) - radius, (maxHeight / 2) - radius, (maxWidth / 2)
                + radius, (maxHeight / 2) + radius);
        //********************波纹圆*************************
        if (!mMaxRadiusSet) {
            mMaxRadius = Math.min(w, h) * mMaxRadiusRate / 2.0f;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawMiddleArc(canvas);

        drawRingProgress(canvas);

        drawLeftText(canvas);

        drawRightText(canvas);


        //*******************波纹圆*********************
        Iterator<Circle> iterator = mCircleList.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            float radius = circle.getCurrentRadius();
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
            } else {
                iterator.remove();
            }
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }
        if (showBitmap) {
            drawBitMap(canvas);
        }

    }

    private void drawBitMap(Canvas canvas) {
//        // 将画布坐标系移动到画布中央
//        canvas.translate(maxWidth / 2 - bitmap.getWidth() / 2, maxHeight / 2 - bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, maxWidth / 2 - bitmap.getWidth()
                / 2, maxHeight / 2 - bitmap.getHeight() / 2, new Paint());

    }

    private void drawRightText(Canvas canvas) {
        //正常绘制RightText
         canvas.drawText(circularRightText, (maxWidth / 2) + (radius*1.2f), (maxHeight / 2), RightTextPaint);

//        //公司自定义
//        String[] text1 = RightText.split("/");
//        canvas.drawText(text1[0] + "/", (maxWidth / 2) + (radius + dp2px(23)), (maxHeight / 2), RightTextPaint);
//        //获取/前的字符串
//        float LeftTextwidth = RightTextPaint.measureText(text1[0]);
//        canvas.drawText(text1[1], (maxWidth / 2) + (radius + dp2px(27) + LeftTextwidth), (maxHeight / 2), RightTextPaint);
    }

    private void drawLeftText(Canvas canvas) {
        //绘制LeftText
        canvas.drawText(circularLeftText, (maxWidth / 2) - (radius*1.2f), (maxHeight / 2), LeftTextPaint);
    }


    /**
     * 绘制外层圆环进度和小圆点
     */
    private void drawRingProgress(Canvas canvas) {

        Path path = new Path();
        path.addArc(mMiddleProgressRect, mStartAngle, mCurrentAngle);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        pathMeasure.getPosTan(pathMeasure.getLength() * 1, pos, tan);
        matrix.reset();
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
        canvas.drawPath(path, mArcProgressPaint);
        //起始角度不为0时候才进行绘制小圆点
        if (mCurrentAngle == 0) {
            return;
        }
//        canvas.drawBitmap(bitmap, matrix, mBitmapPaint);
//        mBitmapPaint.setColor(Color.WHITE);
//        canvas.drawCircle(pos[0], pos[1], 8, mBitmapPaint);
    }


    /**
     * 绘制外层圆环
     */
    private void drawMiddleArc(Canvas canvas) {
        canvas.drawArc(mMiddleRect, mStartAngle, mEndAngle, false, mMiddleArcPaint);
    }


    public void setSesameValues(int values, int totel) {
        if (values >= 0) {
            mMaxNum = values;
            //  mTotalAngle = 290f;
            sesameLevel = values + "/" + totel;
            mTotalAngle = ((float) values / (float) totel) * 360f;
            startAnim();
        }
    }


    public void startAnim() {

        ValueAnimator mAngleAnim = ValueAnimator.ofFloat(mCurrentAngle, mTotalAngle);
        mAngleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        mAngleAnim.setDuration(3000);
        mAngleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mCurrentAngle = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mAngleAnim.start();

        // mMinNum = 350;
        ValueAnimator mNumAnim = ValueAnimator.ofInt(mMinNum, mMaxNum);
        mNumAnim.setDuration(3000);
        mNumAnim.setInterpolator(new LinearInterpolator());
        mNumAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mMinNum = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mNumAnim.start();
    }


    /**
     * dp2px
     */
    public int dp2px(int values) {

        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    //*************************************波纹圆*****************************************

    private Interpolator mInterpolator = new LinearInterpolator();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mInitialRadius;   // 初始波纹半径
    private float mMaxRadius;   // 最大波纹半径
    private long mDuration = 2000; // 一个波纹从创建到消失的持续时间
    private int mSpeed = 500;   // 波纹的创建速度，每500ms创建一个
    private float mMaxRadiusRate =1.0f;
    private boolean mMaxRadiusSet;

    private boolean mIsRunning;
    private long mLastCreateTime;
    private List<Circle> mCircleList = new ArrayList<Circle>();

    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                newCircle();
                postDelayed(mCreateCircle, mSpeed);
            }
        }
    };

    public void setMaxRadiusRate(float maxRadiusRate) {
        mMaxRadiusRate = maxRadiusRate;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    /**
     * 开始
     */
    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    /**
     * 缓慢停止
     */
    public void stop() {
        mIsRunning = false;
    }

    /**
     * 立即停止
     */
    public void stopImmediately() {
        mIsRunning = false;
        mCircleList.clear();
        invalidate();
    }

    public void setInitialRadius(float radius) {
        mInitialRadius = radius;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public void setMaxRadius(float maxRadius) {
        mMaxRadius = maxRadius;
        mMaxRadiusSet = true;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    private void newCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle();
        mCircleList.add(circle);
        invalidate();
        mLastCreateTime = currentTime;
    }

    private class Circle {
        private long mCreateTime;

        Circle() {
            mCreateTime = System.currentTimeMillis();
        }

        int getAlpha() {
            float percent = (getCurrentRadius() - mInitialRadius) / (mMaxRadius - mInitialRadius);
            return (int) (255 - mInterpolator.getInterpolation(percent) * 255);
        }

        float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        if (mInterpolator == null) {
            mInterpolator = new LinearInterpolator();
        }
    }
}

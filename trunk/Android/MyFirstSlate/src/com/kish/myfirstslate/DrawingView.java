package com.kish.myfirstslate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	public int width;
	public int height;
	Context context;

	private Bitmap mBitmap;
	private Canvas mCanvas;			// Canvas
	private Paint mBitmapPaint;
	
	private Path circlePath;
	private Paint circlePaint;		// Pen tip (blue circle)
	private Path mPath;
	private Paint mPaint;			// Pen
	
	private int circleRadius = 6;
	

	public DrawingView(Context c,AttributeSet attrs) {
		super(c, attrs);
		context = c;
		
		mPath = new Path();
		circlePath = new Path();
		
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		
		circlePaint = new Paint();
		circlePaint.setAntiAlias(true);
		circlePaint.setColor(Color.BLUE);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setStrokeJoin(Paint.Join.MITER);
		circlePaint.setStrokeWidth(4f);
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

		canvas.drawPath(mPath, mPaint);

		canvas.drawPath(circlePath, circlePaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;

			circlePath.reset();
			circlePath.addCircle(mX, mY, circleRadius, Path.Direction.CW);
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		circlePath.reset();
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath.reset();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}
	
	// kishore: added methods
	public void setStrokeWidth(int val) {
		mPaint.setStrokeWidth(val);
	}
	
	public void setPenColor(int val) {
		mPaint.setColor(val);
	}
	
	public void clear(int color) {
//		mCanvas.drawColor(Color.WHITE);
//		mCanvas.drawColor(Color.rgb(0xff, 0xff, 0xff));
		mCanvas.drawColor(color);
		this.invalidate();
	}
	
	public void setCircleRadius(int circleRadius) {
		this.circleRadius = circleRadius;
	}
}
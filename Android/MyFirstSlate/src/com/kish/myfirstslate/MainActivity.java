package com.kish.myfirstslate;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	DrawingView board;
	SlateListener slateListener;
	
	// OnCreate - activity life cycle
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("kish", "hello--123-start--");
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		setContentView(new DrawingView(this));
		init();
	}

	// initializing: listeners, etc
	void init() {
		board = (DrawingView)findViewById(R.id.paintBoard);
		
		slateListener = new SlateListener();
		
		// Button Listeners
		Button buttonClear = (Button) findViewById(R.id.buttonClear);
		buttonClear.setOnClickListener(slateListener);
		Button buttonEraser= (Button) findViewById(R.id.buttonEraser);
		buttonEraser.setOnClickListener(slateListener);
		Button buttonPen = (Button) findViewById(R.id.buttonPen);
		buttonPen.setOnClickListener(slateListener);
		Button buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonReset.setOnClickListener(slateListener);
		
		// Pen Size
		SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setOnSeekBarChangeListener(slateListener);
	}
	
	// Listener Class
	class SlateListener implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
	
		// View.OnClickListener: methods
		@Override
		public void onClick(View view) {
			int viewId = view.getId();
			
			switch(viewId) {
			case R.id.buttonClear:
				board.clear();
				break;
			case R.id.buttonEraser:
				board.setPenColor(Color.WHITE);
				break;
			case R.id.buttonPen:
				board.setPenColor(Color.GREEN);
				break;
			case R.id.buttonReset:
				board.setPenColor(Color.GREEN);
				board.setStrokeWidth(12);
				board.setCircleRadius(12);
				board.clear();
				SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
				seekBar1.setProgress(12);
				break;
			}
		}
		
		
		// SeekBar.OnSeekBarChangeListener: methods
		@Override
		public void onStopTrackingTouch(SeekBar paramSeekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar paramSeekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar paramSeekBar, int progressVal,boolean paramBoolean) {
			((TextView)findViewById(R.id.textView1)).setText("" + progressVal);
			
			Log.d("kish", "drawing view11" + board);
			board.setStrokeWidth(progressVal);
			int circleRadius = progressVal / 2;
			board.setCircleRadius(circleRadius);
		}
	}
}
package com.kish.myfirstslate;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
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
	int currentButton; // bgbutton or fgbutton
	
	FragmentManager fm = getFragmentManager();
	
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
//		int color = ((ColorDrawable)findViewById(R.id.buttonbg).getBackground()).getColor();
//		board.clear(color);
//		color = ((ColorDrawable)findViewById(R.id.buttonfg).getBackground()).getColor();
//		board.setPenColor(color);
		
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
		Button buttonbg = (Button) findViewById(R.id.buttonbg);
		buttonbg.setOnClickListener(slateListener);
		Button buttonfg = (Button) findViewById(R.id.buttonfg);
		buttonfg.setOnClickListener(slateListener);
		
		// Pen Size
		SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setOnSeekBarChangeListener(slateListener);
	}
	
	// Listener Class
	class SlateListener implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
	
		int color;
		// View.OnClickListener: methods
		@Override
		public void onClick(View view) {
			int viewId = view.getId();
			ColorDialogFragment colorDlgFrag = new ColorDialogFragment(MainActivity.this);
			
			switch(viewId) {
			case R.id.buttonClear:
				color = ((ColorDrawable)findViewById(R.id.buttonbg).getBackground()).getColor();
				board.clear(color);
				color = ((ColorDrawable)findViewById(R.id.buttonfg).getBackground()).getColor();
				board.setPenColor(color);
				break;
			case R.id.buttonEraser:
				color = ((ColorDrawable)findViewById(R.id.buttonbg).getBackground()).getColor();
				board.setPenColor(color);
//				board.setPenColor(Color.WHITE);
				break;
			case R.id.buttonPen:
				color = ((ColorDrawable)findViewById(R.id.buttonfg).getBackground()).getColor();
				board.setPenColor(color);
//				board.setPenColor(Color.GREEN);
				break;
			case R.id.buttonReset:
				color = ((ColorDrawable)findViewById(R.id.buttonfg).getBackground()).getColor();
				board.setPenColor(color);
//				board.setPenColor(Color.GREEN);
				board.setStrokeWidth(12);
				board.setCircleRadius(12);
				color = ((ColorDrawable)findViewById(R.id.buttonbg).getBackground()).getColor();
				board.clear(color);
				SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
				seekBar1.setProgress(12);
				break;
			case R.id.buttonbg:
				colorDlgFrag.show(fm, "Colors...");
				currentButton = R.id.buttonbg; 
				Log.d("kish", "button bg");
				break;
			case R.id.buttonfg:
				colorDlgFrag.show(fm, "Colors...");
				currentButton = R.id.buttonfg; 
				Log.d("kish", "button fg");
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
package com.kish.myfirstslate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("kish", "hello--11-start--");
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
//		setContentView(new DrawingView(this));

		final DrawingView board = (DrawingView)findViewById(R.id.paintBoard);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View paramView) {
				board.clear();
//				startActivity(new Intent(MainActivity.this,MainActivity1.class));
			}
		});
		
		SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
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
				
				Log.d("kish", "drawing view" + board);
				board.setStrokeWidth(progressVal);
			}
		});
	}
}
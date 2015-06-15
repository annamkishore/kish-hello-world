package com.kish.myfirstslate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
		ImageButton buttonEraser= (ImageButton) findViewById(R.id.imageButtonEraser);
		buttonEraser.setOnClickListener(slateListener);
		ImageButton buttonPen = (ImageButton) findViewById(R.id.imageButtonPen);
		buttonPen.setOnClickListener(slateListener);
		Button buttonbg = (Button) findViewById(R.id.buttonbg);
		buttonbg.setOnClickListener(slateListener);
		Button buttonfg = (Button) findViewById(R.id.buttonfg);
		buttonfg.setOnClickListener(slateListener);

		ImageButton buttonSave = (ImageButton) findViewById(R.id.imageButtonSave);
		buttonSave.setOnClickListener(slateListener);		
		ImageButton buttonShare = (ImageButton) findViewById(R.id.imageButtonShare);
		buttonShare.setOnClickListener(slateListener);
		
		
		// Pen Size
		SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setOnSeekBarChangeListener(slateListener);
	}
	
    private static final int SELECT_PHOTO = 100;
    private String filePath;

    private void saveScreen(View content, boolean forSharing) {
        try {
            content.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(content.getDrawingCache());
            content.setDrawingCacheEnabled(false);
            
            File file = getOutputFilePath( forSharing );
            FileOutputStream ostream = new FileOutputStream(file);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, SELECT_PHOTO, stream);
            ostream.write(stream.toByteArray());
            ostream.close();
            
            if( ! forSharing ) {
            	Toast.makeText(this, "img saved at " + file.getAbsolutePath(), 0).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Please insert SD card.", 0).show();
            e.printStackTrace();
        }
    }
	
    private File getOutputFilePath(boolean forSharing) {
        File mediaStorageDir = 
        		forSharing ? new File(Environment.getExternalStorageDirectory().toString(), "/Android/data/com.kish.myfirstslate/cache/") :
        					 new File(Environment.getExternalStoragePublicDirectory("MyFirstSlate"), "");

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            return null;
        }
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + new StringBuilder(String.valueOf(System.currentTimeMillis())).toString() + ".png");
        this.filePath = "file://" + mediaFile.getAbsolutePath();
        return mediaFile;
    }
    
    // Back Button
    public void onBackPressed() {
    	showAlertDialog();
    };
    
    AlertDialog alert;
    private void showAlertDialog() {
        Builder builder = new Builder(this);
        builder.setMessage("Exit ?   Please rate if liked :)")
        	   .setCancelable(false)
        	   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							            public void onClick(DialogInterface dialog, int which) {
							                MainActivity.this.finish();
							            }}
        			   			 )
        	   .setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
							            public void onClick(DialogInterface dialog, int which) {
							                try {
							                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.kish.myfirstslate")));
							                } catch (Exception e) {
							                    Toast.makeText(MainActivity.this, "Unable to find Google Play.", 0).show();
							                }}}
							        	   )
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
							            public void onClick(DialogInterface dialog, int which) {
							                MainActivity.this.alert.dismiss();
							            }});
        this.alert = builder.create();
        this.alert.show();
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
				board.clear( getColor(R.id.buttonbg, MainActivity.this) );
				board.setPenColor( getColor(R.id.buttonfg, MainActivity.this) );
				break;
			case R.id.imageButtonEraser:
				board.setPenColor(getColor(R.id.buttonbg, MainActivity.this));
				break;
			case R.id.imageButtonPen:
				board.setPenColor(getColor(R.id.buttonfg, MainActivity.this));
				break;
			case R.id.imageButtonSave:
				saveScreen(board, false);
				break;
			case R.id.imageButtonShare:
                Intent intent = new Intent("android.intent.action.SEND");
                saveScreen(board, true);
                Uri screenshotUri = Uri.parse(MainActivity.this.filePath);
                intent.setType("image/png");
                intent.putExtra("android.intent.extra.STREAM", screenshotUri);
                intent.putExtra("android.intent.extra.SUBJECT", "My First Slate Drawing");
                intent.putExtra("android.intent.extra.TEXT", "Share Via MyFirstSlate");
                startActivity(Intent.createChooser(intent, "Share Image"));
				break;
//			case R.id.buttonReset:
//				board.setPenColor(getColor(R.id.buttonfg, MainActivity.this));
//				board.setStrokeWidth(12);
//				board.setCircleRadius(12);
//				board.clear(getColor(R.id.buttonbg, MainActivity.this));
//				SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
//				seekBar1.setProgress(12);
//				break;
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
		
		public int getColor(int viewId, Activity activity) {
			int color = ((ColorDrawable)activity.findViewById(viewId).getBackground()).getColor();
			return color;
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
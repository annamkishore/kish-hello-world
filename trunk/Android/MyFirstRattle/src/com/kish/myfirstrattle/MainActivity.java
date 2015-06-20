package com.kish.myfirstrattle;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.kish.myfirstrattle.ShakeListener.OnShakeListener;

public class MainActivity extends Activity {

	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// no title, full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		// Shake Listener
		ShakeListener shakelistener = new ShakeListener(this);
		shakelistener.setOnShakeListener(new MyShakeListener());

		// Image Adapter
		ImageAdapter adapter = new ImageAdapter(this);
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class MyShakeListener implements OnShakeListener {
		@Override
		public void onShake() {
			final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibe.vibrate(100);
//			Toast.makeText(MainActivity.this, "shaked-----1111", Toast.LENGTH_LONG).show();
//			System.out.println("SHAKE LISTENER CALLED");

			int tempSound = 0;
			switch( viewPager.getCurrentItem() ) {
			case 0: tempSound = R.raw.one; break;
			case 1: tempSound = R.raw.two; break;
			case 2: tempSound = R.raw.three; break;
			}
			System.out.println("Sound played: " + Integer.toHexString(tempSound));
			MediaPlayer player = MediaPlayer.create(MainActivity.this, tempSound);
			player.start();
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
}
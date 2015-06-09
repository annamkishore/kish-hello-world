package com.kish.myfirstslate;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ColorDialogFragment extends DialogFragment{

	ColorListener colorListener;
	
	MainActivity mainActivity;
	
	View rootView;
	
	public ColorDialogFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_test, container, false);
		Dialog d1 = getDialog();
		d1.setTitle("Color Chooser");
		
		colorListener = new ColorListener();
		
		// dialog buttons
		((Button) rootView.findViewById(R.id.Button01)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button02)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button03)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button04)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button05)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button06)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button07)).setOnClickListener(colorListener);
		((Button) rootView.findViewById(R.id.Button08)).setOnClickListener(colorListener);
		
		return rootView;
	}
	
	// Listener Class
	class ColorListener implements View.OnClickListener {
	
		Button button;
		int color;
		
		@Override
		public void onClick(View view) {
			int viewId = view.getId();
			
			// selected Color
			color = ((ColorDrawable)rootView.findViewById(viewId).getBackground()).getColor();
			
			if( mainActivity.currentButton == R.id.buttonfg ) {
				mainActivity.board.setPenColor(color);
			}else if( mainActivity.currentButton == R.id.buttonbg ) {
				mainActivity.board.clear(color);
			}
			
			button = (Button) mainActivity.findViewById(mainActivity.currentButton);
			button.setBackgroundColor(color);
			
//			switch(viewId) {
//			case R.id.Button1:
//				button = (Button) mainActivity.findViewById(mainActivity.currentButton);
//				button.setBackgroundColor(Color.BLUE);
//				
//				if( mainActivity.currentButton == R.id.buttonfg ) {
//					mainActivity.board.setPenColor(Color.BLUE);
//				}
//				Log.d("kish", "button Blue");
//				break;
//			case R.id.buttonRed:
//				button = (Button) mainActivity.findViewById(mainActivity.currentButton);
//				button.setBackgroundColor(Color.RED);
//				
//				if( mainActivity.currentButton == R.id.buttonfg ) {
//					mainActivity.board.setPenColor(Color.RED);
//				}
//				Log.d("kish", "button Red");
//				break;
//			}
		}
	}

}
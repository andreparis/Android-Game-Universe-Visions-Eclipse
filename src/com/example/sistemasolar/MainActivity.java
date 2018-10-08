package com.example.sistemasolar;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private GLSurfaceView mGlSurfaceView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		this.mGlSurfaceView = new MyGLSurfaceView(this);
		setContentView(this.mGlSurfaceView);
		this.mGlSurfaceView.requestFocus();
		this.mGlSurfaceView.setFocusableInTouchMode(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.mGlSurfaceView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.mGlSurfaceView.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.god:
	        	this.mGlSurfaceView = new MyGLSurfaceView(this);
	    		setContentView(this.mGlSurfaceView);
	            return true;
	        case R.id.earth:
	        	this.mGlSurfaceView = new EarthGlSurfaceView(this);
	    		setContentView(this.mGlSurfaceView);
	            return true;
	        case R.id.space:
	        	this.mGlSurfaceView = new spaceship(this);
	    		setContentView(this.mGlSurfaceView);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}

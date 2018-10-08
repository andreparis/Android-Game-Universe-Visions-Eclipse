package com.example.sistemasolar;


import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class spaceship extends GLSurfaceView {

	final static int NONE = 0;
	private float Old_x = 0f;
	private float Old_y = 0f;
	private boolean STOP = true;
	float rho = 40f;
	
	public static int LONG_PRESS_TIME = 500;

	private Handler mHandler; 

	private glRenderer mRenderer;
	// possible touch states
	final static int ZOOM = 2;
	final static int DRAG = 1;
	final static int LONGPRESS = 3;
	int touchState = NONE;
	public spaceship(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRenderer = new glRenderer(context);


		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		mRenderer.aux1 = true;
		mRenderer.vx = -2.3f;
		mRenderer.vy = 0.0f;
		mRenderer.vz = 10.0f;
		mRenderer.x = -2.3f;
		mRenderer.y = 0.0f;
		mRenderer.z = 11.0f;
	}

	@SuppressWarnings("deprecation")
	final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return true;
		}


	});

	Runnable mAction = new Runnable() {
		@Override public void run() {

			mRenderer.x2 =0.4f;
			mRenderer.y2 =0.4f;
			mRenderer.z2 =0.4f;
			mRenderer.rotation += 0.8f;
			mHandler.postDelayed(this, 500);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {


		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			mHandler = new Handler();
			mHandler.postDelayed(mAction, LONG_PRESS_TIME);
			this.Old_x = event.getX();
			this.Old_y = event.getY();
			touchState = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			touchState = ZOOM;
			break;

		case MotionEvent.ACTION_UP:
			//mHandler.removeCallbacks(mAction);
			touchState = NONE;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			touchState = NONE;
			break;
		case MotionEvent.ACTION_MOVE:  

			if(touchState == DRAG){
				System.out.println("touch="+touchState);
				//if(gestureDetector.onTouchEvent(event)){
					mHandler.removeCallbacks(mAction);
					if((event.getX()==Old_x) && (event.getY()==Old_y)){
					}else{
						if((event.getX() == Old_x) && (event.getY() > Old_y) ){
							mRenderer.y3 = 0.4f;
							mRenderer.vy -= 0.1f;
							mRenderer.y -= 0.1f;
							System.out.println("TESTE"+"2");
							mHandler.postDelayed(mAction, LONG_PRESS_TIME);
						}else{
							if((event.getX() == Old_x) && (event.getY() < Old_y)){
								mRenderer.y3 = -0.4f;
								mRenderer.vy += 0.1f;
								mRenderer.y += 0.1f;
								System.out.println("TESTE"+"4");
								mHandler.postDelayed(mAction, LONG_PRESS_TIME);
							}else{
								if((event.getX() > Old_x) && (event.getY() == Old_y)){
									//mRenderer.x3 =0.4f;
									mRenderer.z3 =0.4f;
									mRenderer.z -=0.4f;
									System.out.println("TESTE"+"5");
									mHandler.postDelayed(mAction, LONG_PRESS_TIME);
								}else if((event.getX() < Old_x) && (event.getY() == Old_y)){
									//mRenderer.x3 = -0.4f;
									mRenderer.z3 = -0.4f;
									mRenderer.z +=0.4f;
									System.out.println("TESTE"+"6");
									mHandler.postDelayed(mAction, LONG_PRESS_TIME);
								}
							}
						}
					}
				//}
				System.out.println("COORD ="+event.getX() + " " + event.getY() );
				System.out.println("X,Y,Z ="+mRenderer.x + " " + mRenderer.y + " " + mRenderer.z);
			}
			break;
		}
		return true;
	}


}

package com.example.sistemasolar;

import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.opengl.GLSurfaceView;
import android.util.FloatMath;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class EarthGlSurfaceView extends GLSurfaceView  {
	
	private glRenderer mRenderer;
	final static int NONE = 0;
	private float oldDist = 1f;
	private float Old_x = 0f;
	private float Old_y = 0f;
	final static int ZOOM = 2;
	final static int DRAG = 1;
	int touchState = NONE;
	GL10 gl;
	float theta = 0;
	
	Camera camera;

	public EarthGlSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRenderer = new glRenderer(context);
		setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        mRenderer.x = -2.3f;
        mRenderer.y = 0;
        mRenderer.z = 15;
        mRenderer.x3 = 2.3f;
		mRenderer.y3 = 0;
		mRenderer.z3 = 10;
		mRenderer.translation +=0.2f;
		mRenderer.rotation_1+=6;
		mRenderer.aux = false;
		mRenderer.speed=20;
	}

	@SuppressLint("FloatMath")
	/*private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}*/

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			touchState = ZOOM;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			touchState = NONE;
			break;
		case MotionEvent.ACTION_MOVE:  
			if(touchState == ZOOM){
				float newDist = spacing(event);
				if (newDist > oldDist) {
					mRenderer.vz+=0.2f;
					mRenderer.vx+=0.2f;
					mRenderer.vy+=0.2f;
				}else mRenderer.vz-=0.2f;
			}
			break;
		}*/
		return true;
	}
}

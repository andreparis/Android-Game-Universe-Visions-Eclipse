package com.example.sistemasolar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyGLSurfaceView extends GLSurfaceView {
	final static int NONE = 0;
	private float oldDist = 1f;
	private float Old_x = 0f;
	private float Old_y = 0f;
	private float Old_z = 0f;
	float rho = 40f;
	
	private glRenderer mRenderer;
	// possible touch states
	final static int ZOOM = 2;
	final static int DRAG = 1;
	int touchState = NONE;
	private float theta = 0.0f;
	private float phi = 0.0f;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF start = new PointF();
	PointF mid = new PointF();
	
	Camera camera;

	public MyGLSurfaceView(Context context) {
		super(context);
		
		// TODO Auto-generated constructor stub
		mRenderer = new glRenderer(context);
		
	
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
       camera = new Camera();
      }
	
	@SuppressLint("FloatMath")
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}
	


	@SuppressLint("FloatMath")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		

		System.out.println("PHI ==" + phi);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			this.Old_x = event.getX();
			this.Old_y = event.getY();
			this.Old_z = mRenderer.z;
			savedMatrix.set(matrix);
			touchState = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			touchState = ZOOM;
			break;

		case MotionEvent.ACTION_UP:
			touchState = NONE;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			touchState = NONE;
			break;
		case MotionEvent.ACTION_MOVE:  
			if(touchState == ZOOM){
				float newDist = spacing(event);
				if (newDist > oldDist) {
					mRenderer.x +=0.4f;
					mRenderer.y +=0.4f;
					mRenderer.z +=0.4f;
					mRenderer.rotation = 0;
				}else {
					mRenderer.x -=0.4f;
					mRenderer.y -=0.4f;
					mRenderer.z -=0.4f;
					mRenderer.rotation = 0;
				}
			}else{
				if(touchState == DRAG){
					if( (event.getX() > Old_x) && (event.getY() < Old_y) ){ //1
						mRenderer.x2 =0.4f;
						mRenderer.y2 =0.4f;
						mRenderer.z2 =0.4f;
						mRenderer.rotation += 0.3f;
						mRenderer.rotation2 += -0.3f;
						
					}else{
						if( (event.getX() > Old_x) && (event.getY() > Old_y) ){ //2
							mRenderer.x2 =0.4f;
							mRenderer.y2 =0.4f;
							mRenderer.z2 =0.4f;
							mRenderer.rotation -= -0.3f;
							mRenderer.rotation2 -= -0.3f;
						}else{
							if( (event.getX() < Old_x) && (event.getY() < Old_y) ){ //3
								mRenderer.x2 = 0.4f;
								mRenderer.y2 = 0.4f;
								mRenderer.z2 = 0.4f;
								mRenderer.rotation -= 0.3f;
								mRenderer.rotation2 -= 0.3f;
								
							}else if( (event.getX() < Old_x) && (event.getY() > Old_y) ){ //4
								mRenderer.x2 =-0.4f;
								mRenderer.y2 =-0.4f;
								mRenderer.z2 =-0.4f;
								mRenderer.rotation -= 0.3f;
								mRenderer.rotation2 -= -0.3f;
							}
						}
					}
				}
				System.out.println("RHO ="+rho );
				System.out.println("COORD ="+event.getX() + " " + event.getY() );
				System.out.println("X,Y,Z ="+mRenderer.x + " " + mRenderer.y + " " + mRenderer.z);
			}
			break;
		}
		return true;
	}


}

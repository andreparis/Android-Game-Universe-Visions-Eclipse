package com.example.sistemasolar;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class glRenderer implements GLSurfaceView.Renderer {
	
	Context context;
	public final static int SS_SUNLIGHT2 = GL10.GL_LIGHT2;
	public final static int SS_SUNLIGHT = GL10.GL_LIGHT1;
	public boolean autoRotate=true;
	/* Planets */
	private Sphere earth;
	private Sphere mercury;
	private Sphere venus;
	private Sphere mars;
	private Sphere jupter;
	private Sphere saturn;
	private Sphere uranus;
	private Sphere neptune;
	private Sphere sun;
	private Sphere moon;
	private Sphere spaceship;
	
	float earthTranslation;
	private float moonTranslation;
	private float mercuryTranslation;
	private float venusTranslation;
	private float marsTranslation;
	private float jupterTranslation;
	private float saturnTranslation;
	private float uranusTranslation;
	private float neptuneTranslation;
	
	private float earthRotation = 0;
	private float mercuryRotation = 0;
	private float venusRotation = 0;
	private float marsRotation = 0;
	private float jupterRotation = 0;
	private float saturnRotation = 0;
	private float uranusRotation = 0;
	private float neptuneRotation = 0;
	
	float rotation = 0;
	float rotation2 = 0;
	float rotation_1 = 0;
	float translation = 0;
	int x1 = 0,y1 = 0;
	float x2,y2,z2;
	float x3,y3,z3;
	float x4,y4,z4;
	float xSS = -2.3f,ySS=0,zSS=10;
	float rotationSSx;
	float rotationSSy;
	boolean aux = true;
	float speed=1;
	float zoom=1;
	boolean aux1 = false;
	
	static Random r = new Random();

	float eyeX = 0.0f; float eyeY = 0.0f; float eyeZ = 1.0f;
	float x = 10,y = 10,z = 40,vx = 0,vy = 0,vz = 0;
	float angle = 0;
	float theta = 0;
	
	/** Object distance on the screen. move it back a bit so we can see it! */
	private static final float OBJECT_DISTANCE = -15.0f;

	/** Tilt the spheres a little. */
	private static final int AXIAL_TILT_DEGRESS = 30;
	/** Perspective setup, field of view component. */
	private float FOVY = 45.0f;

	/** Perspective setup, near component. */
	private static final float Z_NEAR = 0.1f;

	/** Perspective setup, far component. */
	private float Z_FAR = 100.0f;
	
	void angleToCartesian(double radius, double a, double b) {
	    x = (float) (radius * Math.sin(a) * Math.cos(b));
	    y = (float) (radius * Math.cos(a));
	    z = (float) (radius * Math.sin(a) * Math.sin(b));
	}
	
	public glRenderer(Context context) {
		this.context = context;

		this.earth = new Sphere(3,0.25f);         // (NEW)
		this.moon = new Sphere(3,0.07f);
		this.mercury = new Sphere(3,0.1f);
		this.venus = new Sphere(3,0.25f);
		this.mars = new Sphere(3,0.24f);
		this.jupter = new Sphere(3,0.75f);
		this.saturn = new Sphere(3,0.55f);
		this.uranus = new Sphere(3,0.35f);
		this.neptune = new Sphere(3,0.3f);
		this.sun = new Sphere(3,4.83f);
		this.spaceship = new Sphere(3,0.02f);
		
		this.earthTranslation = 0;
		this.moonTranslation = 0;
		this.mercuryTranslation =0;
		this.venusTranslation = 0;
		this.marsTranslation = 0;
		this.jupterTranslation = 0;
		this.saturnTranslation = 0;
		this.uranusTranslation = 0;
		this.neptuneTranslation = 0;
		
	}
	
	
	public void enable_earth(boolean aux, GL10 gl){
		if(aux == true){ this.earth.draw(gl);}
		else aux=false;
	}
	public void InitEarthMode(boolean aux,GL10 gl){
		
		if(aux == false){
			x = -2.3f;
			y = 0;
			z = 30;
			theta += 0.1;
			translation +=0.2f;
			rotation_1+=6;
			gl.glPushMatrix();
			gl.glRotatef(this.moonTranslation+=(13/speed), 0, 1, 0);
			gl.glTranslatef(-2.3f, 0.0f, 2);
			this.moon.draw(gl);
			gl.glPopMatrix();
		}else aux = true;
	}
	public void InitGodMode(boolean aux,GL10 gl){
		if(aux==true){
			gl.glRotatef(rotation2, 1, 0, 0);
			gl.glRotatef(rotation, 0,1 ,0);
			gl.glTranslatef(x2, y2, z2);
		}
	}
	
	public void InitSpaceShipMode(boolean aux, GL10 gl){
		
		if(aux==true){
			gl.glRotatef(rotation, 0,1 ,0);
			gl.glTranslatef(x4, y4, z4);
		}
	}
	public void InitGlLookAt(boolean aux,GL10 gl){
		if(aux==false){ 
			gl.glRotatef(translation, 0, 1, 0);
			GLU.gluLookAt(gl,x,y,z, vx,vy,vz, 0,1,0);
		}

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		System.out.println("teste");
		gl.glPushMatrix();
	    gl.glTranslatef(-2.3f, 0, 10f);
		this.spaceship.draw(gl);
		 gl.glPopMatrix();
		 
		 gl.glPushMatrix();
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glRotatef(translation, 0, 1, 0);
		GLU.gluLookAt(gl,x,y,z, vx,vy,vz, 0,1,0);
		//gl.glPushMatrix();
		gl.glTranslatef(x3, y3, z3);
		gl.glRotatef(rotation_1/speed, 0,1 ,0);
		InitGodMode(aux,gl);
		InitEarthMode(aux,gl);
		InitSpaceShipMode(aux1,gl);
		
		
		
	    System.out.println(angle);System.out.println(x+" "+y+" "+z);
		
		 /*Sun*/
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, 0);
		this.sun.draw(gl);
		
		
		/*Earth*/
	    gl.glPushMatrix();
	    gl.glRotatef(this.earthTranslation+=(1/speed), 0, 1, 0);
	    gl.glTranslatef(-2.3f, 0.0f, 15);
	    gl.glRotatef(this.earthRotation+= (30/speed), 0, 1, 0);
	    enable_earth(aux,gl);
	    /*Moon*/
	    gl.glPushMatrix();
	    gl.glRotatef(this.moonTranslation+=(13/speed), 0, 1, 0);
	    gl.glTranslatef(0.6f, 0.0f, 0);
	    this.moon.draw(gl);
	    gl.glPopMatrix();
	    gl.glPopMatrix();
	    
	    /*Mercury*/
	    gl.glPushMatrix();
	    gl.glRotatef(mercuryTranslation+= (4/speed), 0, 1, 0);
	    gl.glTranslatef(-3.5f, 0.0f, 7.5f);
	    gl.glRotatef(mercuryRotation+= (50/speed), 0, 1, 0);
	    this.mercury.draw(gl);
	    gl.glPopMatrix();
	     
	    
	    /*Venus*/
	     gl.glPushMatrix();
	     gl.glRotatef(venusTranslation+= (1.6f/speed), 0, 1, 0);
	     gl.glTranslatef(-3.0f, 0.0f, -12);
	     gl.glRotatef(venusRotation+= (40/speed), 0, 1, 0);
	     this.venus.draw(gl);
	     gl.glPopMatrix();	   
	   
	    
	    /*Mars*/
	    gl.glPushMatrix();
	    gl.glRotatef(marsTranslation+= (0.54f/speed), 0, 1, 0);
	    gl.glTranslatef(-1.5f, 0.0f,19.5f);
	    gl.glRotatef(marsRotation+= (25/speed), 0, 1, 0);
	    this.mars.draw(gl);
	    gl.glPopMatrix();
	    
	    
	    /*Jupter*/
	    gl.glPushMatrix();
	    gl.glRotatef(jupterTranslation+= (0.05f/speed), 0, 1, 0);
	    gl.glTranslatef(-0.5f, 0.0f, -22.5f);
	    gl.glRotatef(jupterRotation+= (5/speed), 0, 1, 0);
	    this.jupter.draw(gl);
	    gl.glPopMatrix();
	    
	    
	    /*Saturn*/
	    gl.glPushMatrix();
	    gl.glRotatef(saturnTranslation+= (0.004f/speed), 0, 1, 0);
	    gl.glTranslatef(1.0f, 0.0f, 24);
	    gl.glRotatef(saturnRotation+= (0.2f/speed), 0, 1, 0);
	    this.saturn.draw(gl);
	    gl.glPopMatrix();
	    
	    
	    /*Uranus*/
	    gl.glPushMatrix();
	    gl.glRotatef(uranusTranslation+= (0.002f/speed), 0, 1, 0);
	    gl.glTranslatef(2.0f, 0.0f,-25.5f);
	    gl.glRotatef(uranusRotation+= (0.01f/speed), 0, 1, 0);
	    this.uranus.draw(gl);
	    gl.glPopMatrix();
	    
	    /*Neptune*/
	    gl.glPushMatrix();
	    gl.glRotatef(neptuneTranslation+= (0.001f/speed), 0, 1, 0);
	    gl.glTranslatef(3.0f, 0.0f, 27);
	    gl.glRotatef(neptuneRotation+= (0.01f/speed), 0, 1, 0);
	    this.neptune.draw(gl);
	    gl.glPopMatrix();
	    
	    
	    gl.glPopMatrix();
	    gl.glPopMatrix();
	    
	    
	    
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		final float aspectRatio = (float) width / (float) (height == 0 ? 1 : height);
		
	    gl.glViewport(x1,y1, width, height);
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
	    gl.glLoadIdentity();
	    
	    GLU.gluPerspective(gl, FOVY, aspectRatio, Z_NEAR, Z_FAR);
	    
	    
	 }

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		
		/*Texture*/
		this.earth.loadGLTexture(gl, this.context, R.drawable.earth);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.moon.loadGLTexture(gl, this.context, R.drawable.moon);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.mercury.loadGLTexture(gl, this.context, R.drawable.mercurio);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.venus.loadGLTexture(gl, this.context, R.drawable.mercurio);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.mars.loadGLTexture(gl, this.context, R.drawable.mars);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.jupter.loadGLTexture(gl, this.context, R.drawable.jupter);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.saturn.loadGLTexture(gl, this.context, R.drawable.saturn);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.uranus.loadGLTexture(gl, this.context, R.drawable.uranus);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.neptune.loadGLTexture(gl, this.context, R.drawable.neptune);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.sun.loadGLTexture(gl, this.context, R.drawable.sun);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		this.spaceship.loadGLTexture(gl, this.context, R.drawable.spaceship);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);  // Set color's clear-value to black
		gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
		gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
		gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
		gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
		gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

	}

}

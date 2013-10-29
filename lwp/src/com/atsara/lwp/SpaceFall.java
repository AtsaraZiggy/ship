package com.atsara.lwp;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceFall implements ApplicationListener
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private int FPS = 50; //maximum FPS for limiter
	private long lastTime;
	private long deltaTime;
	private SpaceState State;
	
	@Override
	public void create()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, w, h);
		batch = new SpriteBatch();
		
		lastTime = System.currentTimeMillis();
		
		State = new SpaceState();
	}

	@Override
	public void dispose()
	{
		State.dispose();
		batch.dispose();
	}

	@Override
	public void render()
	{
		long currentTime = System.currentTimeMillis();
		
		if((currentTime - lastTime) >= (((long) 800) / ((long) FPS)))	//frames limiter
		{
			long deltaMS = currentTime - lastTime;	//time delta from last frame in MS
			lastTime = currentTime;
			
			if(deltaMS > 0)
				State.update((int) deltaMS);
			
			
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	//clean the screen
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			
			State.render(batch);
			
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height)
	{
		State.resize(width, height);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera.setToOrtho(true, w, h);
	}

	@Override
	public void pause()
	{
		long currentTime = System.currentTimeMillis();
		deltaTime = currentTime -lastTime;
	}

	@Override
	public void resume()
	{
		long currentTime = System.currentTimeMillis();
		
		if((currentTime - lastTime) > (((long) 800) / ((long) FPS)))
			lastTime = currentTime - deltaTime;
	}
}

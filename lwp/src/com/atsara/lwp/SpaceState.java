package com.atsara.lwp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceState
{
	protected SpaceField field;
	
	public SpaceState()
	{
		field =  new SpaceField();
	}
	
	public void render(SpriteBatch batch)
	{
		field.render(batch);
	}
	
	public void update(int deltaMS)
	{
		field.update(deltaMS);
	}
	
	public void dispose()
	{
		field.dispose();
	}
	
	public void resize(int width, int height)
	{
		field.resize(width, height);
	}
}

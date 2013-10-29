package com.atsara.lwp;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceField
{
	private List<SpaceEffect> effects = new LinkedList<SpaceEffect>();
	private int width;
    private int height;
    private Texture shipImage;
    
    public SpaceField()
    {
    	shipImage = new Texture(Gdx.files.internal("data/spaceship.png"));
    	width = Gdx.graphics.getWidth();
    	height = Gdx.graphics.getHeight();
    	int effectNumber = (width * height) / 2700;
    	
    	for (int i = 0; i < effectNumber; i++) 
    	{
    		effects.add(new SpaceEffect((float) (Math.random() * width), (float) (Math.random() * height), shipImage));
        }
    }
    
    public void render(SpriteBatch batch)
    {

        for (SpaceEffect f : effects)
            f.render(batch);
    }
    
    public void update(int deltaMS)
    {
        for (SpaceEffect f : effects)
        {
            f.update(deltaMS);
            
            if(f.getY() > height)
            	f.reset((float) (Math.random() * width));
        }
    }
    
    public void dispose()
    {
    	shipImage.dispose();
    }
    
    public void resize(int width, int height)
    {
    	
    }
}

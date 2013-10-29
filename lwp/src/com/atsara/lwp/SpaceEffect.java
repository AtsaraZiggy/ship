package com.atsara.lwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpaceEffect
{
	protected float angle;
	protected float angleStep;
	protected float lateralPower;
	protected float fallSpeed;
//	protected Texture shipImage;
	protected Sprite effectSprite;
	protected float rotationSpeed;
	protected float scale = 1;
	protected int rotationSense;
	protected Rectangle effect;
	
	public SpaceEffect(float x, float y, Texture shipImage)
	{
		effect = new Rectangle();
		//shipImage = new Texture(Gdx.files.internal("data/spaceship.png"));
		effect.height = shipImage.getHeight();
		effect.width = shipImage.getWidth();
		effectSprite = new Sprite(shipImage);
		reset(x);
		effect.y = y - effect.height / 2;
	}
	
	public void render(SpriteBatch batch)
	{
		effectSprite.draw(batch);
	}
	
	public void update(int deltaMS)
	{
		effect.y += fallSpeed * deltaMS / 1000 * (0.9 + Math.random() / 5);
    	angle +=  rotationSense * angleStep * deltaMS / 100 * (0.9 + Math.random() / 5);
    	if(angle > Math.PI)
    		angle -= 2 * Math.PI;
    	if(angle < -Math.PI)
    		angle += 2 * Math.PI;
    	effectSprite.rotate(rotationSense * rotationSpeed * deltaMS);
    	effectSprite.setPosition(getX(), getY());
	}
	
	public float getX() 
	{
        return (float) (effect.x + (seno(angle) * lateralPower));
    }

    public float getY() 
    {
        return effect.y;
    }
    
    public void reset(float x) 
    {
		effect.y = -effect.height;
		effect.x = x - effect.width / 2;
		
		angle = (float) (Math.random() * Math.PI);
		angleStep = (float) ((Math.random() * Math.PI / 16) + Math.PI / 32);
		lateralPower = (float) (Math.random() * 13 + 0.7);
		
		scale = (float) (Math.random() * 1.2 + 1.25);
		effectSprite.setScale(scale);
		
		fallSpeed = (float) (Math.random() * 25 + 10);
		
		rotationSpeed = (float) (Math.random() * 360 / 4500 + 0.001);
		if(Math.random() >= 0.5)
			rotationSense = 1;
		else
			rotationSense = -1;
	}
    
    public void dispose()
    {
//    	shipImage.dispose();
    }
    
    private float seno(float x)
    {
    	double B = (4 / Math.PI);
    	double C = (-4 / (Math.PI * Math.PI));
    	double y = B * x + C * x * Math.abs(x);

        return(float) (0.0225 * (y * Math.abs(y) - y) + y);
    }
}

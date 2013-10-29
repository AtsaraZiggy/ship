package com.atsara.lwp;

import android.view.SurfaceHolder;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;

public class LiveWallpaper extends AndroidLiveWallpaperService
{
	
	@Override
	public AndroidApplicationConfiguration createConfig()
	{
    	return  new AndroidApplicationConfiguration();

	}

	@Override
	public void offsetChange (ApplicationListener listener, float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset)
	{

	}

	@Override
	public ApplicationListener createListener(boolean arg0)
	{
		return new SpaceFall();
	}
	
	@Override
	public Engine onCreateEngine()
	{
		AndroidWallpaperEngine engine = new AndroidWallpaperEngine()
		{
			@Override
			public void onSurfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height)
			{
				super.onSurfaceChanged(holder, format, width, height);
			}
		};
		
		engine.setTouchEventsEnabled(true);
		return engine;
	}
	
}

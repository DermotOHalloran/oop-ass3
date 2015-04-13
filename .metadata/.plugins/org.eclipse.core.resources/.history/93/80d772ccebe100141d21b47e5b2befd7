package ie.dit.reeageshark;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private GamePanel gamePanel;
	private boolean active;
	
	float dt;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public MainThread(SurfaceHolder holder, GamePanel gamePanel) {
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
		dt = 0;	
		
		
	}
	
	@Override
	public void run()
	{
		Canvas canvas;
		while(active)
		{
			if(!gamePanel.game_paused)
			{
				long StartDraw = System.currentTimeMillis();
				canvas = null;
				try
				{
					canvas = this.surfaceHolder.lockCanvas();
					synchronized(surfaceHolder)
					{
						gamePanel.update(dt);
						gamePanel.draw(canvas); 
					}
				}
				finally
				{
					if(canvas != null)
					{
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
				
				
				long EndDraw = System.currentTimeMillis();
				dt = (EndDraw-StartDraw)/1000.f;
		}
	}

	
	
}

package ie.dit.reeageshark;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	public MainThread thread;
	public boolean Pause_game;
	private Background background;
	public float SharkSpeed; 
	public int ScreenWidth;
	public int Screenheigt;
	public Game game;
	
	
	public GamePanel(Context context, Game game,int ScreenWidth,int Screenheigt) {
		super(context);
		getHolder().addCallback(this);
		this.game = game;
		thread = new MainThread(getHolder(),this);
		background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.game_fon), ScreenWidth, this);
		
		
		
		setFocusable(true);
		SharkSpeed = ScreenWidth/2.f;
		this.ScreenWidth = ScreenWidth;
		this.Screenheigt = Screenheigt;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN){
			//ship.up=true;
		}
		if (event.getAction()==MotionEvent.ACTION_UP){
			//ship.up=false;
		}
		
		return true;
	}
	
	void Draw(Canvas canvas){
		if (!Pause_game)
			if (canvas!=null){
				canvas.drawColor(Color.BLACK);
				background.draw(canvas);
			}
				
	}
	
	void Update(float dt){
	
		background.update(dt);
		
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setActive(true);
		thread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		while (retry) {
				try{
					thread.join();
					retry=false;
				} catch (InterruptedException e){
					
				}
			
		}
		
	}

}

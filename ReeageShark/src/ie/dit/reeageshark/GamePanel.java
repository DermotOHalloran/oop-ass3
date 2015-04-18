package ie.dit.reeageshark;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	public MainThread thread;
	public boolean Pause_game; 
	private Background background;
	public float SharkSpeed; 
	public int ScreenWidth;
	public int ScreenHeight;
	public Game game; //game class
	private Shark shark; //shark class
	private Barriermanager BM;
	private Bonus crate; // point class
	private Jelly jelly;
	
	public GamePanel(Context context, Game game,int ScreenWidth,int ScreenHeight) {
		super(context);
		getHolder().addCallback(this);
		this.game = game;
		thread = new MainThread(getHolder(),this);
		background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.game_fon), ScreenWidth, this);
		BM = new Barriermanager(BitmapFactory.decodeResource(getResources(), R.drawable.barier), this);
		BM.setScreen(ScreenWidth, ScreenHeight);
		shark = new Shark(BitmapFactory.decodeResource(getResources(), R.drawable.player), 100, 0, ScreenWidth, ScreenHeight);
		crate = new Bonus(BitmapFactory.decodeResource(getResources(), R.drawable.bonus),-200,-200);
		jelly = new Jelly(BitmapFactory.decodeResource(getResources(), R.drawable.jell1y),-300,-300);
		ArrayList<Bitmap> animation = new ArrayList<Bitmap>();//animation of dying shark
		animation.add(BitmapFactory.decodeResource(getResources(), R.drawable.boom1));
		animation.add(BitmapFactory.decodeResource(getResources(), R.drawable.boom2));
		animation.add(BitmapFactory.decodeResource(getResources(), R.drawable.boom3));
		animation.add(BitmapFactory.decodeResource(getResources(), R.drawable.boom4));
		shark.setBloodAnimation(animation);
		
		crate.setBarrierManager(BM);// set the barier manager for the crate class
		jelly.setBarrierManager(BM);
		setFocusable(true);
		SharkSpeed = ScreenWidth/2.f;
		this.ScreenWidth = ScreenWidth;
		this.ScreenHeight = ScreenHeight;
	}

	@Override //movement of the shark if the screen is clicked the shark goes up
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN){
			shark.up=true;
		}
		if (event.getAction()==MotionEvent.ACTION_UP){
			shark.up=false;
		}
		
		return true;// make sure that touching allredy happened
	}
	
	void Draw(Canvas canvas){
		if (!Pause_game)
			if (canvas!=null){
				canvas.drawColor(Color.BLACK);
				background.draw(canvas);
				shark.draw(canvas);//drawing the shark
				BM.draw(canvas);//drawing the barriers
				crate.draw(canvas);
				jelly.draw(canvas);
			}
				
	}
	
	void Update(float dt)
	{
		
		shark.update(dt);//updating the shark
		if(!shark.death)
		{
			background.update(dt);
			BM.update(dt);//updating barriers
			crate.update(dt); // updating the position of the coin
			ArrayList<Point> crate_point = new ArrayList<Point>(crate.GetArray());
			if (shark.bump(crate_point.get(0), crate_point.get(1), crate_point.get(2), crate_point.get(3)))
			{
				crate.setX(-200); // when its touched the crate goes off the screen
				crate.setY(-200);
			}
			jelly.update(dt);
			ArrayList<Point> jelly_point = new ArrayList<Point>(jelly.GetArray());
			if (shark.bump(jelly_point.get(0), jelly_point.get(1), jelly_point.get(2), jelly_point.get(3)))
			{
				jelly.setX(-300); // when its touched the crate goes off the screen
				jelly.setY(-300);
			}
			}
			for(int i = 0 ; i<BM.TopWalls.size(); i++)
			{
				ArrayList< Point>temp = new ArrayList<Point>(BM.TopWalls.get(i).GetArray());
				ArrayList< Point>temp2 = new ArrayList<Point>(BM.BottomWalls.get(i).GetArray());
				if ((shark.bump(temp.get(0), temp.get(1), temp.get(2), temp.get(3)))||
						(shark.bump(temp2.get(0), temp2.get(1), temp2.get(2), temp2.get(3))))
				{ 
					shark.death = true;
					
				}
			}
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

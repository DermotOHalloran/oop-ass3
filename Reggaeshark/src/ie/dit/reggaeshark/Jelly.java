package ie.dit.reggaeshark;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Jelly {

	private Bitmap bitmap;	// the actual bitmap	
	private int x;	//x co-ordinates	
	private int y;	//y co-ordinates		
	Barriermanager BGG; //Barier Manager so we know where to spawn the crates
	
	public Jelly(Bitmap decodeResource, int x, int y) 
	{
		// TODO Auto-generated constructor stub
		this.bitmap = decodeResource;
		this.x = x;
		this.y = y;	
		
	}
	
	public void setBarrierManager(Barriermanager candidate) // used for coins to be generated in the playable area
	{
		BGG=candidate;
	}

	public Bitmap getBitmap() // bitmap method
	{
		return bitmap;
	}
	
	public void draw(Canvas canvas) // same as the shark class and the barier class
	{
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);	
	}
	
	public void update(float dt){
		if (x<-BGG.game_panel.ScreenWidth/4)
		{
			x=BGG.game_panel.ScreenWidth+bitmap.getWidth();
			Random r = new Random();
			y=r.nextInt(BGG.dl)+BGG.dpos -BGG.dl/2; // position is taken randomly from the Barrier manager
		}
		
		x -=BGG.game_panel.SharkSpeed*dt;
	}
	
	public ArrayList<Point> GetArray() // copy method from the barrier class it returns all the point which are needed for colision detection
	{
		Point TL = new Point(), TR = new Point(), BL = new Point(), BR = new Point();
		TL.x = x-bitmap.getWidth() / 2;
		TL.y = y - bitmap.getHeight() / 2;
		
		TR.x = x+bitmap.getWidth() / 2;
		TR.y = y - bitmap.getHeight() / 2;
		
		BL.x = x-bitmap.getWidth() / 2;
		BL.y = y + bitmap.getHeight() / 2;
		
		BR.x = x+bitmap.getWidth() / 2;
		BR.y = y+bitmap.getHeight() / 2;
		
		ArrayList<Point> temp = new ArrayList<Point>();
		temp.add(TL);
		temp.add(TR);
		temp.add(BR);
		temp.add(BL);
		return temp;
	}

	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x=x;
	}

	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y=y;
	}
}

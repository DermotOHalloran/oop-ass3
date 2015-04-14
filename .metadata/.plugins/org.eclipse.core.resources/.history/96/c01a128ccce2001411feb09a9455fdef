package ie.dit.reeageshark;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;


import java.util.Random;

public class Barier {
	
	private Bitmap bitmap;	
	private int x;			
	private int y;			
	
	Barriermanager BM;
	boolean doit;

	public Barier(Bitmap center, int x, int y) {
		bitmap=center;
		this.x=x;
		this.y=y;
	}

	public void setManager(Barriermanager barriermanager) {
		BM = barriermanager;
		
	}
	
	public Bitmap getBitmap() {
		
		return bitmap;
	}
	
	public void setY(int y) {
		this.y=y;
		
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null); //bitmap set to screen center
		
	}

	public void update(float dt, boolean b) { //function is used to regenerate barriers after they move off screen
		
if (x<-bitmap.getWidth()){ //If barrier is off screen
			if (b)
			{			
				if (Math.abs(BM.TargetY-BM.dpos)<50)
					doit = true;
				if ((BM.TargetY==-1)||doit){
					BM.TargetY = new Random().nextInt(BM.screenH - BM.dl/20)+BM.dl/40; //Random barrier placement along the available row
				}
				if (BM.dpos<BM.TargetY)
					BM.dpos = BM.dpos + new Random().nextInt(15);
				else
					BM.dpos = BM.dpos - new Random().nextInt(15);	
				y = BM.dpos - BM.dl/2 -bitmap.getHeight()/2;
			}
			else
			{
				y = BM.dpos + BM.dl/2+bitmap.getHeight()/2;
			}	
			x=(int) (x +bitmap.getWidth()*(BM.TopWalls.size()-1)); //End of the row
		}
		
	 x = (int) (x - BM.game_panel.SharkSpeed*dt); //Constant barrier progression
		
	}
		
	public ArrayList<Point> GetArray() {
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

}
	
	



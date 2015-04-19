package ie.dit.reeageshark;

import android.graphics.Bitmap;
import java.util.ArrayList;
import android.graphics.Canvas;

public class Barriermanager {
	
	//Set of variables, used for controlling game barriers
	Bitmap center;
	int sharkHeight;
	int Num;
	int screenH;
	int dl;
	int TargetY =-1;
	int dpos;
	public GamePanel game_panel;
	
	ArrayList<Barier>  TopWalls = null;
	ArrayList<Barier>  BottomWalls = null;

	public Barriermanager(Bitmap decodeResource, GamePanel game_panel) {
		center = decodeResource;
		this.game_panel =game_panel;
	}
	
	void setSharkH(int h){
		sharkHeight = h;
	}
	
	public void setScreen(int width, int height) { //This function is setting the number of barriers and their positions
		screenH = height;
		Num = (width)/center.getWidth()+15;
		TopWalls = new ArrayList<Barier>(); //Initializing barrier arrays
		BottomWalls = new ArrayList<Barier>();
		for (int i = 0; i<Num+1; i++){
			Barier BB = new Barier(center, width+200+center.getWidth()*i, 0);
			BB.setManager (this);
			TopWalls.add(BB);
			Barier BBB = new Barier(center, width+200+center.getWidth()*i, 0);//Loop creates a manager, set up barriers off screen
			BBB.setManager (this);
			BottomWalls.add(BBB);
		}
		Generate();
	}

	private void Generate() { 
		int h = center.getHeight()/2;
		dl = screenH;
		dpos =screenH/2;
		int new_dl = screenH*3/5;
		int inc =  (dl-new_dl)/Num;
		for (int i = 0; i<Num+1; i++){
			dl = dl - inc;
			h =TopWalls.get(i).getBitmap().getHeight()/2;
			TopWalls.get(i).setY(dpos -dl/2-h);
			BottomWalls.get(i).setY(dpos +dl/2+h);
			
		}
		
	}
	
	public void draw(Canvas canvas){
		for (int i=0;i<Num+1; i++){
			TopWalls.get(i).draw(canvas);
			BottomWalls.get(i).draw(canvas);
		}
	}
	
	public void update(float dt){
		for (int i=0;i<Num+1; i++){
			TopWalls.get(i).update(dt, true);
			BottomWalls.get(i).update(dt, false);
		}
	}

	


}



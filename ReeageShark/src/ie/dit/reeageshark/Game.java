package ie.dit.reeageshark;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;


public class Game extends Activity {
	
	View pauseButton;
	
	RelativeLayout Rel_main_game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		Rel_main_game = (RelativeLayout) findViewById(R.id.main_game_rl);
		
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);

		final int heightS = dm.heightPixels;
		final int widthS = dm.widthPixels;
		
		LayoutInflater myInflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
		pauseButton = myInflater.inflate(R.layout.pause, null, false);
		pauseButton.setX(widthS - 245);
		pauseButton.setY(0);
		Rel_main_game.addView(pauseButton);
		
	}

}

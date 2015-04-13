package ie.dit.reeageshark;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Game extends Activity {
	
	View pauseButton;
	View PauseMenu;

	RelativeLayout Rel_main_game;
	 
	GamePanel game_panel;
    
	
	OnClickListener Continue_list = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			PauseMenu.setVisibility(View.GONE);
			pauseButton.setVisibility(View.VISIBLE);
		
		}
	};
	
	OnClickListener To_Main_Menu_list = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Game.this.finish();
			
		}
	};
	
	OnClickListener Pausa_click =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			pauseButton.setVisibility(View.GONE);
			PauseMenu.setVisibility(View.VISIBLE);
			
			//Pause Starts
			
		}
	};
			
			
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		Rel_main_game = (RelativeLayout) findViewById(R.id.main_game_rl);
		
		game_panel = new GamePanel(getApplicationContext(), this);
		Rel_main_game.addView(game_panel);
		
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);

		final int heightS = dm.heightPixels;
		final int widthS = dm.widthPixels;
		
		LayoutInflater myInflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
		pauseButton = myInflater.inflate(R.layout.pause, null, false);
		pauseButton.setX(widthS - 245);
		pauseButton.setY(0);
		Rel_main_game.addView(pauseButton);
		pauseButton.setOnClickListener(Pausa_click);
		pauseButton.setOnClickListener(Pausa_click);pauseButton.setOnClickListener(Pausa_click);pauseButton.setOnClickListener(Pausa_click);
		pauseButton.getLayoutParams().height=250;
		pauseButton.getLayoutParams().width=250;
		
		PauseMenu= myInflater.inflate(R.layout.pause_menu, null, false);
		Rel_main_game.addView(PauseMenu);
		PauseMenu.setVisibility(View.GONE);//hiding the pause menu the screen
		
		ImageView Cont = (ImageView)PauseMenu.findViewById(R.id.imCont);
		ImageView MainMenuTo = (ImageView)PauseMenu.findViewById(R.id.toMain);
		Cont.setOnClickListener(Continue_list);
		MainMenuTo.setOnClickListener(To_Main_Menu_list);
	}

}

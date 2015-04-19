package ie.dit.reeageshark;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Game extends Activity {
	
	final static int UPDATE_SCORE = 0;
	final static int DEATH = 1;
	final static int LOSE = 2;
	
	View pauseButton;
	View PauseMenu;
	View LoseDialog;

	RelativeLayout Rel_main_game;
	TextView txt;
	GamePanel game_panel;
	MediaPlayer MainMusic;
	int get_crate=0;
	int score=0;
    
	final Handler handler = new Handler() // catching a message and doing assaigned function
	{
		@Override
		public void handleMessage(Message msg) {
			if (msg.what==UPDATE_SCORE){
				
				i_get_crate();
			}
			if (msg.what==DEATH){
				postDelayed(new Runnable() {
					
					@Override
					public void run() {
						Message msg = handler.obtainMessage();
					    msg.what = LOSE;
						handler.sendMessage(msg);
						
					}
				}, 3000); // 3 seconds delay after dying 
			}
			if (msg.what==LOSE){
				i_lose();
			}
			
			super.handleMessage(msg);
		}
	};
	
	OnClickListener Continue_list = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			PauseMenu.setVisibility(View.GONE);
			pauseButton.setVisibility(View.VISIBLE);
			MainMusic.start();
			game_panel.Pause_game=false;
		
		}
	};
	
	OnClickListener To_Main_Menu_list = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			game_panel.thread.setActive(false);// let us start the game again
			MainMusic.stop();
			Game.this.finish();
			
		}
	};
	
	OnClickListener Pausa_click =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			pauseButton.setVisibility(View.GONE);
			PauseMenu.setVisibility(View.VISIBLE);
			MainMusic.pause();
			game_panel.Pause_game=true;
			//Pause Starts
			
		}
	};
			
			
			
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
		
		game_panel = new GamePanel(getApplicationContext(), this,widthS, heightS);
		Rel_main_game.addView(game_panel);
		
		RelativeLayout RR = new RelativeLayout(this);
		RR.setBackgroundResource(R.drawable.btn);
		RR.setGravity(Gravity.CENTER);
		Rel_main_game.addView(RR,400,150);
		RR.setX(0);
		txt= new TextView(this);
		Typeface Custom = Typeface.createFromAsset(getAssets(), "font.ttf");
		txt.setTypeface(Custom);
		txt.setTextColor(Color.GREEN);
		txt.setText("Score: " + score);
		RR.addView(txt);
		
		

		MainMusic = MediaPlayer.create(Game.this, R.raw.music);
		MainMusic.setVolume(0.7f, 0.7f);
		MainMusic.setLooping(true);
		MainMusic.start();

		
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
		
		LoseDialog= myInflater.inflate(R.layout.lose, null, false); // Looosing xml dialog window
		Rel_main_game.addView(LoseDialog);
		ImageView Lose_to_main = (ImageView) LoseDialog.findViewById(R.id.toMain);
		Lose_to_main.setOnClickListener(To_Main_Menu_list);
		LoseDialog.setVisibility(View.GONE);
	}



	protected void i_lose() {
		// TODO Auto-generated method stub
		if (MainMusic.isPlaying()) // stops music and starts the loosing music then the loosing dialog shows up
			MainMusic.stop();
		MainMusic = MediaPlayer.create(Game.this, R.raw.lose);
		MainMusic.start();
		pauseButton.setVisibility(View.GONE);
		game_panel.Pause_game=true;// pasue the game
		LoseDialog.setVisibility(View.VISIBLE);
	}



	protected void i_get_crate() {
		// TODO Auto-generated method stub
		get_crate++; // for every crate we get 10 points
		score+=10;
		txt.setText("Score: " + score);
		MediaPlayer mp = MediaPlayer.create(Game.this, R.raw.bite);// playing the sound of biting
		mp.start();
		if (get_crate==42){
			i_win();
		}
	}



	private void i_win() {
		// TODO Auto-generated method stub
		
	}

}

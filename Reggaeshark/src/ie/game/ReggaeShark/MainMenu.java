package ie.game.ReggaeShark;

import ie.game.ReggaeShark.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainMenu extends Activity {

	
	RelativeLayout Btn;
	RelativeLayout Btn2;
	
	ImageView ImageButton;
	TextView txt,txt2;
	MediaPlayer MainMenuMusic;
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Btn = (RelativeLayout) findViewById(R.id.butto_start);
        ImageButton = (ImageView) findViewById(R.id.image_butto);
        txt = (TextView) findViewById(R.id.Begin_Text);
        
        Btn2 = (RelativeLayout) findViewById(R.id.about_butto);
        ImageButton = (ImageView) findViewById(R.id.help_butto);
        txt2 = (TextView) findViewById(R.id.about_text);
        
        Typeface Custom = Typeface.createFromAsset(getAssets(), "font.ttf");
        txt.setTypeface(Custom);
        txt2.setTypeface(Custom);
        
        Btn.setOnTouchListener(new ButtonTouch(ImageButton));
        Btn2.setOnTouchListener(new ButtonTouch(ImageButton));
        
			
		
		
		Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainMenu.this, Game.class);
				startActivity(myIntent);
			}
		});
		Btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainMenu.this, Game.class);
				startActivity(myIntent);
			}
		});
        
    }
    @Override
    protected void onStart() {
        MainMenuMusic = MediaPlayer.create(MainMenu.this, R.raw.main);
    	//MainMenuMusic.setVolume(0.3f, 0.3f);
    	MainMenuMusic.start();
    	super.onStart();
    }
    
    @Override
    protected void onStop() {
    	if (MainMenuMusic.isPlaying())
    		MainMenuMusic.stop();
    	super.onStop();
    }

  
}

package ie.dit.reggaeshark;

import ie.dit.reeageshark.R;
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
	ImageView ImageButton;
	TextView txt;
	MediaPlayer MainMenuMusic;
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Btn = (RelativeLayout) findViewById(R.id.butto_start);
        ImageButton = (ImageView) findViewById(R.id.image_butto);
        txt = (TextView) findViewById(R.id.Begin_Text);
        
        Typeface Custom = Typeface.createFromAsset(getAssets(), "font.ttf");
        txt.setTypeface(Custom);
        
        Btn.setOnTouchListener(new ButtonTouch(ImageButton));
        
			
		
		
		Btn.setOnClickListener(new OnClickListener() {
			
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
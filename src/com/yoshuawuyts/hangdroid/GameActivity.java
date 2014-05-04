package com.yoshuawuyts.hangdroid;

import java.util.Random;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	private String[] words;
	private Random rand;
	private String currWord;
	private LinearLayout wordLayout;
	private TextView[] charViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_game);
	  Resources res = getResources();
	  words = res.getStringArray(R.array.words);
	  rand = new Random();
	  currWord = "";
	  wordLayout = (LinearLayout)findViewById(R.id.word);
	  playGame();
	}
	
	private void playGame() {
		String newWord = words[rand.nextInt(words.length)];
		while(newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];
		currWord = newWord;
		wordLayout.removeAllViews();
		
		for (int c = 0; c < currWord.length(); c++) {
		  charViews[c] = new TextView(this);
		  charViews[c].setText(""+currWord.charAt(c));
		}
		
		for (int c = 0; c < currWord.length(); c++) {
		  charViews[c] = new TextView(this);
		  charViews[c].setText(""+currWord.charAt(c));
		 
		  charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		  charViews[c].setGravity(Gravity.CENTER);
		  charViews[c].setTextColor(Color.WHITE);
		  charViews[c].setBackgroundResource(R.drawable.letter_bg);
		  
		  //add to layout
		  wordLayout.addView(charViews[c]);
		}
	}
}

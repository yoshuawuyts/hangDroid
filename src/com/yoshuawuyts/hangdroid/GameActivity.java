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
import android.widget.GridView;

public class GameActivity extends Activity {
	private String[] words;
	private Random rand;
	private String currWord;
	private LinearLayout wordLayout;
	private TextView[] charViews;
	private GridView letters;
	private LetterAdapter ltrAdapt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_game);
	  Resources res = getResources();
	  words = res.getStringArray(R.array.words);
	  rand = new Random();
	  currWord = "";
	  wordLayout = (LinearLayout)findViewById(R.id.word);
	  
	  // get a reference to the grid view
	  letters = (GridView)findViewById(R.id.letters);
	  
	  // call playGame
	  playGame();
	}
	
	private void playGame() {
		String newWord = words[rand.nextInt(words.length)];
		while(newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];
		
		// update instance with new word
		currWord = newWord;
		
		// create text view for each letter
		charViews = new TextView[currWord.length()];
		
		// remove any text views from wordLayout layout
		wordLayout.removeAllViews();
		
		// Use a simple for loop to iterate over each letter of the answer, 
		// create a text view for each letter, 
		// and set the text view's text to the current letter.
		for (int c = 0; c < currWord.length(); c++) {
		  charViews[c] = new TextView(this);
		  charViews[c].setText(""+currWord.charAt(c));
		}
		
		// access the character inside the string at a specific index
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
		
		ltrAdapt=new LetterAdapter(this);
		letters.setAdapter(ltrAdapt);
	}
}

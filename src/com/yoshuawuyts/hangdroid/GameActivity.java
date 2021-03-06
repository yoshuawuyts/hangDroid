/**
 * Package declaration
 */

package com.yoshuawuyts.hangdroid;

/**
 * Module dependencies
 */

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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Main
 */

public class GameActivity extends Activity {
	private String[] words;
	private Random rand;
	private String currWord;
	private LinearLayout wordLayout;
	private TextView[] charViews;
	private TextView txtname;
	private GridView letters;
	private LetterAdapter ltrAdapt;
	
	//number of body parts
	private int numParts=6;
	//current part - will increment when wrong answers are chosen
	private int currPart;
	//number of characters in current word
	private int numChars;
	//number correctly guessed
	private int numCorr;
	
	/**
	 * Create a blank game environment
	 * @api protected
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_game);
	  Resources res = getResources();
	  words = res.getStringArray(R.array.words);
	  rand = new Random();
	  currWord = "";

	  // get a reference to the linearLayout view
	  wordLayout = (LinearLayout)findViewById(R.id.word);
	  
	  // get a reference to the grid view
	  letters = (GridView)findViewById(R.id.letters);

	  txtname = (TextView) findViewById(R.id.txtName);
	  
	  // call playGame
	  playGame();
	}

	/**
	 * Start a new game
	 * @api private
	 */
	
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
		// access the character inside the string at a specific index
		for (int c = 0; c < currWord.length(); c++) {
		  charViews[c] = new TextView(this);
		  charViews[c].setText("" + currWord.charAt(c));
		 
		  charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		  charViews[c].setGravity(Gravity.CENTER);
		  charViews[c].setTextColor(Color.WHITE);
		  charViews[c].setBackgroundResource(R.drawable.letter_bg);
		  
		  //add to layout
		  wordLayout.addView(charViews[c]);
		}

		// initialize counter
		
		txtname.setText("" + numParts);
		txtname.setGravity(Gravity.CENTER);
		txtname.setTextColor(Color.WHITE);

		ltrAdapt=new LetterAdapter(this);
		letters.setAdapter(ltrAdapt);
		
		// init base values
		currPart=0;
		numChars=currWord.length();
		numCorr=0;
	}
	
	/**
	 * Initialize menu
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Handle clicks on menu
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  	case R.id.settings_restart:
	  		GameActivity.this.playGame();
	  		return true;
		  case R.id.settings_highscore:
			  //NavUtils.navigateUpFromSameTask(this);
			  return true;
		  case R.id.settings_settings:
		    //showHelp();
		    return true;
	  }
	  return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Handle letter pressing logic
	 * 
	 * @param view
	 * @api public
	 */
	
	public void letterPressed(View view) {
	  //user has pressed a letter to guess
	  String ltr=((TextView)view).getText().toString();
	  char letterChar = ltr.charAt(0);
	  view.setEnabled(false);
	  view.setBackgroundResource(R.drawable.letter_down);
	  
	  // loop through letters to check if found
	  boolean correct = false;
	  for(int k = 0; k < currWord.length(); k++) {
	    if(currWord.charAt(k)==letterChar){
	      correct = true;
	      numCorr++;
	      charViews[k].setTextColor(Color.BLACK);
	    }
	  }
	  
	  // handle press decision tree
	  if (correct) {
	  	// game complete
		  if (numCorr == numChars) {
			  // Disable Buttons
			  disableBtns();
			 
			  // Display Alert Dialog
			  AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
			  winBuild.setTitle("HUZZAH");
			  winBuild.setMessage("You win!\n\nThe answer was:\n\n" + currWord);
			  winBuild.setPositiveButton("Play Again",
			    new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int id) {
			        GameActivity.this.playGame();
			    	}
			  	}
	    	);
			 
			  winBuild.setNegativeButton("Exit",
			    new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int id) {
		  	      GameActivity.this.finish();
	    			}
	    		}
	    	);
			 
			  winBuild.show();
			}
		} else if (currPart < numParts) {
		  //some guesses left
		  currPart++;
		  txtname.setText("" + (numParts - currPart));
		} else {
		  //user has lost
		  disableBtns();
		 
		  // Display Alert Dialog
		  AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
		  loseBuild.setTitle("OOPS");
		  loseBuild.setMessage("You lose!\n\nThe answer was:\n\n"+currWord);
		  loseBuild.setPositiveButton("Play Again",
		    new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int id) {
		        GameActivity.this.playGame();
		   		}
		 		}
	 		);
		 
		  loseBuild.setNegativeButton("Exit",
		    new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int id) {
		        GameActivity.this.finish();
		   		}
		   	}
		  );
		 
		  loseBuild.show();
		}
	}
	
	/**
	 * Disable the buttons once the game has finished.
	 * @api public
	 */
	
	public void disableBtns() {
	  int numLetters = letters.getChildCount();
	  for (int l = 0; l < numLetters; l++) {
	    letters.getChildAt(l).setEnabled(false);
	  }
	}
}

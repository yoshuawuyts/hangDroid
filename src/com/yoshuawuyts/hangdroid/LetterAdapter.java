package com.yoshuawuyts.hangdroid;

/**
 * Module dependencies
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

/**
 * Main LetterAdapter
 */

public class LetterAdapter extends BaseAdapter {
	private String[] letters;
	private LayoutInflater letterInf;
	
	// constructor method
	public LetterAdapter(Context c) {
	  letters = new String[26];
		for (int a = 0; a < letters.length; a++) {
		  letters[a] = "" + (char)(a+'A');
		}
		
		letterInf = LayoutInflater.from(c);
	}

	/**
	 * Get letter count
	 */

	@Override
	public int getCount() {
	  return letters.length;
	}

	/**
	 * Null
	 */

	@Override
	public Object getItem(int arg0) {
	  return null;
	}
	 
	/**
	 * Get item ID
	 */

	@Override
	public long getItemId(int arg0) {
	  return 0;
	}

	/**
	 * Get corresponding adapter view
	 */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	  //create a button for the letter at this position in the alphabet
	  Button letterBtn;
	  if (convertView == null) {
	    //inflate the button layout
	    letterBtn = (Button)letterInf.inflate(R.layout.letter, parent, false);
	  } else {
	    letterBtn = (Button) convertView;
	  }
	  //set the text to this letter
	  letterBtn.setText(letters[position]);
	  return letterBtn;
	}
}

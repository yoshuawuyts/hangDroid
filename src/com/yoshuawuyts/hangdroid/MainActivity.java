package com.yoshuawuyts.hangdroid;

import android.support.v7.app.ActionBarActivity;
// import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
// import android.os.Build;
import android.content.Intent;
// import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button playBtn = (Button)findViewById(R.id.playBtn);
		playBtn.setOnClickListener(this);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
		    .add(R.id.container, new PlaceholderFragment()).commit();
		}
		Intent playIntent = new Intent(this, GameActivity.class);
		this.startActivity(playIntent);
	}
	
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.playBtn) {
		  Intent playIntent = new Intent(this, GameActivity.class);
		  this.startActivity(playIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
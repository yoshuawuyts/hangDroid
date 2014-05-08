package com.yoshuawuyts.hangdroid;

import android.support.v7.app.ActionBarActivity;
// import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}
}
package com.example.uiutilslibraryforandroid;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
	public static class PlaceholderFragment extends Fragment implements
			OnClickListener {

		Button btn_slide_all;
		Button btn_slide_on;
		Button btn_slide_qq;

		Intent intent = new Intent();
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			btn_slide_all = (Button) rootView.findViewById(R.id.btn_slide_all);
			btn_slide_on = (Button) rootView.findViewById(R.id.btn_slide_on);
			btn_slide_qq = (Button) rootView.findViewById(R.id.btn_slide_qq);

			btn_slide_all.setOnClickListener(this);
			btn_slide_on.setOnClickListener(this);
			btn_slide_qq.setOnClickListener(this);

			return rootView;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_slide_all:
				
                intent.setClass(getActivity(), SlideAllActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
			case R.id.btn_slide_on:
//				 intent.setClass(this, cls);
//	                startActivity(intent);
				break;
			case R.id.btn_slide_qq:
//				 intent.setClass(this, cls);
//	                startActivity(intent);
				break;
                 
			default:
				break;
			}
		}
	}
}

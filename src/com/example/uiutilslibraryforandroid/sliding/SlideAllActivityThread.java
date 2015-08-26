package com.example.uiutilslibraryforandroid.sliding;

import com.example.uiutilslibraryforandroid.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SlideAllActivityThread extends Activity implements OnTouchListener,
		OnClickListener {

	private LinearLayout mLayout;
	private LinearLayout menu;
	private LinearLayout content;
	private LayoutParams menuParams;
	private LayoutParams contentParams;

	// menu完全显示时，留给content的宽度值。
	private static final int menuPadding = 150;

	// 分辨率
	private int disPlayWidth;

	private float xDown;
	private float xMove;

	private boolean mIsShow = false;
	private static final int speed = 50;

	private Thread mThread;

	private int toRight = 0001;
	private int toLeft = 0002;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_slide_all);

		disPlayWidth = getWindowManager().getDefaultDisplay().getWidth();

		mLayout = (LinearLayout) findViewById(R.id.layout);
		mLayout.setOnTouchListener(this);
		mLayout.setOnClickListener(this);

		menu = (LinearLayout) findViewById(R.id.menu);
		// menu.setOnClickListener(this);
		content = (LinearLayout) findViewById(R.id.content);
		// content.setOnClickListener(this);
		menuParams = (LayoutParams) menu.getLayoutParams();
		contentParams = (LayoutParams) content.getLayoutParams();
		// findViewById(R.id.layout).setOnTouchListener(this);

		menuParams.width = disPlayWidth - menuPadding;
		contentParams.width = disPlayWidth;

		showMenu(mIsShow);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
			// new showMenuAsyncTask().execute(-50);
			break;
		case R.id.content:
			// new showMenuAsyncTask().execute(50);
			break;
		}

	}

	float original_x;
	float move_x;
	float original_move_x = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			original_x = event.getX();

			break;
		case MotionEvent.ACTION_MOVE:

			move_x = event.getX() - original_x;

			if (!mIsShow) {
				if (move_x < 0) {
				} else {
					if (menuParams.leftMargin <= -300) {
						menuParams.leftMargin = menuParams.leftMargin
								+ (int) move_x - (int) original_move_x;
						menu.setLayoutParams(menuParams);
					}
					if (menuParams.leftMargin > -300) {
						new showMenuAsyncTask().execute(toLeft);
						mIsShow = true;
					}

				}
			}
			if (mIsShow) {
				if (move_x > 0) {
				} else {
					menuParams.leftMargin = menuParams.leftMargin
							+ (int) move_x - (int) original_move_x;
					if (menuParams.leftMargin < -600) {
						menuParams.leftMargin = -(disPlayWidth - menuPadding);
						mIsShow = false;
					}
					menu.setLayoutParams(menuParams);
				}
			}
			Log.i("tag", "menuParams.leftMargin=" + menuParams.leftMargin);
			original_move_x = move_x;
			break;
		case MotionEvent.ACTION_UP:
			original_move_x = 0;
			if (!mIsShow) {
				if (menuParams.leftMargin <= -300) {
					menuParams.leftMargin = -(disPlayWidth - menuPadding);
					menu.setLayoutParams(menuParams);
				}
			}
			if (mIsShow) {
				if (move_x > 0) {
				} else {
					if (menuParams.leftMargin >= -600) {
						menuParams.leftMargin = 0;
						menu.setLayoutParams(menuParams);
					}
				}
			}
			break;
		}
		return false;
	}

	private void showMenu(boolean isShow) {
		if (isShow) {
			mIsShow = true;
			menuParams.leftMargin = 0;
		} else {
			mIsShow = false;
			menuParams.leftMargin = 0 - menuParams.width;
		}
		menu.setLayoutParams(menuParams);
	}

	/**
	 * 
	 * 这是主要代码：模拟动画过程，也让我更熟悉了AsyncTask这玩意儿
	 * 
	 */
	class showMenuAsyncTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {

			while (true) {

				try {
					Thread.sleep(2);
					menuParams.leftMargin = menuParams.leftMargin + 4;
					if (menuParams.leftMargin >= 0) {
						break;
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				publishProgress(menuParams.leftMargin);

			}
			return menuParams.leftMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			menuParams.leftMargin = values[0];
			menu.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			menuParams.leftMargin = result;
			menu.setLayoutParams(menuParams);
		}

	}

}

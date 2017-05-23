package com.example.appbackgroundstatus;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author Harsha
 * 
 *         BaseActivity class extends Activity
 */
public abstract class BaseActivity extends Activity {

	protected static final String TAG = BaseActivity.class.getName();

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public static boolean isAppWentToBg = false;

	public static boolean isWindowFocused = false;

	public static boolean isBackPressed = false;

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart isAppWentToBg " + isAppWentToBg);

		applicationWillEnterForeground();

		super.onStart();
	}

	private void applicationWillEnterForeground() {
		if (isAppWentToBg) {
			isAppWentToBg = false;
			Toast.makeText(getApplicationContext(), "App is in foreground",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();

		Log.d(TAG, "onStop ");
		applicationdidenterbackground();
	}

	public void applicationdidenterbackground() {
		if (!isWindowFocused) {
			isAppWentToBg = true;
			Toast.makeText(getApplicationContext(),
					"App is Going to Background", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {

		if (this instanceof MainActivity) {

		} else {
			isBackPressed = true;
		}

		Log.d(TAG,
				"onBackPressed " + isBackPressed + ""
						+ this.getLocalClassName());
		super.onBackPressed();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

		isWindowFocused = hasFocus;

		if (isBackPressed && !hasFocus) {
			isBackPressed = false;
			isWindowFocused = true;
		}

		super.onWindowFocusChanged(hasFocus);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_settings:
			Intent i = new Intent(this, SettingActivity.class);
			startActivity(i);
			break;
		}
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_settings:
			Intent i = new Intent(this, SettingActivity.class);
			startActivity(i);
			break;
		}
		return true;
	}

}

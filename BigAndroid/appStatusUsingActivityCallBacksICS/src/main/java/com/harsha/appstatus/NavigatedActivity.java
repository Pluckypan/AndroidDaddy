package com.harsha.appstatus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * NavigatedActivity is used to check the life cycle flow when we shift between
 * activities.
 * 
 * @author Vardhan
 * 
 */
public class NavigatedActivity extends Activity {

	private static final String TAG = NavigatedActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, TAG + " onCreate");
		setContentView(R.layout.navigated_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, TAG + " onStart");
		if (AppStatusApplication.wasInBackground) {
			Toast.makeText(getApplicationContext(),
					"Application came to foreground", Toast.LENGTH_SHORT)
					.show();
			AppStatusApplication.wasInBackground = false;
		}
	}
}

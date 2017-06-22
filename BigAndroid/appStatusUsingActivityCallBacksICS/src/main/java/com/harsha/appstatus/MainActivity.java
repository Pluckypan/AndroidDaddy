package com.harsha.appstatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * MainActivity is used to check the life cycle flow and to test the application
 * status.
 * 
 * @author Vardhan
 * 
 */
public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, TAG + " onCreate");
		setContentView(R.layout.main);
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

		((Button) findViewById(R.id.btn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(getApplicationContext(),
								NavigatedActivity.class));

					}
				});
	}
}

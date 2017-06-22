package com.harsha.appstatus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * AppStatusApplication helps us to find, whether the application came to
 * foreground or not by using interfaces like ActivityLifecycleCallbacks,
 * ComponentCallbacks2 and Broadcast receiver.
 * 
 * @author Vardhan
 * 
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AppStatusApplication extends Application implements
		ActivityLifecycleCallbacks, ComponentCallbacks2 {

	private static String TAG = AppStatusApplication.class.getName();

	public static String stateOfLifeCycle = "";

	public static boolean wasInBackground = false;
	ScreenOffReceiver screenOffReceiver = new ScreenOffReceiver();

	@Override
	public void onCreate() {
		super.onCreate();
		registerActivityLifecycleCallbacks(this);

		registerReceiver(screenOffReceiver, new IntentFilter(
				"android.intent.action.SCREEN_OFF"));
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle arg1) {
		Log.d(TAG, "onActivityCreated " + activity.getLocalClassName());
		wasInBackground = false;
		stateOfLifeCycle = "Create";
	}

	@Override
	public void onActivityStarted(Activity activity) {
		Log.d(TAG, "onActivityStarted " + activity.getLocalClassName());
		stateOfLifeCycle = "Start";
	}

	@Override
	public void onActivityResumed(Activity activity) {
		Log.d(TAG, "onActivityResumed " + activity.getLocalClassName());
		stateOfLifeCycle = "Resume";
	}

	@Override
	public void onActivityPaused(Activity activity) {
		Log.d(TAG, "onActivityPaused " + activity.getLocalClassName());
		stateOfLifeCycle = "Pause";
	}

	@Override
	public void onActivityStopped(Activity activity) {
		Log.d(TAG, "onActivityStopped " + activity.getLocalClassName());
		stateOfLifeCycle = "Stop";
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle arg1) {
		Log.d(TAG,
				"onActivitySaveInstanceState " + activity.getLocalClassName());
	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		Log.d(TAG, "onActivityDestroyed " + activity.getLocalClassName());
		wasInBackground = false;
		stateOfLifeCycle = "Destroy";
	}

	@Override
	public void onTrimMemory(int level) {
		if (stateOfLifeCycle.equals("Stop")) {
			wasInBackground = true;
		}
		super.onTrimMemory(level);
		Log.d(TAG, "onTrimMemory " + level);
	}

	class ScreenOffReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			wasInBackground = true;
		}
	}
}

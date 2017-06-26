package echo.engineer.oneactivity.widget;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import java.util.HashMap;

import echo.engineer.oneactivity.R;

/**
 * FRSoundPool
 * Created by Frank Fan<frank.fan@ubnt.com> on 5/2/17.
 */
public class FRSoundPool {
    public static final String TAG = "FRSoundPool";
    public static final boolean DEBUG = true;

    private static FRSoundPool sInstance;

    public static FRSoundPool get(Context context) {
        if (sInstance == null) {
            sInstance = new FRSoundPool(context.getApplicationContext());
        }
        return sInstance;
    }


    public enum SoundType {
//        Alert,
//        Paired,
//        System,
//        StartRecord,
//        EndRecord,
//        Shutter,
        CountDown,
        Dock,
//        LOW_STORAGE,
//        LOW_BATTERY
    }

    public static final int MAX_MEDIA_STREAMS = SoundType.values().length;

    private Context mContext;
    private SoundPool mMediaPool;
    private HashMap<SoundType, Integer> mTypeToId = new HashMap<>();

    private FRSoundPool(Context context) {
        mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mMediaPool = new SoundPool.Builder()
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA).build())
                    .setMaxStreams(MAX_MEDIA_STREAMS).build();
        } else {
            mMediaPool = new SoundPool(MAX_MEDIA_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        mMediaPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (DEBUG) Log.d(TAG, "onLoadComplete: " + sampleId);
            }
        });

//        mTypeToId.put(SoundType.Alert, mMediaPool.load(mContext, R.raw.alert, 1));
//        mTypeToId.put(SoundType.Paired, mMediaPool.load(mContext, R.raw.paried_sound, 1));
//        mTypeToId.put(SoundType.System, mMediaPool.load(mContext, R.raw.system_sound, 1));
//        mTypeToId.put(SoundType.StartRecord, mMediaPool.load(mContext, R.raw.begin_record, 1));
//        mTypeToId.put(SoundType.EndRecord, mMediaPool.load(mContext, R.raw.end_record, 1));
//        mTypeToId.put(SoundType.Shutter, mMediaPool.load(mContext, R.raw.photo_shutter, 1));
        mTypeToId.put(SoundType.CountDown, mMediaPool.load(mContext, R.raw.count_down_beep, 1));
        mTypeToId.put(SoundType.Dock, mMediaPool.load(mContext, R.raw.sound_for_dock, 1));
//        mTypeToId.put(SoundType.LOW_STORAGE, mMediaPool.load(mContext, R.raw.low_storage, 1));
//        mTypeToId.put(SoundType.LOW_BATTERY, mMediaPool.load(mContext, R.raw.low_battery, 1));

    }

    private HashMap<SoundType, Integer> mStreamId = new HashMap<>();

    public int play(SoundType type) {
        Integer id = mTypeToId.get(type);
        if (id == null) {
            if (DEBUG) Log.w(TAG, "play: failed, not exited. type=" + type);
            return -1;
        }
        int streamId = mMediaPool.play(id, 1, 1, 1, 0, 1);
        mStreamId.put(type, streamId);
        return streamId;
    }

    public void stop(SoundType type) {
        Integer id = mStreamId.get(type);
        if (id == null) {
            if (DEBUG) Log.w(TAG, "stop: failed, not exited. type=" + type);
            return;
        }
        mMediaPool.stop(id);
    }
}

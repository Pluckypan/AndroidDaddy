package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fragmentmaster.app.MasterFragment;

import echo.engineer.oneactivity.App;
import echo.engineer.oneactivity.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RetrofitFragment.java
 * Useage: RetrofitFragment
 * Created by Plucky<py@meitu.com> on 2017/8/14 - 13:45
 */

public class RetrofitFragment extends MasterFragment implements View.OnClickListener {

    private static final String TAG = "RetrofitFragment";
    TextView mTvMsg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_retrofit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view) {
        mTvMsg = (TextView) view.findViewById(R.id.tv_retrofit_msg);
        view.findViewById(R.id.btn_retrofit_weather).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit_weather:
                App.getComponent().getWeatherApi().getWeather("厦门")
                        .flatMap(json -> {
                            if (json == null) {
                                return Observable.error(new Exception("json is null"));
                            }
                            if (json.status != 1000) {
                                return Observable.error(new Exception("status is invalid."));
                            }
                            return Observable.just(json.data);
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(d -> {
                            Log.d(TAG, d.city);
                        }, t -> {
                            Log.e(TAG, "getWeather", t);
                        });
                break;
        }
    }
}

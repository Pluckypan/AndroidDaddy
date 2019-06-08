package echo.engineer.oneactivity.app.fragments;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import echo.engineer.oneactivity.App;
import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.app.MainActivity;
import echo.engineer.oneactivity.cmpts.sensor.GyroscopeSensorWrapper;
import echo.engineer.oneactivity.cmpts.sensor.SimpleGyroscopeSensorCallBack;
import engineer.echo.oneactivity.core.MasterFragment;
import engineer.echo.oneactivity.core.Request;

/**
 * HomeFragment
 * Created by Plucky<plucky@echo.engineer> on 6/22/17 2017 15:03.
 */

public class HomeFragment extends MasterFragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private GyroscopeSensorWrapper sensorWrapper;
    private TextView tvMsg;
    private ImageView tvTestImage;
    private int screenW;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnOrderList).setOnClickListener(this);
        view.findViewById(R.id.btnHello).setOnClickListener(this);
        view.findViewById(R.id.btnWorld).setOnClickListener(this);
        view.findViewById(R.id.btnSensor).setOnClickListener(this);
        view.findViewById(R.id.btnCrash).setOnClickListener(this);
        view.findViewById(R.id.btnCamera).setOnClickListener(this);
        view.findViewById(R.id.btnTestView).setOnClickListener(this);
        view.findViewById(R.id.btnRetrofit).setOnClickListener(this);
        view.findViewById(R.id.btnShadow).setOnClickListener(this);


        screenW = getResources().getDisplayMetrics().widthPixels;
        tvMsg = view.findViewById(R.id.tvMsg);
        tvTestImage = view.findViewById(R.id.tvTestImage);
        sensorWrapper = App.getComponent().getGyroscopeSensorWrapper();
        sensorWrapper.setSensorCallBack(new SimpleGyroscopeSensorCallBack() {
            @Override
            public void onYDegreesChanged(float y) {
                super.onYDegreesChanged(y);
                if (y >= -45 && y <= 45) {
                    int bitmapH = tvTestImage.getDrawable().getIntrinsicHeight();
                    int bitmapW = tvTestImage.getDrawable().getIntrinsicWidth();
                    Matrix matrix = new Matrix();
                    float dlt = (bitmapW - screenW) / 2;
                    float transX = -(dlt * y / 45 + dlt);
                    if (transX >= -(bitmapW - screenW) && transX <= 0) {
                        matrix.postTranslate(transX, -bitmapH / 2);
                        tvTestImage.setImageMatrix(matrix);
                        String msg = "msg: y degress has changed:" + y
                                + "\nbitmapH=" + bitmapH + " bitmapW=" + bitmapW
                                + " \ntransX=" + transX;
                        tvMsg.setText(msg);
                    }
                }
            }
        });
        Log.d(TAG, "sensorWrapper=" + (sensorWrapper != null));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOrderList:
                Request request = new Request(OrdersFragment.class);
                startFragment(request);
                break;
            case R.id.btnHello:
                Logger.d("call hello");
                ((MainActivity) getActivity()).sendMessage("hello");
                break;
            case R.id.btnWorld:
                ((MainActivity) getActivity()).sendMessage("world");
                break;
            case R.id.btnSensor:
                sensorWrapper.start();
                break;
            case R.id.btnCrash:
                throw new RuntimeException("HaHaHa~~~");
            case R.id.btnCamera:
                Request reqCamera = new Request(CameraFragment.class);
                startFragment(reqCamera);
                break;
            case R.id.btnTestView:
                Request reqTestView = new Request(TestViewDrawFragment.class);
                startFragment(reqTestView);
                break;
            case R.id.btnRetrofit:
                Request retrofitReq = new Request(RetrofitFragment.class);
                startFragment(retrofitReq);
                break;
            case R.id.btnShadow:
                startFragment(new Request(ShadowFragment.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sensorWrapper.stop();
    }
}

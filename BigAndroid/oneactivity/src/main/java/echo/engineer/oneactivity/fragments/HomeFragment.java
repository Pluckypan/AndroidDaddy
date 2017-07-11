package echo.engineer.oneactivity.fragments;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fragmentmaster.app.MasterFragment;
import com.fragmentmaster.app.Request;
import com.orhanobut.logger.Logger;

import echo.engineer.oneactivity.App;
import echo.engineer.oneactivity.MainActivity;
import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.cmpts.sensor.GyroscopeSensorWrapper;
import echo.engineer.oneactivity.cmpts.sensor.SimpleGyroscopeSensorCallBack;
import echo.engineer.oneactivity.immutables.Fatttther;
import echo.engineer.oneactivity.immutables.ImmutableFatttther;
import echo.engineer.oneactivity.immutables.ImmutableItem;

/**
 * HomeFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 15:03.
 */

public class HomeFragment extends MasterFragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private GyroscopeSensorWrapper sensorWrapper;
    private TextView tvMsg;
    private ImageView tvTestImage;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnOrderList).setOnClickListener(this);
        view.findViewById(R.id.btnHello).setOnClickListener(this);
        view.findViewById(R.id.btnWorld).setOnClickListener(this);
        view.findViewById(R.id.btnSensor).setOnClickListener(this);
        view.findViewById(R.id.btnCrash).setOnClickListener(this);
        tvMsg = (TextView) view.findViewById(R.id.tvMsg);
        tvTestImage = (ImageView) view.findViewById(R.id.tvTestImage);
        sensorWrapper = App.getComponent().getGyroscopeSensorWrapper();
        sensorWrapper.setSensorCallBack(new SimpleGyroscopeSensorCallBack() {
            @Override
            public void onYDegreesChanged(float y) {
                super.onYDegreesChanged(y);
                int bitmapH = tvTestImage.getDrawable().getIntrinsicHeight();
                int bitmapW = tvTestImage.getDrawable().getIntrinsicWidth();
                Matrix matrix = new Matrix();
                float transX = -bitmapW / 2 * (y / 90 + 1);
                matrix.postTranslate(transX, -bitmapH / 2);
                tvTestImage.setImageMatrix(matrix);
                String msg = "msg: y degress has changed:" + y
                        + "\nbitmapH=" + bitmapH + " bitmapW=" + bitmapW
                        + " \ntransX=" + transX;
                tvMsg.setText(msg);
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
                Fatttther fatttther =
                        ImmutableFatttther.builder()
                                .name("My value")
                                .addCounts(1)
                                .addCounts(2)
                                .build();
                Logger.d("call hello");
                ((MainActivity) getActivity()).sendMessage("hello");
                break;
            case R.id.btnWorld:
                ImmutableItem namelessItem = ImmutableItem.builder()
                        .name("Nameless")
                        .addTags("important", "relevant")
                        .description("Description provided")
                        .build();
                /*AbstractItem namelessItem = AbstractItem.builder()
                        .setName("Nameless")
                        .addTags("important", "relevant")
                        .setDescription("Description provided")
                        .build();*/
                ((MainActivity) getActivity()).sendMessage("world");
                break;
            case R.id.btnSensor:
                sensorWrapper.start();
                break;
            case R.id.btnCrash:
                throw new RuntimeException("HaHaHa~~~");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sensorWrapper.stop();
    }
}

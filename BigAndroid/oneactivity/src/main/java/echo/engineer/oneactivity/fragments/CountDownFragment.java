package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragmentmaster.app.MasterFragment;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.widget.CountDownView;

/**
 * CountDownFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 6/26/17 2017 13:42.
 */

public class CountDownFragment extends MasterFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CountDownView countDownView = (CountDownView) view.findViewById(R.id.count_down_to_capture);
        view.findViewById(R.id.btnStart).setOnClickListener(v -> {
            if (countDownView.isCountingDown()) {
                countDownView.cancelCountDown();
            }
            countDownView.startCountDown(4, true);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }
}

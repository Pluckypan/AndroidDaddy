package echo.engineer.oneactivity.fragments;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.fragments.adapter.CountDownAdapter;
import echo.engineer.oneactivity.widget.CountDownView;
import echo.engineer.oneactivity.widget.PulseAlphaFramelayout;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * CountDownFragment
 * Created by Plucky<plucky@echo.engineer> on 6/26/17 2017 13:42.
 */

public class CountDownFragment extends MasterFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CountDownView countDownView = (CountDownView) view.findViewById(R.id.count_down_to_capture);
        PulseAlphaFramelayout pflRecording = (PulseAlphaFramelayout) view.findViewById(R.id.pflRecording);
        View vDot = view.findViewById(R.id.vDot);
        RecyclerView dockRecyclerView = (RecyclerView) view.findViewById(R.id.dockRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dockRecyclerView.setLayoutManager(layoutManager);
        dockRecyclerView.setAdapter(new CountDownAdapter());


        view.findViewById(R.id.btnStart).setOnClickListener(v -> {
            if (countDownView.isCountingDown()) {
                countDownView.cancelCountDown();
            }
            countDownView.startCountDown(4, false);
            pflRecording.setVisibility(View.VISIBLE);

            ValueAnimator valueAnimator = getValueAnimator();
            valueAnimator.addUpdateListener(animation -> {
                float val = animation.getAnimatedFraction();
                vDot.setAlpha(val);
            });
            valueAnimator.start();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getValueAnimator().cancel();
    }

    private ValueAnimator alphaAnimator;

    private ValueAnimator getValueAnimator() {
        if (alphaAnimator == null) {
            alphaAnimator = ValueAnimator.ofFloat(0, 1.0f);
            alphaAnimator.setDuration(1000);
            alphaAnimator.removeAllUpdateListeners();
            alphaAnimator.setRepeatCount(-1);
            alphaAnimator.setInterpolator(new TimeInterpolator() {
                @Override
                public float getInterpolation(float v) {
                    return (float) (Math.sin(2f * Math.PI * v - Math.PI / 2f) + 1) / 2f;
                }
            });
        }
        return alphaAnimator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }
}

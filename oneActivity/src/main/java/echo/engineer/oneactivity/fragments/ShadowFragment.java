package echo.engineer.oneactivity.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitomi.cslibrary.CrazyShadow;
import com.hitomi.cslibrary.base.CrazyShadowDirection;

import echo.engineer.oneactivity.R;
import engineer.echo.oneactivity.core.MasterFragment;

/**
 * ShadowFragment.java
 * Useage: ShadowFragment
 * Created by Plucky<plucky@echo.engineer> on 2017/9/4 - 9:46
 */

public class ShadowFragment extends MasterFragment {

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View vShadow = view.findViewById(R.id.vShadow);
        new CrazyShadow.Builder()
                .setContext(getContext())
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(6)
                .setCorner(10)
                .setBackground(Color.parseColor("#96a993"))
                .setImpl(CrazyShadow.IMPL_DRAW)
                .action(vShadow);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shadow, container, false);
    }

}

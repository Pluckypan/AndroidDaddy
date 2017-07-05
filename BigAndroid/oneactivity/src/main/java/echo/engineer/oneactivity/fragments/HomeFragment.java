package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragmentmaster.app.MasterFragment;
import com.fragmentmaster.app.Request;

import echo.engineer.oneactivity.MainActivity;
import echo.engineer.oneactivity.R;

/**
 * HomeFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 15:03.
 */

public class HomeFragment extends MasterFragment implements View.OnClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnOrderList).setOnClickListener(this);
        view.findViewById(R.id.btnHello).setOnClickListener(this);
        view.findViewById(R.id.btnWorld).setOnClickListener(this);
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
                ((MainActivity) getActivity()).sendMessage("hello");
                break;
            case R.id.btnWorld:
                ((MainActivity) getActivity()).sendMessage("world");
                break;
        }
    }
}

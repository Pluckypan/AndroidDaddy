package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fragmentmaster.app.MasterFragment;
import com.fragmentmaster.app.Request;
import echo.engineer.oneactivity.R;

/**
 * HomeFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 15:03.
 */

public class HomeFragment extends MasterFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(OrdersFragment.class);
                startFragment(request);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}

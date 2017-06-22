package echo.engineer.oneactivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fragmentmaster.animator.PageAnimator;
import com.fragmentmaster.annotation.Configuration;
import com.fragmentmaster.app.MasterFragment;
import com.fragmentmaster.app.MasterListFragment;
import com.fragmentmaster.app.Request;

import java.util.ArrayList;
import java.util.List;

import echo.engineer.oneactivity.Entry;
import echo.engineer.oneactivity.R;
import echo.engineer.oneactivity.pageanimator.Animators;

/**
 * OrdersFragment
 * Created by Plucky<plucky.pan@ubnt.com> on 6/22/17 2017 15:30.
 */

public class OrdersFragment extends MasterListFragment {
    private static final List<Entry> ENTRIES = new ArrayList<Entry>();

    static {
        ENTRIES.add(new Entry("StackAnimator", new Request(StackPage.class)));
        ENTRIES.add(new Entry("EnterOvershootAnimator", new Request(
                EnterOvershootPage.class)));
        ENTRIES.add(new Entry("VerticalSlideAnimator", new Request(
                VerticalSlidePage.class)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<Entry>(getActivity(),
                android.R.layout.simple_list_item_1, ENTRIES));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Entry page = ENTRIES.get(position);
        startFragment(page.mRequest);
    }

    @Configuration(theme = R.style.AppTheme_MasterFragment_Transparent)
    public static class VerticalSlidePage extends MasterFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_simple,
                    container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((TextView)view.findViewById(R.id.tvMsg)).setText("VerticalSlidePage");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setSlideable(false);
        }

        @Override
        public PageAnimator onCreatePageAnimator() {
            return Animators.VERTICAL_SLIDE_ANIMATOR;
        }
    }

    public static class EnterOvershootPage extends MasterFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_simple,
                    container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ((TextView)view.findViewById(R.id.tvMsg)).setText("EnterOvershootPage");
        }

        @Override
        public PageAnimator onCreatePageAnimator() {
            return Animators.ENTER_OVER_SHOOT_ANIMATOR;
        }
    }

    public static class StackPage extends MasterFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_simple,
                    container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ((TextView)view.findViewById(R.id.tvMsg)).setText("StackPage");
        }

        @Override
        public PageAnimator onCreatePageAnimator() {
            return Animators.STACK_ANIMATOR;
        }
    }
}

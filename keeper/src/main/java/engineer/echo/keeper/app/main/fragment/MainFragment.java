package engineer.echo.keeper.app.main.fragment;

import engineer.echo.keeper.R;
import engineer.echo.keeper.app.color.fragment.ColorFragment;
import engineer.echo.keeper.app.main.mvp.MainPresenter;
import engineer.echo.keeper.compts.mvp.AbstractMvpFragment;
import engineer.echo.keeper.app.main.mvp.MainContract;

import android.os.Bundle;
import android.view.View;

/**
 * MainFragment
 *
 * @author Plucky at 2018/01/14.
 */

public class MainFragment extends AbstractMvpFragment<MainContract.Presenter> implements MainContract.View, View.OnClickListener {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected MainContract.Presenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {
        view.findViewById(R.id.app_main_color_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_main_color_btn:
                startFragment(ColorFragment.class);
                break;
        }
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}

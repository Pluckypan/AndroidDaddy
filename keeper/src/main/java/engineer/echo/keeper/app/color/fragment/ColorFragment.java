package engineer.echo.keeper.app.color.fragment;

import engineer.echo.keeper.R;
import engineer.echo.keeper.app.color.mvp.ColorPresenter;
import engineer.echo.keeper.compts.mvp.AbstractMvpFragment;
import engineer.echo.keeper.app.color.mvp.ColorContract;

import android.os.Bundle;
import android.view.View;

/**
 * ColorFragment
 *
 * @author Plucky at 2018/01/14.
 */

public class ColorFragment extends AbstractMvpFragment<ColorContract.Presenter> implements ColorContract.View {
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
    protected ColorContract.Presenter providePresenter() {
        return new ColorPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_color;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {

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

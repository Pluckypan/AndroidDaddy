package engineer.echo.keeper.compts.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import engineer.echo.keeper.App;
import engineer.echo.keeper.compts.context.BaseFragment;

/**
 * AbstractMvpFragment
 *
 * @author Plucky at 2018/01/06.
 */

public abstract class AbstractMvpFragment<P extends IPresenter> extends BaseFragment implements IView {

    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG="AbstractMvpFragment";

    // ===========================================================
    // Fields
    // ===========================================================
    protected P mPresenter;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        mPresenter.attachView(this);
        App.LOG(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.LOG(TAG, "onCreateView()");
        return inflater.inflate(provideLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        App.LOG(TAG, "onViewCreated()");
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
        initData(savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroyView() {
        App.LOG(TAG, "onDestroyView()");
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        App.LOG(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        App.LOG(TAG, "onResume()");
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    /**
     * 设置Presenter
     *
     * @return IPresenter
     */
    protected abstract P providePresenter();

    /**
     * 设置布局文件
     *
     * @return ResId
     */
    protected abstract int provideLayout();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initViews(View view);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}

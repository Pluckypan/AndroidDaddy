package engineer.echo.keeper.compts.mvp;

/**
 * AbstractPresenter
 *
 * @author Plucky at 2018/01/06.
 */

public abstract class AbstractPresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    // ===========================================================
    // Constants
    // ===========================================================
    protected final String TAG = this.getClass().getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================
    protected V mView;
    protected M mModel;

    // ===========================================================
    // Override Methods
    // ===========================================================
    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    public boolean isViewAttached() {
        return mView != null;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}

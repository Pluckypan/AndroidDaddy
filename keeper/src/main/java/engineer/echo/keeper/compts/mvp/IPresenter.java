package engineer.echo.keeper.compts.mvp;

/**
 * IPresenter
 *
 * @author Plucky at 2018/01/06.
 */

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void subscribe();

    void unsubscribe();
}

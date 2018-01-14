package engineer.echo.keeper.app.main.mvp;

import engineer.echo.keeper.compts.mvp.AbstractPresenter;
import engineer.echo.keeper.compts.mvp.IModel;
import engineer.echo.keeper.compts.mvp.IView;

/**
 * MainContract
 *
 * @author Plucky at 2018/01/14.
 */

public class MainContract {

    public interface View extends IView {

    }

    public static abstract class Presenter extends AbstractPresenter<View, Model>{

    }

    public interface Model extends IModel {

    }
}

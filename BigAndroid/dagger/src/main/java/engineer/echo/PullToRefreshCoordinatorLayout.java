package engineer.echo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import engineer.echo.pulltorefresh.library.PullToRefreshBase;

/**
 * 分红全球购
 * 青岛芳林信息版权所有
 * author:Created by Plucky on 16/5/31-上午11:18.
 * 功能描述:Android-PullToRefresh RecycleView扩展
 */
public class PullToRefreshCoordinatorLayout extends PullToRefreshBase<CoordinatorLayout> {
    public PullToRefreshCoordinatorLayout(Context context) {
        super(context);
    }

    public PullToRefreshCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshCoordinatorLayout(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshCoordinatorLayout(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    @Override
    protected CoordinatorLayout createRefreshableView(Context context, AttributeSet attrs) {
        return new CoordinatorLayout(context, attrs);
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    private boolean isFirstItemVisible() {
        return false;
    }

    private boolean isLastItemVisible() {

        return false;
    }
}

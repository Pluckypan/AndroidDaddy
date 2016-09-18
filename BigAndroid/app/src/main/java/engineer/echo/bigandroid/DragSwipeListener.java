package engineer.echo.bigandroid;

/**
 * 分红全球购
 * 青岛芳林信息版权所有
 * author:Created by Plucky on 16/8/16-上午10:32.
 * 功能描述:
 */
public interface DragSwipeListener {
    void onItemDismiss(int position);

    void onDrag(int from, int to);
}

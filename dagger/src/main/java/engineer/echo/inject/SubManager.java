package engineer.echo.inject;

/**
 * SubManager
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/26 下午6:11.
 */

public class SubManager extends ChannelManager {
    public SubManager() {
    }

    @MustardChannel(value = {ChannelManager.CHANNEL_BLUETOOTH, ChannelManager.CHANNEL_TCP})
    public void getAplsit() {
        super.getBridge();
    }

    @MustardChannel(value = {ChannelManager.CHANNEL_TCP})
    public void getAplsitByName() {
        super.getBridge();
    }
}

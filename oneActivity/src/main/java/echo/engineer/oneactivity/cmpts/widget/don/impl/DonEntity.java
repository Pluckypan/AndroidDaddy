package echo.engineer.oneactivity.cmpts.widget.don.impl;

/**
 * DonEntity.java.java
 * Info: DonEntity.java
 * Created by Plucky<plucky@echo.engineer> on 2018/5/3 - 14:05
 * More about me: http://www.1991th.com
 */

public class DonEntity {
    int type;
    String title;
    String message;
    int icon;
    String cancel;
    String confirm;
    Runnable confirmAction;
    Runnable cancelAction;

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setConfirmAction(Runnable confirmAction) {
        this.confirmAction = confirmAction;
    }

    public void setCancelAction(Runnable cancelAction) {
        this.cancelAction = cancelAction;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getIcon() {
        return icon;
    }

    public String getCancel() {
        return cancel;
    }

    public String getConfirm() {
        return confirm;
    }

    public Runnable getConfirmAction() {
        return confirmAction;
    }

    public Runnable getCancelAction() {
        return cancelAction;
    }
}

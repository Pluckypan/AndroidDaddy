package engineer.echo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TranslatePosition
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/4/1 下午8:11.
 */

public class TranslatePosition implements Parcelable {
    public int startCenterX;
    public int startCenterY;
    public int toCenterX;
    public int toCenterY;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.startCenterX);
        dest.writeInt(this.startCenterY);
        dest.writeInt(this.toCenterX);
        dest.writeInt(this.toCenterY);
    }

    public TranslatePosition() {

    }

    public TranslatePosition(int startCenterX, int startCenterY, int toCenterX, int toCenterY) {
        this.startCenterX = startCenterX;
        this.startCenterY = startCenterY;
        this.toCenterX = toCenterX;
        this.toCenterY = toCenterY;
    }

    protected TranslatePosition(Parcel in) {
        this.startCenterX = in.readInt();
        this.startCenterY = in.readInt();
        this.toCenterX = in.readInt();
        this.toCenterY = in.readInt();
    }

    public static final Parcelable.Creator<TranslatePosition> CREATOR = new Parcelable.Creator<TranslatePosition>() {
        @Override
        public TranslatePosition createFromParcel(Parcel source) {
            return new TranslatePosition(source);
        }

        @Override
        public TranslatePosition[] newArray(int size) {
            return new TranslatePosition[size];
        }
    };
}

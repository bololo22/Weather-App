package model;

/**
 * Created by manolofernandez on 9/27/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results implements Parcelable
{

    @SerializedName("channel")
    @Expose
    private Channel channel;
    public final static Parcelable.Creator<Results> CREATOR = new Creator<Results>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return (new Results[size]);
        }

    }
            ;

    protected Results(Parcel in) {
        this.channel = ((Channel) in.readValue((Channel.class.getClassLoader())));
    }

    public Results() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(channel);
    }

    public int describeContents() {
        return 0;
    }

}

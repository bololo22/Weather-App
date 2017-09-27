
package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsList implements Parcelable
{

    @SerializedName("channel")
    @Expose
    private List<Channel> channel = null;
    public final static Creator<ResultsList> CREATOR = new Creator<ResultsList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ResultsList createFromParcel(Parcel in) {
            return new ResultsList(in);
        }

        public ResultsList[] newArray(int size) {
            return (new ResultsList[size]);
        }

    }
    ;

    protected ResultsList(Parcel in) {
        in.readList(this.channel, (Channel.class.getClassLoader()));
    }

    public ResultsList() {
    }

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(channel);
    }

    public int describeContents() {
        return  0;
    }

}

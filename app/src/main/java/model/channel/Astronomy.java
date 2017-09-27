
package model.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Astronomy implements Parcelable
{

    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    public final static Creator<Astronomy> CREATOR = new Creator<Astronomy>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Astronomy createFromParcel(Parcel in) {
            return new Astronomy(in);
        }

        public Astronomy[] newArray(int size) {
            return (new Astronomy[size]);
        }

    }
    ;

    protected Astronomy(Parcel in) {
        this.sunrise = ((String) in.readValue((String.class.getClassLoader())));
        this.sunset = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Astronomy() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
    }

    public int describeContents() {
        return  0;
    }

}

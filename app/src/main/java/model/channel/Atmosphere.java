
package model.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atmosphere implements Parcelable
{

    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("rising")
    @Expose
    private String rising;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    public final static Creator<Atmosphere> CREATOR = new Creator<Atmosphere>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Atmosphere createFromParcel(Parcel in) {
            return new Atmosphere(in);
        }

        public Atmosphere[] newArray(int size) {
            return (new Atmosphere[size]);
        }

    }
    ;

    protected Atmosphere(Parcel in) {
        this.humidity = ((String) in.readValue((String.class.getClassLoader())));
        this.pressure = ((String) in.readValue((String.class.getClassLoader())));
        this.rising = ((String) in.readValue((String.class.getClassLoader())));
        this.visibility = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Atmosphere() {
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(humidity);
        dest.writeValue(pressure);
        dest.writeValue(rising);
        dest.writeValue(visibility);
    }

    public int describeContents() {
        return  0;
    }

}

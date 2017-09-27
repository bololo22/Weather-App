
package model.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Units implements Parcelable
{

    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    public final static Creator<Units> CREATOR = new Creator<Units>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Units createFromParcel(Parcel in) {
            return new Units(in);
        }

        public Units[] newArray(int size) {
            return (new Units[size]);
        }

    }
    ;

    protected Units(Parcel in) {
        this.distance = ((String) in.readValue((String.class.getClassLoader())));
        this.pressure = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.temperature = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Units() {
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(distance);
        dest.writeValue(pressure);
        dest.writeValue(speed);
        dest.writeValue(temperature);
    }

    public int describeContents() {
        return  0;
    }

}

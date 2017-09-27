
package model.channel.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition implements Parcelable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("text")
    @Expose
    private String text;
    public final static Creator<Condition> CREATOR = new Creator<Condition>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        public Condition[] newArray(int size) {
            return (new Condition[size]);
        }

    }
    ;

    protected Condition(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.temp = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Condition() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(date);
        dest.writeValue(temp);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}

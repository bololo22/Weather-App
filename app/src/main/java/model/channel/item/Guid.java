
package model.channel.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guid implements Parcelable
{

    @SerializedName("isPermaLink")
    @Expose
    private String isPermaLink;
    public final static Creator<Guid> CREATOR = new Creator<Guid>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Guid createFromParcel(Parcel in) {
            return new Guid(in);
        }

        public Guid[] newArray(int size) {
            return (new Guid[size]);
        }

    }
    ;

    protected Guid(Parcel in) {
        this.isPermaLink = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Guid() {
    }

    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isPermaLink);
    }

    public int describeContents() {
        return  0;
    }

}

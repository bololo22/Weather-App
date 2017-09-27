package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manolofernandez on 9/27/17.
 */

public class ResponseList implements Parcelable
{

    @SerializedName("query")
    @Expose
    private Query query;
    public final static Parcelable.Creator<ResponseList> CREATOR = new Creator<ResponseList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseList createFromParcel(Parcel in) {
            return new ResponseList(in);
        }

        public ResponseList[] newArray(int size) {
            return (new ResponseList[size]);
        }

    }
            ;

    protected ResponseList(Parcel in) {
        this.query = ((Query) in.readValue((Query.class.getClassLoader())));
    }

    public ResponseList() {
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(query);
    }

    public int describeContents() {
        return  0;
    }

}
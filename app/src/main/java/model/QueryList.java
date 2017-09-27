package model;

/**
 * Created by manolofernandez on 9/27/17.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryList implements Parcelable
{

    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("results")
    @Expose
    private ResultsList results;
    public final static Creator<QueryList> CREATOR = new Creator<QueryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public QueryList createFromParcel(Parcel in) {
            return new QueryList(in);
        }

        public QueryList[] newArray(int size) {
            return (new QueryList[size]);
        }

    }
            ;

    protected QueryList(Parcel in) {
        this.count = ((long) in.readValue((long.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.results = ((ResultsList) in.readValue((ResultsList.class.getClassLoader())));
    }

    public QueryList() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ResultsList getResults() {
        return results;
    }

    public void setResults(ResultsList results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(created);
        dest.writeValue(lang);
        dest.writeValue(results);
    }

    public int describeContents() {
        return  0;
    }

}



package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query implements Parcelable
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
    private Results results;
    public final static Creator<Query> CREATOR = new Creator<Query>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        public Query[] newArray(int size) {
            return (new Query[size]);
        }

    }
    ;

    protected Query(Parcel in) {
        this.count = ((long) in.readValue((long.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.results = ((Results) in.readValue((Results.class.getClassLoader())));
    }

    public Query() {
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

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
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

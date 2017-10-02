package util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by manolofernandez on 9/29/17.
 */

public class Settings {
    public static final String PREFERENCES = "SharedPreferences";
    public static final String CITIES_KEY = "Cities";

    Activity mActivity;
    SharedPreferences settings;

    public Settings(Activity activity){
        this.mActivity = activity;
        settings = activity.getSharedPreferences(PREFERENCES, 0);
    }

    public void saveCity(String city){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CITIES_KEY, city);
        editor.commit();
    }

    public String loadCity(){
        return settings.getString(CITIES_KEY, null);
    }
}

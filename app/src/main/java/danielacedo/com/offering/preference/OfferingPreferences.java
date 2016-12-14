package danielacedo.com.offering.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by usuario on 14/12/16.
 */

/**
 * Manages preferences for showing certain offerings
 * @author Daniel Acedo Calderón
 */
public class OfferingPreferences {
    private static final String SHOWHOME_KEY ="home";
    private static final String SHOWELECTRONIC_KEY ="electronic";
    private static final String SHOWSPORT_KEY ="sport";
    private static final String SHOWWEIGHT_KEY ="weight";

    private static OfferingPreferences instance;
    private SharedPreferences preferences;

    public static OfferingPreferences getInstance(Context c){
        if (instance == null) {
            instance = new OfferingPreferences(c);
        }

        return instance;
    }

    private OfferingPreferences(Context c){
        preferences = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public void putShowHome(boolean value){
        preferences.edit().putBoolean(SHOWHOME_KEY, value).apply();
    }

    public void putShowElectronic(boolean value){
        preferences.edit().putBoolean(SHOWELECTRONIC_KEY, value).apply();
    }

    public void putShowSport(boolean value){
        preferences.edit().putBoolean(SHOWSPORT_KEY, value).apply();
    }

    public void putShowWeight(boolean value){
        preferences.edit().putBoolean(SHOWWEIGHT_KEY, value).apply();
    }

    public boolean getShowHome(){
        return preferences.getBoolean(SHOWHOME_KEY, false);
    }

    public boolean getShowElectronic(){
        return preferences.getBoolean(SHOWELECTRONIC_KEY, false);
    }

    public boolean getShowSport(){
        return preferences.getBoolean(SHOWSPORT_KEY, false);
    }

    public boolean getShowWeight(){
        return preferences.getBoolean(SHOWWEIGHT_KEY, false);
    }



}

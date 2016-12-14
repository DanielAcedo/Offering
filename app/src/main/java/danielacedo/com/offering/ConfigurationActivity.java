package danielacedo.com.offering;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import danielacedo.com.offering.model.Offering;
import danielacedo.com.offering.preference.OfferingPreferences;

/**
 * Activity used for changing settings regarding list visibility
 * @author Daniel Acedo Calderón
 */
public class ConfigurationActivity extends AppCompatActivity {

    private TextView txv_settingTitle;
    private CheckBox cb_showHome, cb_showElectronic, cb_showSport, cb_showWeight;
    private Button btn_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize Offering category name with resource array for making translation easier
        Offering.setCategoryNames(this);
        setContentView(R.layout.activity_configuration);

        txv_settingTitle = (TextView)findViewById(R.id.txv_configuration_title);

        Typeface ttf = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");

        if(ttf != null)
            txv_settingTitle.setTypeface(ttf);

        init_checkBox();

        btn_accept = (Button)findViewById(R.id.btn_settingAccept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! (cb_showHome.isChecked() || cb_showElectronic.isChecked() || cb_showSport.isChecked())){
                    Toast.makeText(ConfigurationActivity.this, R.string.err_categoryEmpty, Toast.LENGTH_SHORT).show();
                }else{
                    OfferingPreferences.getInstance(ConfigurationActivity.this).putShowHome(cb_showHome.isChecked());
                    OfferingPreferences.getInstance(ConfigurationActivity.this).putShowElectronic(cb_showElectronic.isChecked());
                    OfferingPreferences.getInstance(ConfigurationActivity.this).putShowSport(cb_showSport.isChecked());
                    OfferingPreferences.getInstance(ConfigurationActivity.this).putShowWeight(cb_showWeight.isChecked());

                    Intent intent = new Intent(ConfigurationActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void init_checkBox(){
        cb_showHome = (CheckBox)findViewById(R.id.cb_showHomeOffering);
        cb_showHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OfferingPreferences.getInstance(ConfigurationActivity.this).putShowHome(isChecked);
            }
        });

        cb_showElectronic = (CheckBox)findViewById(R.id.cb_showElectronicOffering);
        cb_showElectronic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OfferingPreferences.getInstance(ConfigurationActivity.this).putShowElectronic(isChecked);
            }
        });

        cb_showSport = (CheckBox)findViewById(R.id.cb_showSportOffering);
        cb_showSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OfferingPreferences.getInstance(ConfigurationActivity.this).putShowSport(isChecked);
            }
        });

        cb_showWeight = (CheckBox)findViewById(R.id.cb_showOfferingWeight);
        cb_showWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OfferingPreferences.getInstance(ConfigurationActivity.this).putShowWeight(isChecked);
            }
        });
    }
}

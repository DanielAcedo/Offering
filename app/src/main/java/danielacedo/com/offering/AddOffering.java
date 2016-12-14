package danielacedo.com.offering;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import danielacedo.com.offering.model.Offering;
import danielacedo.com.offering.repository.OfferingRepository;

/**
 * Activity used for adding new Offerings
 * @author Daniel Acedo Calderón
 */
public class AddOffering extends AppCompatActivity {


    private EditText edt_name, edt_shop, edt_date;
    private Spinner spn_category, spn_weight;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offering);

        edt_name = (EditText)findViewById(R.id.edt_offeringName);
        edt_shop = (EditText)findViewById(R.id.edt_offeringShop);
        edt_date = (EditText)findViewById(R.id.edt_offeringDate);

        spn_category = (Spinner)findViewById(R.id.spn_offeringCategory);
        spn_category.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.categories)));

        spn_weight = (Spinner)findViewById(R.id.spn_offeringWeight);
        spn_weight.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.offering_weight)));

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                int categoryPosition = spn_category.getSelectedItemPosition();

                Offering offer = new Offering(name, edt_shop.getText().toString(), edt_date.getText().toString(), spn_weight.getSelectedItemPosition(),
                        categoryPosition);

                List<Offering> offerings = OfferingRepository.getInstance().getOfferings();
                boolean error = false;

                for (Offering o: offerings) {
                    if(o.equals(offer)){
                        error = true;
                    }
                }

                if(error){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddOffering.this);

                    builder.setTitle(R.string.app_name).setMessage(R.string.err_addError)
                            .setPositiveButton(R.string.appinfo_positiveButton, null);

                    builder.create().show();
                }else{
                    OfferingRepository.getInstance().addOffering(offer);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}

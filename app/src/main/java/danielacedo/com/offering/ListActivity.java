package danielacedo.com.offering;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import danielacedo.com.offering.adapter.OfferingsAdapter;
import danielacedo.com.offering.model.Offering;
import danielacedo.com.offering.preference.OfferingPreferences;
import danielacedo.com.offering.repository.OfferingRepository;

/**
 * Activity containing a list of Offerings
 * @author Daniel Acedo Calderón
 */
public class ListActivity extends AppCompatActivity {

    public static final int ADD_OFFERING = 0;

    private ListView lv_offerings;
    private OfferingsAdapter adapter;
    private FloatingActionButton fab_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv_offerings = (ListView)findViewById(R.id.lv_offerings);
        selectShowCategoryAdapter();

        fab_add = (FloatingActionButton)findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddOffering.class);
                startActivityForResult(intent, ADD_OFFERING);
            }
        });

        registerForContextMenu(lv_offerings);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.ctx_offering, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
      super.onContextItemSelected(item);

        switch (item.getItemId()){
            case R.id.ctx_appinfo:
                showAppInfo();
                break;
        }

        return true;
    }

    private void showAppInfo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.appinfo_message_prefix)+" "+getResources().getString(R.string.author))
                .setTitle(R.string.app_name)
                .setPositiveButton(R.string.appinfo_positiveButton, null);

        builder.create().show();
    }

    private void selectShowCategoryAdapter(){
        List<Offering> offerings = new ArrayList<>();
        List<Offering> repositoryOfferings = new ArrayList<>(OfferingRepository.getInstance().getOfferings());


        for (Offering o: repositoryOfferings) {
            if(OfferingPreferences.getInstance(this).getShowElectronic() && o.getCategoryCode() == Offering.CATEGORY_ELECTRONIC){
                offerings.add(o);
            }

            if(OfferingPreferences.getInstance(this).getShowHome() && o.getCategoryCode() == Offering.CATEGORY_HOME){
                offerings.add(o);
            }

            if(OfferingPreferences.getInstance(this).getShowSport() && o.getCategoryCode() == Offering.CATEGORY_SPORT){
                offerings.add(o);
            }
        }

        adapter = new OfferingsAdapter(this, offerings);
        lv_offerings.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_offerings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.menu_sort_ASC:
                adapter.sortASC();
                break;
            case R.id.menu_sort_DESC:
                adapter.sortDesc();
                break;
            case R.id.menu_sort_category:
                adapter.sortCategory();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adapter.loadOffers();
    }
}

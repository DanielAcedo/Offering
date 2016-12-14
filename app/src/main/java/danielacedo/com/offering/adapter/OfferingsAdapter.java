package danielacedo.com.offering.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import danielacedo.com.offering.R;
import danielacedo.com.offering.model.Offering;
import danielacedo.com.offering.preference.OfferingPreferences;
import danielacedo.com.offering.repository.OfferingRepository;

/**
 * Created by usuario on 14/12/16.
 */

/**
 * Adapter for showing Offerings
 * @author Daniel Acedo Calderón
 */
public class OfferingsAdapter extends ArrayAdapter<Offering> {

    private boolean showWeight;

    public OfferingsAdapter(Context c, List<Offering> offerings){
        super(c, R.layout.offering_layout, new ArrayList<Offering>(offerings));

        showWeight = OfferingPreferences.getInstance(getContext()).getShowWeight();

        sort(Offering.SORT_ASC);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        OfferingHolder holder = null;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.offering_layout, null);
            holder = new OfferingHolder();

            holder.txv_name = (TextView)v.findViewById(R.id.txv_offeringName);
            holder.txv_date = (TextView)v.findViewById(R.id.txv_offeringDate);
            holder.txv_shop = (TextView)v.findViewById(R.id.txv_offeringShop);
            holder.imv_category = (ImageView)v.findViewById(R.id.imv_offeringCategory);

            v.setTag(holder);
        }else{
            holder = (OfferingHolder)v.getTag();
        }

        holder.txv_name.setText(getItem(position).getName());
        holder.txv_shop.setText(getItem(position).getShopName());
        holder.txv_date.setText(getItem(position).getDate());

        //Set category icon
        Drawable drawable = null;

        switch (getItem(position).getCategoryCode()){
            case Offering.CATEGORY_ELECTRONIC:
                drawable = getContext().getDrawable(R.mipmap.ic_mobile);
                break;
            case Offering.CATEGORY_SPORT:
                drawable = getContext().getDrawable(R.mipmap.ic_sports);
                break;
            case Offering.CATEGORY_HOME:
                drawable = getContext().getDrawable(R.mipmap.ic_home);
        }

        if (drawable != null) {
            holder.imv_category.setImageDrawable(drawable);
        }

        //Show offering weight color if selected
        if(showWeight){
            int color = -1;

            switch (getItem(position).getWeight()){
                case Offering.IMPORTANT:
                    color = getContext().getResources().getColor(R.color.color_important);
                    break;
                case Offering.NOT_IMPORTANT:
                    color = getContext().getResources().getColor(R.color.color_notImportant);
                    break;
                case Offering.VERY_IMPORTANT:
                    color = getContext().getResources().getColor(R.color.color_veryImportant);
                    break;
            }

            if (color != -1)
                v.setBackgroundColor(color);
        }


        return v;

    }

    public void sortASC(){
        sort(Offering.SORT_ASC);
        notifyDataSetChanged();
    }

    public void sortDesc(){
        sort(Offering.SORT_DESC);
        notifyDataSetChanged();
    }

    public void sortCategory(){
        sort(Offering.SORT_CATEGORY);
        notifyDataSetChanged();
    }

    public void loadOffers(){
        clear();

        List<Offering> offerings = new ArrayList<>();
        List<Offering> repositoryOfferings = new ArrayList<>(OfferingRepository.getInstance().getOfferings());


        for (Offering o: repositoryOfferings) {
            if(OfferingPreferences.getInstance(getContext()).getShowElectronic() && o.getCategoryCode() == Offering.CATEGORY_ELECTRONIC){
                offerings.add(o);
            }

            if(OfferingPreferences.getInstance(getContext()).getShowHome() && o.getCategoryCode() == Offering.CATEGORY_HOME){
                offerings.add(o);
            }

            if(OfferingPreferences.getInstance(getContext()).getShowSport() && o.getCategoryCode() == Offering.CATEGORY_SPORT){
                offerings.add(o);
            }
        }

        addAll(offerings);
    }


    public static class OfferingHolder{
        TextView txv_name, txv_date, txv_shop;
        ImageView imv_category;
    }
}

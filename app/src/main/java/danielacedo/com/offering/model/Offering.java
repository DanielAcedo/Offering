package danielacedo.com.offering.model;

/**
 * Created by usuario on 14/12/16.
 */

import android.content.Context;

import java.util.Comparator;

import danielacedo.com.offering.R;

/**
 * Class representing an Offering from a shop belonging to a categoryCode
 * @author Daniel Acedo Calderón
 */
public class Offering {
    private static String[] CATEGORY_NAMES;

    //WEIGHT
    public static final int VERY_IMPORTANT = 2;
    public static final int IMPORTANT = 1;
    public static final int NOT_IMPORTANT = 0;

    //CATEGORY
    public static final int CATEGORY_HOME = 0;
    public static final int CATEGORY_ELECTRONIC = 1;
    public static final int CATEGORY_SPORT = 2;

    public static final Comparator<Offering> SORT_ASC = new Comparator<Offering>() {
        @Override
        public int compare(Offering o1, Offering o2) {
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }
    };

    public static final Comparator<Offering> SORT_DESC = new Comparator<Offering>() {
        @Override
        public int compare(Offering o1, Offering o2) {
            return -1 * SORT_ASC.compare(o1, o2);
        }
    };

    public static final Comparator<Offering> SORT_CATEGORY = new Comparator<Offering>() {
        @Override
        public int compare(Offering o1, Offering o2) {
            if(o1.getCategoryCode() == o2.getCategoryCode()){
                return SORT_ASC.compare(o1, o2);
            }

            return o1.getCategoryName().toLowerCase().compareTo(o2.getCategoryName().toLowerCase());
        }
    };

    private String name;
    private String shopName;
    private String date;
    private int weight;
    private int categoryCode;
    private String categoryName;

    public Offering(String name, String shopName, String date, int weight, int category) {
        this.name = name;
        this.shopName = shopName;
        this.date = date;
        this.weight = weight;
        this.categoryCode = category;
        this.categoryName = CATEGORY_NAMES[categoryCode];
    }

    public static String[] getCategoryNames(){
        return CATEGORY_NAMES;
    }

    public static void setCategoryNames(Context context){
        CATEGORY_NAMES = context.getResources().getStringArray(R.array.categories);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Offering)
            return this.name.equals(((Offering)obj).getName()) && this.categoryCode == ((Offering)obj).getCategoryCode();

        return false;
    }
}

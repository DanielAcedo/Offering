package danielacedo.com.offering.repository;

import java.util.ArrayList;
import java.util.List;

import danielacedo.com.offering.model.Offering;

/**
 * Created by usuario on 14/12/16.
 */

/**
 * Repository for obtaining initial Offerings
 * @author Daniel Acedo Caldeŕon
 */
public class OfferingRepository {
    private static OfferingRepository repository;
    private List<Offering> offerings;

    public static OfferingRepository getInstance(){
        if (repository == null) {
            repository = new OfferingRepository();
        }

        return repository;
    }

    private OfferingRepository(){
        initializeList();
    }

    private void initializeList(){
        offerings = new ArrayList<>();

        offerings.add(new Offering("Macbook", "Apple Shop", "11/12/2016", Offering.IMPORTANT, Offering.CATEGORY_ELECTRONIC));
        offerings.add(new Offering("Monopatin", "Decathlon", "05/11/2017", Offering.NOT_IMPORTANT, Offering.CATEGORY_SPORT));
        offerings.add(new Offering("Playstation 4", "GAME", "04/05/2016", Offering.VERY_IMPORTANT, Offering.CATEGORY_ELECTRONIC));
        offerings.add(new Offering("Cortinas", "IKEA", "16/04/2016", Offering.NOT_IMPORTANT, Offering.CATEGORY_HOME));
        offerings.add(new Offering("Zapatillas running", "Decathlon", "15/09/2016", Offering.VERY_IMPORTANT, Offering.CATEGORY_SPORT));
        offerings.add(new Offering("i7 4770k", "PCComponentes", "01/03/2016", Offering.IMPORTANT, Offering.CATEGORY_ELECTRONIC));
        offerings.add(new Offering("Limpiacristales", "Alcampo", "10/07/2016", Offering.NOT_IMPORTANT, Offering.CATEGORY_HOME));
    }

    public List<Offering> getOfferings(){
        return offerings;
    }

    /**
     * Returns a list filtered by offering category
     * @param categoryCode Category code for filtering. Codes are available in Offering class
     * @return
     */
    public List<Offering> getCategoryOfferings(int categoryCode){
        List<Offering> offerings = new ArrayList<>(this.offerings);

        for (Offering o : offerings) {
            if(o.getCategoryCode() == categoryCode)
                offerings.add(o);
        }

        return offerings;
    }

    public void addOffering(Offering offering){
        offerings.add(offering);
    }
}

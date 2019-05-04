package degree.SmartBuildings.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


public class Product {
    @Id
    private String id;
    private String name;
    private List<Consumption> consumptions;

    protected Product(){this.consumptions = new ArrayList<>();}

    public Product(String name, List<Consumption> consumptions){
        this.name=name;
        this.consumptions = consumptions;
    }

    public String getId(){
        return id;
    }

    public String getName() {return name;}

    public List<Consumption> getConsumptions() { return consumptions;}
}

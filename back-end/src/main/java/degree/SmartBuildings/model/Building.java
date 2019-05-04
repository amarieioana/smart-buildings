package degree.SmartBuildings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Buildings")
public class Building {
    @Id
    private String id;
    private String name;
    private List<Product> products;

    protected Building() { this.products= new ArrayList<>(); }

    public Building(String name, List<Product> products){
        this.name=name;
        this.products = products;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public List<Product> getProducts() { return products; }
}

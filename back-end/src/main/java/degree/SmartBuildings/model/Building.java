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
    private String address;
    private Boolean active;
    private List<Product> products;

    protected Building() {
        this.products = new ArrayList<>();
    }

    public Building(String name, String address, Boolean active, List<Product> products) {
        this.name = name;
        this.address = address;
        this.active = active;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

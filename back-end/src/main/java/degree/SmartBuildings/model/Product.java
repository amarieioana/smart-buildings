package degree.SmartBuildings.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public String name;
    private List<Floor> floors;

    protected Product() {
        this.floors = new ArrayList<>();
    }

    public Product(String name, List<Floor> floors) {
        this.name = name;
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}

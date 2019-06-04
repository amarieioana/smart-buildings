package degree.SmartBuildings.model;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    public String floor;
    private List<Consumption> consumptions;

    protected Floor() {
        this.consumptions = new ArrayList<>();
    }

    public Floor(String floor, List<Consumption> consumptions) {
        this.floor = floor;
        this.consumptions = consumptions;
    }

    public String getFloor() {
        return floor;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }
}

package degree.SmartBuildings.model;

import java.time.LocalDateTime;

public class Consumption {

    private int consumedQuantity;
    private LocalDateTime supplyDate;

    protected Consumption() {
    }

    public Consumption(int consumedQuantity, LocalDateTime supplyDate) {
        this.consumedQuantity = consumedQuantity;
        this.supplyDate = supplyDate;
    }

    public int getConsumedQuantity() {
        return consumedQuantity;
    }

    public LocalDateTime getSupplyDate() {
        return supplyDate;
    }

}

package degree.SmartBuildings.model;

import java.time.LocalDateTime;

public class Consumption {

    private int consumedQuantity;
    private LocalDateTime supplyDate;
    private LocalDateTime endDate;

    protected Consumption() {
    }

    public Consumption(int consumedQuantity, LocalDateTime supplyDate, LocalDateTime endDate) {
        this.consumedQuantity = consumedQuantity;
        this.supplyDate = supplyDate;
        this.endDate = endDate;
    }

    public int getConsumedQuantity() {
        return consumedQuantity;
    }

    public LocalDateTime getSupplyDate() {
        return supplyDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}

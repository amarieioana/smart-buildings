package degree.SmartBuildings.model;

import java.time.LocalDateTime;

public class Consumption {

     private String floor;
     private int consumedQuantity;
     private LocalDateTime lastUpdate;

     protected Consumption() {
     }

     public Consumption(String floor, int consumedQuantity, LocalDateTime lastUpdate) {
         this.floor=floor;
         this.consumedQuantity=consumedQuantity;
         this.lastUpdate=lastUpdate;
     }

     public String getFloor(){ return floor; }

     public int getConsumedQuantity() { return consumedQuantity; }

     public LocalDateTime getLastUpdate() { return lastUpdate; }
}

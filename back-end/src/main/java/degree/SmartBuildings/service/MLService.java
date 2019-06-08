package degree.SmartBuildings.service;

import degree.SmartBuildings.model.Building;
import degree.SmartBuildings.model.Consumption;
import degree.SmartBuildings.model.Floor;
import degree.SmartBuildings.model.Product;
import degree.SmartBuildings.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MLService {

    @Autowired
    private BuildingRepository buildingRepository;


    public void exportMongoDbToCSV() throws IOException {
        try {
            FileWriter csvWriter = new FileWriter("../BuildingConsumptionPrediction/data/consumption.csv");
            csvWriter.append("BuildingId");
            csvWriter.append(",");
            csvWriter.append("Product");
            csvWriter.append(",");
            csvWriter.append("Floor");
            csvWriter.append(",");
            csvWriter.append("ConsumedQuantity");
            csvWriter.append(",");
            csvWriter.append("SupplyDate");
            csvWriter.append("\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            List<Building> buildings = (List<Building>) buildingRepository.findAll();
            for (int i = 0; i < buildings.size(); i++) {
                Building building = buildings.get(i);
                List<Product> products = building.getProducts();
                for (int j = 0; j < products.size(); j++) {
                    Product product = products.get(j);
                    List<Floor> floors = product.getFloors();
                    for (int k = 0; k < floors.size(); k++) {
                        Floor floor = floors.get(k);
                        List<Consumption> consumptions = floor.getConsumptions();
                        for (int l = 0; l < consumptions.size(); l++) {
                            Consumption consumption = consumptions.get(l);
                            csvWriter.append(building.getId());
                            csvWriter.append(",");
                            csvWriter.append(product.getName());
                            csvWriter.append(",");
                            csvWriter.append(floor.getFloor());
                            csvWriter.append(",");
                            csvWriter.append(String.valueOf(consumption.getConsumedQuantity()));
                            csvWriter.append(",");
                            csvWriter.append(consumption.getSupplyDate().format(formatter));
                            csvWriter.append("\n");
                        }
                    }
                }
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

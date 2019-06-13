package degree.SmartBuildings.service;

import degree.SmartBuildings.model.Building;
import degree.SmartBuildings.model.Consumption;
import degree.SmartBuildings.model.Floor;
import degree.SmartBuildings.model.Product;
import degree.SmartBuildings.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MLService {

    @Autowired
    private BuildingRepository buildingRepository;


    public void exportMongoDbToCSV() throws IOException {
        try {
            FileWriter csvWriter = new FileWriter("../NetApi/NetApi/data/consumption.csv");
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
            FileWriter csv = new FileWriter("../NetApi/NetApi/data/prediction.csv");
            csv.append("BuildingId");
            csv.append(",");
            csv.append("Product");
            csv.append(",");
            csv.append("Floor");
            csv.append(",");
            csv.append("ConsumedQuantity");
            csv.append(",");
            csv.append("SupplyDate");
            csv.append("\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            List<Building> buildings = buildingRepository.findAll();
            for (int i = 0; i < buildings.size(); i++) {
                Building building = buildings.get(i);
                List<Product> products = building.getProducts();
                for (int j = 0; j < products.size(); j++) {
                    Product product = products.get(j);
                    List<Floor> floors = product.getFloors();
                    for (int k = 0; k < floors.size(); k++) {
                        Floor floor = floors.get(k);
                        if (building.getActive()) {
                            csv.append(building.getId());
                            csv.append(",");
                            csv.append(product.getName());
                            csv.append(",");
                            csv.append(floor.getFloor());
                            csv.append(",");
                            csv.append(String.valueOf(0));
                            csv.append(",");
                            LocalDateTime now = LocalDateTime.now();
                            csv.append(formatter.format(now));
                            csv.append("\n");
                        }
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
                            csvWriter.append(formatter.format(consumption.getSupplyDate()));
                            csvWriter.append("\n");
                        }
                    }
                }
            }
            csvWriter.flush();
            csvWriter.close();
            csv.flush();
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPredictions(String predictions) {

        String[] predictionsArray=predictions.split("@");
        int index=0;
        String csvFile = "../NetApi/NetApi/data/prediction.csv";
        String line = "";
        String cvsSplitBy = ",";
        String message = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prediction = line.split(cvsSplitBy);

                message += "In " + buildingRepository.findById(prediction[0]).get().getName() + " building, at the " + prediction[2] + " floor, " +
                        predictionsArray[index] + " packs of " + prediction[1] + " are needed " + "\n";
                index++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (message.equals(""))
            message = "No building needs products";
        return message;
    }

    public String startMLAlgorithm() throws IOException {
        try {
            exportMongoDbToCSV();
            final String uri = "http://localhost:52199/algorithm";
            RestTemplate restTemplate = new RestTemplate();
            String predictions=restTemplate.getForObject(uri, String.class);
            return getPredictions(predictions);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

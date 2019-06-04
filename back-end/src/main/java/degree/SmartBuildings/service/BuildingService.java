package degree.SmartBuildings.service;

import degree.SmartBuildings.model.Building;
import degree.SmartBuildings.model.Consumption;
import degree.SmartBuildings.model.Floor;
import degree.SmartBuildings.model.Product;
import degree.SmartBuildings.repository.BuildingRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@Component
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    private final Integer STARTSHEETROW = 0;
    private final Map<String, Integer> excelMap = new HashMap<>();

    public String uploadConsumptions(String fileContent, String buildingId) {
        Building building = buildingRepository.findById(buildingId).get();
        InputStream fileContentStream = new ByteArrayInputStream(parseBase64Binary(fileContent));
        String fileErrors;
        try (Workbook workbook = WorkbookFactory.create(fileContentStream)) {
            populateMap();
            fileErrors = validateConsumptionFile(workbook);
            if (fileErrors.isEmpty())
                parseConsumptionFile(workbook, building);
        } catch (InvalidFormatException | IOException | NullPointerException e) {
            return "Bad file format";
        }
        return fileErrors;
    }

    private void populateMap() {
        excelMap.put("PRODUCT", 0);
        excelMap.put("FLOOR", 1);
        excelMap.put("CONSUMEDQUANTITY", 2);
        excelMap.put("SUPPLYDATE", 3);
        excelMap.put("ENDDATE", 4);
    }

    private String validateConsumptionFile(Workbook workbook) {
        List<Integer> rowErrors = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(STARTSHEETROW);
        for (Row row : sheet) {
            if (row.getRowNum() == 0)
                continue;
            if (!validateRow(row))
                rowErrors.add(row.getRowNum() + 1);
        }

        return rowErrors.isEmpty() ? "" : rowErrors.toString();
    }

    private Boolean validateRow(Row row) {
        final Boolean[] validRow = {Boolean.TRUE};
        IntStream.range(0, row.getLastCellNum()).forEach(colNum -> {
            if (row.getRowNum() != 0 && (getCellContent(row, colNum) == null || getCellContent(row, colNum).isEmpty())) {
                validRow[0] = Boolean.FALSE;
            }
        });
        return validRow[0];
    }

    private String getCellContent(Row row, Integer index) {
        Cell cell = row.getCell(index, CREATE_NULL_AS_BLANK);
        if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
            return null;
        }
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }

    private void parseConsumptionFile(Workbook workbook,Building building) {
        Sheet sheet = workbook.getSheetAt(STARTSHEETROW);
        for (Row row : sheet) {
            if (row.getRowNum() == 0)
                continue;
            addConsumptionEntry(row,building);
        }
    }

    private void addConsumptionEntry(Row row,Building building) {
        String product = getCellContent(row, excelMap.get("PRODUCT"));
        String floor = getCellContent(row, excelMap.get("FLOOR"));
        List<Product> result = building.getProducts().stream()
                .filter(item -> item.name.equals(product))
                .collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime supplyDate = LocalDateTime.parse(getCellContent(row,excelMap.get("SUPPLYDATE")), formatter);
        LocalDateTime endDate = LocalDateTime.parse(getCellContent(row,excelMap.get("ENDDATE")), formatter);
        if(result.isEmpty())
        {
            Consumption newConsumption= new Consumption(Integer.parseInt(getCellContent(row,(excelMap.get("CONSUMEDQUANTITY")))),supplyDate, endDate);
            List<Consumption> consumptions= new ArrayList<>();
            consumptions.add(newConsumption);
            Floor newFloor= new Floor(floor,consumptions);
            List<Floor> floors=new ArrayList<>();
            floors.add(newFloor);
            Product newProduct= new Product(product,floors);
            building.getProducts().add(newProduct);
        }
        else
        {
            Product updatedProduct= result.get(0);
            List<Floor> floorResult = updatedProduct.getFloors().stream()
                    .filter(item -> item.floor.equals(floor))
                    .collect(Collectors.toList());
            if(floorResult.isEmpty()){
                Consumption newConsumption= new Consumption(Integer.parseInt(getCellContent(row,(excelMap.get("CONSUMEDQUANTITY")))),supplyDate, endDate);
                List<Consumption> consumptions= new ArrayList<>();
                consumptions.add(newConsumption);
                Floor newFloor= new Floor(floor,consumptions);
                List<Product> allProducts= building.getProducts();
                allProducts.stream().filter(item-> item.name.equals(product)).findFirst().get().getFloors().add(newFloor);
                building.setProducts(allProducts);
            }
            else{
                Consumption newConsumption= new Consumption(Integer.parseInt(getCellContent(row,(excelMap.get("CONSUMEDQUANTITY")))),supplyDate, endDate);
                List<Floor> allFloors= building.getProducts().stream().filter(item-> item.name.equals(product)).findFirst().get().getFloors();
                allFloors.stream().filter(item->item.floor.equals(floor)).findFirst().get().getConsumptions().add(newConsumption);
                building.getProducts().stream().filter(item->item.name.equals(product)).findFirst().get().setFloors(allFloors);
            }
        }
        this.buildingRepository.save(building);
    }

//    private Candidate addConsumption(Row row, Faculty faculty,Campaign campaign) {
//        Candidate dbCandidate = candidateRepository.getByEmailAndCampaign(getCellContent(row, excelMap.get("EMAIL")),campaign);
//
//        if (dbCandidate == null) {
//            dbCandidate = new Candidate();
//            dbCandidate.setCampaign(campaign);
//            dbCandidate.setFaculty(faculty);
//            dbCandidate.setAlumniCandidate(Boolean.valueOf(getCellContent(row, excelMap.get("ALUMNI"))));
//            dbCandidate.setInternalCandidate(Boolean.valueOf(getCellContent(row, excelMap.get("INTERNALCANDIDATE"))));
//            dbCandidate.setEmail(getCellContent(row, excelMap.get("EMAIL")));
//            dbCandidate.setPhone(getCellContent(row, excelMap.get("PHONE")));
//            dbCandidate.setFirstName(getCellContent(row, excelMap.get("FIRSTNAME")));
//            dbCandidate.setLastName(getCellContent(row, excelMap.get("LASTNAME")));
//            dbCandidate.setStudyYear(Integer.parseInt(getCellContent(row, excelMap.get("STUDYYEAR"))));
//            candidateRepository.add(dbCandidate);
//        }
//
//        return dbCandidate;
//    }
}

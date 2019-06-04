package degree.SmartBuildings.controller;

import degree.SmartBuildings.model.Building;
import degree.SmartBuildings.repository.BuildingRepository;
import degree.SmartBuildings.service.BuildingService;
import degree.SmartBuildings.service.FileService;
import degree.SmartBuildings.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MLService mlService;

    public BuildingController(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @GetMapping("buildings/all")
    public List<Building> getAll() throws IOException {
        List<Building> buildings = this.buildingRepository.findByActive(true);

        return buildings;
    }

    @GetMapping("buildings/{id}")
    public Optional<Building> getById(@PathVariable("id") String id) {
        Optional<Building> building = this.buildingRepository.findById(id);
        return building;
    }

    @PutMapping
    public void insert(@RequestBody Building building) {
        this.buildingRepository.insert(building);
    }

    @PostMapping("add-building")
    public void update(@RequestBody Building building) {
        this.buildingRepository.save(building);
    }

    @DeleteMapping("buildings/{id}")
    public void delete(@PathVariable("id") String id) {
        Building building = this.buildingRepository.findById(id).get();
        building.setActive(false);
        this.buildingRepository.save(building);
    }

    @PostMapping("buildings")
    public ResponseEntity<String> uploadConsumptions(@RequestBody String fileContent, @RequestParam("id") String buildingId) {
        String fileErrors = buildingService.uploadConsumptions(fileContent, buildingId);
        if (fileErrors.isEmpty())
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(fileErrors, HttpStatus.OK);
    }

    @GetMapping(value = "download/{filename}", produces = "text/csv; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public Resource getFileFromFileSystem(@PathVariable String filename, HttpServletResponse response) {
        return fileService.getFileSystem(filename, response);
    }
}

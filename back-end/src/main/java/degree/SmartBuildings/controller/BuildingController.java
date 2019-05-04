package degree.SmartBuildings.controller;

import degree.SmartBuildings.model.Building;
import degree.SmartBuildings.repository.BuildingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingRepository buildingRepository;

    public BuildingController(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @GetMapping("/all")
    public List<Building> getAll(){
        List<Building> buildings = this.buildingRepository.findAll();

        return buildings;
    }

    @GetMapping("/{id}")
    public Optional<Building> getById(@PathVariable("id") String id){
        Optional<Building> building = this.buildingRepository.findById(id);
        return building;
    }

    @PutMapping
    public void insert(@RequestBody Building building){
        this.buildingRepository.insert(building);
    }

    @PostMapping
    public void update(@RequestBody Building building){
        this.buildingRepository.save(building);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") String id){
//        this.buildingRepository.delete(id);
//    }
}

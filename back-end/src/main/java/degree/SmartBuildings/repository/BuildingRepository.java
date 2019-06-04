package degree.SmartBuildings.repository;

import degree.SmartBuildings.model.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends MongoRepository<Building, String> {

    Optional<Building> findById(String id);

    List<Building> findByActive(Boolean active);
}

package degree.SmartBuildings.model;

import degree.SmartBuildings.repository.BuildingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private BuildingRepository buildingRepository;

    public DbSeeder(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }
    @Override
    public void run(String... strings) throws Exception{
        Building build1= new Building(
                "building1",
                Arrays.asList((new Product(
                        "cafea",
                        Arrays.asList(
                                new Consumption("1st floor", 2,LocalDateTime.now()),
                                new Consumption("2nd floor",3, LocalDateTime.now())
                        ))),
                        new Product(
                                "ceai",
                                Arrays.asList(
                                        new Consumption("1st floor",1, LocalDateTime.now()),
                                        new Consumption("2nd floor",3, LocalDateTime.now())
                                )
                        ),
                        new Product(
                                "zahar",
                                Arrays.asList(
                                        new Consumption("2nd floor",2, LocalDateTime.now())
                                )

                        )
                ));
        Building build2= new Building(
                "UBC",
                Arrays.asList((new Product(
                                "cafea",
                                Arrays.asList(
                                        new Consumption("4st floor", 5,LocalDateTime.now()),
                                        new Consumption("5nd floor",1, LocalDateTime.now())
                                ))),
                        new Product(
                                "zahar",
                                Arrays.asList(
                                        new Consumption("4nd floor",2, LocalDateTime.now())
                                )

                        )
                ));
        Building build3= new Building(
                "E5",
                Arrays.asList((new Product(
                                "cafea",
                                Arrays.asList(
                                        new Consumption("1st floor", 2,LocalDateTime.now()),
                                        new Consumption("2nd floor",3, LocalDateTime.now())
                                )))
                ));

        //drop all buildings
        this.buildingRepository.deleteAll();

        //add our buildings to the database
        List<Building> buildings = Arrays.asList(build1,build2,build3);
        this.buildingRepository.save(build1);
        this.buildingRepository.save(build2);
        this.buildingRepository.save(build3);

    }
}

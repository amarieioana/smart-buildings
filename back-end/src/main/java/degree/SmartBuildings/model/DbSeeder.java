package degree.SmartBuildings.model;

import degree.SmartBuildings.repository.BuildingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@Component
//public class DbSeeder implements CommandLineRunner {
//
//    private BuildingRepository buildingRepository;
//
//    public DbSeeder(BuildingRepository buildingRepository){
//        this.buildingRepository = buildingRepository;
//    }
//    @Override
//    public void run(String... strings) throws Exception{
//        Building build1= new Building(
//                "building1",
//                "Str Cuza Voda, nr 5",
//                true,
//                Arrays.asList(new Product(
//                        "cafea",
//                        Arrays.asList(new Floor(
//                                "1st floor",
//                                Arrays.asList(
//                                        new Consumption( 2,LocalDateTime.now(),LocalDateTime.now()),
//                                        new Consumption(3, LocalDateTime.now(),LocalDateTime.now())
//                                )
//                        ),
//                                new Floor(
//                                        "2nd floor",
//                                        Arrays.asList(
//                                                new Consumption( 1,LocalDateTime.now(),LocalDateTime.now()),
//                                                new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                        )
//                                ),
//                                new Floor(
//                                        "3rd floor",
//                                        Arrays.asList(
//                                                new Consumption( 2,LocalDateTime.now(),LocalDateTime.now()),
//                                                new Consumption(2, LocalDateTime.now(),LocalDateTime.now())
//                                        )
//                                )
//                        )),
//                        new Product(
//                                "ceai",
//                                Arrays.asList(new Floor(
//                                                "4th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 1,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(2, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        ),
//                                        new Floor(
//                                                "5th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 3,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        )
//                                )
//                        ),
//                        new Product(
//                                "zahar",
//                                Arrays.asList(new Floor(
//                                                "4th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 5,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        )
//                        )
//                )));
//        Building build2= new Building(
//                "UBC",
//                "Str Palat, nr 2",
//                true,
//                Arrays.asList(new Product(
//                                "cafea",
//                                Arrays.asList(new Floor(
//                                                "4th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 4,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(2, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        ),
//                                        new Floor(
//                                                "5th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 3,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        )
//                                )),
//                        new Product(
//                                "zahar",
//                                Arrays.asList(new Floor(
//                                                "4th floor",
//                                                Arrays.asList(
//                                                        new Consumption( 5,LocalDateTime.now(),LocalDateTime.now()),
//                                                        new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                                )
//                                        )
//                        )
//                )));
//        Building build3= new Building(
//                "E5",
//                "Str Palat, nr 1",
//                true,
//                Arrays.asList(new Product(
//                                "cafea",
//                        Arrays.asList(new Floor(
//                                        "1st floor",
//                                        Arrays.asList(
//                                                new Consumption( 1,LocalDateTime.now(),LocalDateTime.now()),
//                                                new Consumption(2, LocalDateTime.now(),LocalDateTime.now())
//                                        )
//                                ),
//                                new Floor(
//                                        "2nd floor",
//                                        Arrays.asList(
//                                                new Consumption( 3,LocalDateTime.now(),LocalDateTime.now()),
//                                                new Consumption(4, LocalDateTime.now(),LocalDateTime.now())
//                                        )
//                                )
//                        )
//                        )
//                ));
//
//        //drop all buildings
//        this.buildingRepository.deleteAll();
//
//        //add our buildings to the database
//        List<Building> buildings = Arrays.asList(build1,build2,build3);
//        this.buildingRepository.save(build1);
//        this.buildingRepository.save(build2);
//        this.buildingRepository.save(build3);
//
//    }
//}

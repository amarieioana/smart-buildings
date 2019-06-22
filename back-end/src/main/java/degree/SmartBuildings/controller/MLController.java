package degree.SmartBuildings.controller;


import degree.SmartBuildings.dto.Prediction;
import degree.SmartBuildings.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MLController {

    @Autowired
    MLService mlService;

    @GetMapping(value = "/algorithm")
    public String startMLAlgoritm() throws IOException {
        return mlService.startMLAlgorithm();
    }

    @PostMapping(value = "/singlePrediction")
    public String singlePrediction(@RequestBody Prediction prediction) throws IOException {
        return mlService.getSinglePrediction(prediction.getBuildingId(), prediction.getProduct(),prediction.getFloor());
    }

}

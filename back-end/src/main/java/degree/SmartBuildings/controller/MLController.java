package degree.SmartBuildings.controller;


import degree.SmartBuildings.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

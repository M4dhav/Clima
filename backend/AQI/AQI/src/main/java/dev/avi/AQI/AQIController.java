package dev.avi.AQI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

@RestController
public class AQIController {

    @Autowired
    private AqiService aqiService;

    @GetMapping("/")
    public ResponseEntity<String> getHello() {  
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/aqi/{city}")
    public ResponseEntity<String> getWords(@PathVariable String city) {
        JSONObject result = aqiService.getAqiData(city);

        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
}

package dev.avi.AQI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

class AqiData {
    private String city;
    private String state;
    private String lastUpdate;
    private String pollutantMin;
    private String pollutantAvg;
    private String pollutantMax;

    public AqiData() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPollutantMin() {
        return pollutantMin;
    }

    public void setPollutantMin(String pollutantMin) {
        this.pollutantMin = pollutantMin;
    }

    public String getPollutantAvg() {
        return pollutantAvg;
    }

    public void setPollutantAvg(String pollutantAvg) {
        this.pollutantAvg = pollutantAvg;
    }

    public String getPollutantMax() {
        return pollutantMax;
    }

    public void setPollutantMax(String pollutantMax) {
        this.pollutantMax = pollutantMax;
    }

    @Override
    public String toString() {
        return "City: " + this.getCity() + "    AQI: " + this.getPollutantAvg() + "     Last Updated: " + this.getLastUpdate();
    }
}



@Service
public class AqiService {

    private static final String API_KEY = "579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b";
    private static final String BASE_URL = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key="
            + API_KEY + "&format=json&offset=0";

    public JSONObject getAqiData(String city) {

        if (city != null && city.length() > 0) {
            char firstChar = Character.toUpperCase(city.charAt(0));
            city = firstChar + city.substring(1);
        }

        String url = BASE_URL +"&filters[city]="+city;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // fetching response
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject records = (JSONObject) jsonObject.getJSONArray("records").get(0);

        // Return the JSON response as is
        return records;
    }
}

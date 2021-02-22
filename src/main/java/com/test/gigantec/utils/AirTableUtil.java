package com.test.gigantec.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@Component
public class AirTableUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${airtable.apiKey}")
    private String apikey;

    @Value("${airtable.url}")
    private String url;

    @Value("${airtable.tableName}")
    private String TABLE_NAME;

    public Map<String, Object> createDataAirTable(Map<String, Object> map) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(url + "/" + TABLE_NAME);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apikey);
        HttpEntity<Map> body = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(componentBuilder.build().toUri(), body, Map.class);
            if (response.getStatusCodeValue() == 200) {
                return response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> updateDataAirTable(Map<String, Object> map) {
        if (map == null) {
            return null;
        } else {
            UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(url + "/" + TABLE_NAME);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apikey);
            HttpEntity<Map> body = new HttpEntity<>(map, headers);
            try {
                ResponseEntity<Map> response = restTemplate.exchange(componentBuilder.build().toUri(), HttpMethod.PUT, body, Map.class);
                if (response.getStatusCodeValue() == 200) {
                    List<Object> records = (List<Object>) response.getBody().get("records");
                    return (Map<String, Object>) records.get(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

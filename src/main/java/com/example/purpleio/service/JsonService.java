package com.example.purpleio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonService {

    public JSONObject getJsonObj(String oEmbedUrl) {
        JSONObject object = new JSONObject();
        try {
            URL url = new URL(oEmbedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();

            String result = sb.toString();

            JSONParser parser = new JSONParser();
            object = (JSONObject) parser.parse(result);
        } catch (IOException | ParseException e) {
            log.warn("Parsing Error={}", e.toString());
            e.printStackTrace();
            return null;
        }
        return object;
    }

}

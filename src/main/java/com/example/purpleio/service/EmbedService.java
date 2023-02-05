package com.example.purpleio.service;

import com.example.purpleio.service.json.JsonHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmbedService {

    private final JsonHandlerService jsonHandlerService;

    public static final String YOUTUBE = "youtube";

    public JSONObject urlParser(String url) {
        if (url.contains(YOUTUBE)) {
            return youtubeHandler(url);
        } else {
            return null;
        }
    }

    public JSONObject youtubeHandler(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.youtube.com/oembed?url=https://youtube.com/watch?v=");
        sb.append(url.split("watch\\?v=")[1]);
        sb.append("&format=json");

        String jsonData = getJsonObject(sb.toString());

        return jsonHandlerService.youtubeJson(jsonData);
    }

    private String getJsonObject(String result) {
        return jsonHandlerService.getData(result);

    }

}

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
    public static final String TWITTER = "twitter";
    public static final String INSTAGRAM = "instagram";
    public static final String VIMEO = "vimeo";

    public JSONObject urlParser(String url) {
        StringBuilder sb = new StringBuilder();

        if (url.contains(YOUTUBE)) {
            sb.append("https://www.youtube.com/oembed?url=https://youtube.com/watch?v=");
            sb.append(url.split("watch\\?v=")[1]);
            sb.append("&format=json");
        } else if (url.contains(TWITTER)) {
            sb.append("https://publish.twitter.com/oembed?url=https://twitter.com/");
            sb.append(url.split("twitter.com/")[1]);
            sb.append("&format=json");
        } else if (url.contains(VIMEO)) {
            sb.append("https://vimeo.com/api/oembed.json?url=");
            sb.append(url);
        } else if (url.contains(INSTAGRAM)) {
            sb.append("https://api.instagram.com/oembed?omitscript=true&url=https://www.instagram.com/p/");
            sb.append(url.split("/p/")[1]);
            sb.append("&format=json");
        }

        String jsonData = getJsonObject(sb.toString());

        return jsonHandlerService.jsonParsing(jsonData);
    }

    private String getJsonObject(String result) {
        return jsonHandlerService.getData(result);
    }

}

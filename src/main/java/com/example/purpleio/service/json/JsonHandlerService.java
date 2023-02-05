package com.example.purpleio.service.json;

import com.example.purpleio.service.EmbedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonHandlerService {

    public String[] keyInit(String platform) {
        return switch (platform) {
            case EmbedService.YOUTUBE ->
                    new String[]{"title", "author_name", "author_url", "type", "height", "width", "version", "provider_name", "provider_url", "thumbnail_height", "thumbnail_width", "thumbnail_url", "html"};
            case EmbedService.TWITTER ->
                    new String[]{"title", "html", "width", "height", "type", "cache_age", "provider_name", "provider_url", "version"};
            case EmbedService.VIMEO ->
                    new String[]{"type", "version", "provider_name", "provider_url", "title", "author_name", "author_url", "is_plus", "account_type", "html", "width", "height", "duration", "description", "thumbnail_url", "thumbnail_width", "thumbnail_height", "thumbnail_url_with_play_button"};
            default -> new String[]{"null"};
        };
    }

    public JSONObject jsonParsing(String data){
        String platform = "";
        if (data.contains(EmbedService.YOUTUBE)) {
            platform = EmbedService.YOUTUBE;
        } else if (data.contains(EmbedService.TWITTER)) {
            platform = EmbedService.TWITTER;
        } else if (data.contains(EmbedService.VIMEO)) {
            platform = EmbedService.VIMEO;
        } else {
            return null;
        }
        JSONObject jsonobj = new JSONObject();
        String[] key = keyInit(platform);
        data=data.replace("{","");
        jsonobj.put("url", platform);
        for(int i=0; i<key.length; i++){
            if(i== key.length-1){
                int start = data.indexOf(key[i]);
                int end= data.indexOf("}");
                String value = data.substring(start + key[i].length() + 3, end - 1);
                jsonobj.put(key[i], value);
            }
            else {
                int start = data.indexOf(key[i]);
                int end = data.indexOf(key[i + 1]);
                String value = data.substring(start+key[i].length()+2, end);
                value = value.replace(",", "");
                value = value.replace("\"", "");
                jsonobj.put(key[i], value);
            }

        }

        return jsonobj;

    }

    public String getData(String url) {
        log.info("url = {}", url);
        String data = null;
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-type", "application/json");
            conn.setDoOutput(true);

            try{
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                while(br.ready()) {
                    sb.append(br.readLine());
                }
                data = convertString(String.valueOf(sb));
                log.info("data={}", data);

            }catch(Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public  String convertString(String val) {
        log.info("val = {}", val);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < val.length(); i++) {
            if ('\\' == val.charAt(i) && 'u' == val.charAt(i + 1)) {
                Character r = (char) Integer.parseInt(val.substring(i + 2, i + 6), 16);
                sb.append(r);
                i += 5;
            } else {
                sb.append(val.charAt(i));
            }
        }
        String result = sb.toString().replaceAll("\\\\/", "/").replaceAll("=\\\\", "=").replaceAll("\\\\\"", "\"");

        log.info("after val = {}", sb.toString());
        log.info("after result = {}", result);
        return result;
    }

}

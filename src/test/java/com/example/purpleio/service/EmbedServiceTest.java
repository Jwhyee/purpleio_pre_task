package com.example.purpleio.service;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmbedServiceTest {

    @Autowired
    private EmbedService embedService;
    @Autowired
    private JsonService jsonService;

    @Test
    void getYoutubeUrlTest() {
        String url = "https://www.youtube.com/watch?v=t8LQnUSBqe8&ab_channel=Avocado%F0%9F%A5%91forHaerin";
        JSONObject object = embedService.urlParser(url);
        System.out.println("object = " + object.toJSONString());
    }

    @Test
    void jsonParsingTest() {
        String videoUrl = "https://www.youtube.com/oembed?url=https://youtube.com/watch?v=t8LQnUSBqe8&ab_channel=Avocado%F0%9F%A5%91forHaerin&format=json";

        JSONObject object = jsonService.getJsonObj(videoUrl);

        assertThat(object.get("type")).isEqualTo("video");
        assertThat(object.get("provider_name")).isEqualTo("YouTube");

        System.out.println("object = " + object);
        System.out.println("provider_url = " + object.get("provider_url"));
        System.out.println("author_name = " + object.get("author_name"));
        System.out.println("author_url = " + object.get("author_url"));
        System.out.println("type = " + object.get("type"));
        System.out.println("height = " + object.get("height"));
        System.out.println("width = " + object.get("width"));
        System.out.println("version = " + object.get("version"));
        System.out.println("provider_name = " + object.get("provider_name"));
        System.out.println("provider_url = " + object.get("provider_url"));
        System.out.println("thumbnail_height = " + object.get("thumbnail_height"));
        System.out.println("thumbnail_width = " + object.get("thumbnail_width"));
        System.out.println("thumbnail_url = " + object.get("thumbnail_url"));
        System.out.println("html = " + object.get("html"));
    }

}
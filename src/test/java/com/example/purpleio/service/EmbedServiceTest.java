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

    @Test
    void getYoutubeUrlTest() {
        String url = "https://www.youtube.com/watch?v=t8LQnUSBqe8&ab_channel=Avocado%F0%9F%A5%91forHaerin";
        JSONObject object = embedService.youtubeHandler(url);
        System.out.println("object = " + object.toJSONString());
    }

}
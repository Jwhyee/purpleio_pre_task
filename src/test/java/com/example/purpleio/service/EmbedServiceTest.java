package com.example.purpleio.service;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    @Test
    void jsonParsing() throws ParseException, JSONException {
        String url = """
                {"title":"221224 SBS 가요대전 뉴진스(newjeans) - Tell me 해린 FOCUS CAM","author_name":"Avocado🥑 for Haerin","author_url":"https://www.youtube.com/@avocado0515hr","type":"video","height":113,"width":200,"version":"1.0","provider_name":"YouTube","provider_url":"https://www.youtube.com/","thumbnail_height":360,"thumbnail_width":480,"thumbnail_url":"https://i.ytimg.com/vi/t8LQnUSBqe8/hqdefault.jpg","html":"<iframe width="200" height="113" src="https://www.youtube.com/embed/t8LQnUSBqe8?feature=oembed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen title="221224 SBS 가요대전 뉴진스(newjeans) - Tell me 해린 FOCUS CAM"></iframe>"}
                """;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(url);
        System.out.println("obj = " + jsonObject);
    }

}
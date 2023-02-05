package com.example.purpleio.web;

import com.example.purpleio.service.EmbedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmbedRestController {

    private final EmbedService embedService;

    @GetMapping("/search")
    public JSONObject doSearch(HttpServletRequest rq) {
        String url = rq.getParameter("url");
        log.info("url={}", url);
        JSONObject data = embedService.urlParser(url);
        log.info("data={}", data);
        return data;
    }
}

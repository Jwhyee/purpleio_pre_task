package com.example.purpleio.web;

import com.example.purpleio.service.EmbedService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class EmbedRestController {

    private final EmbedService embedService;

    @GetMapping("/search")
    public JSONObject doSearch(HttpServletRequest rq) {
        String url = rq.getParameter("url");
        JSONObject data = embedService.urlParser(url);
        return data;
    }
}

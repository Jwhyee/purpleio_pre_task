package com.example.purpleio.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class EmbedRestController {
    @GetMapping("/search")
    public boolean doSearch(HttpServletRequest rq) {
        log.info("url={}", rq.getParameter("url"));
        return true;
    }
}

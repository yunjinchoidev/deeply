package com.server.deeply;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Slf4j
public class SampleController {

    @GetMapping("/sample")
    public String sample() {
        return "sample";
    }
}

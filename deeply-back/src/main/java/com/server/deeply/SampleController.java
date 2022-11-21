package com.server.deeply;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@Slf4j
@RestController
public class SampleController {

    @GetMapping("/sample")
    public String sample() {
        return "sample";
    }
}

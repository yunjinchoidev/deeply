package com.server.deeply;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class thymeController {

    @GetMapping("/sample/more")
    public void sample(Model model) {
        model.addAttribute("message", "Hello, SnowDeer.");
    }
}

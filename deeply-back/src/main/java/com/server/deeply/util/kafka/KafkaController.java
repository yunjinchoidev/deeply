//package com.server.deeply.util.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class KafkaController {
//
//    private final KafkaProducer kafkaProducer;
//
//    @PostMapping("/kafka")
//    public String sendMsg(@RequestParam("message") String message) {
//        kafkaProducer.sendMessgae(message);
//        return "Successful Sending!!";
//    }
//}
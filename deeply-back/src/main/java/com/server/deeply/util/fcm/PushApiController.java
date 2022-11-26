package com.server.deeply.util.fcm;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PushApiController {

    private final FCMService fcmService;

    /**
     * 푸시 알람 요청
     * @param title
     * @param body
     * @throws IOException
     */
    @PostMapping("/fcm")
    public ResponseEntity<?> reqFcm(
            @RequestParam(required = true) String token,
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String body
    ) {

        log.info("** title : {}",title);
        log.info("** body : {}",body);

        CommResponse res = new CommResponse();

        try {

            fcmService.sendMessageTo(token, title, body);
            res.setCdResult("SUCCESS");

        } catch(Exception e) {
            log.error(e.getMessage());
            res.setCdResult("FAILED");
            res.setMsgResult("처리중 에러 발생");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(res);
    }

    @Data
    public class CommResponse {
        public String cdResult;
        public String msgResult;


    }

}

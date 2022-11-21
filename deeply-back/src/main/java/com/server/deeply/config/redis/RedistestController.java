package com.server.deeply.config.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RedistestController {

    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshRedisRepository refreshRedisRepository;

    @PostMapping("/redis/data")
    public ResponseEntity<String> setRedisData(
        @RequestBody(required = true) Map<String,String> map) throws Exception{
        RefreshRedisToken refreshRedisToken = RefreshRedisToken.builder().token(map.get("data")).build();
refreshRedisRepository.save(refreshRedisToken);
//            redisTemplate.opsForValue().set(map.get("key"), map.get("value"));

        return new ResponseEntity<>("정상 등록", HttpStatus.CREATED);
    }

    @GetMapping("/redis/data")
    public ResponseEntity<String> getRedisData(
        @RequestParam(required = true) String key){

        return new ResponseEntity<>(redisTemplate.opsForValue().get(key), HttpStatus.OK);

    }

}
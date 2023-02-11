package com.example.ubuntutestserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/api/test")
@Slf4j
public class MainController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> main() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "HIHI");
        try {
            Thread.sleep(500);
            return ResponseEntity.ok(result);
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestParam("message") String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", message == null ? "HI~" : message);
        return ResponseEntity.ok(result);
    }

}

package com.example.ubuntutestserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EnvironmentController {

    @Value("${env.podName:NONE}")
    public String podName;

    @GetMapping("/api/environments/pod-name")
    public ResponseEntity<Map<String, Object>> fetchPodName() {
        final Map<String, Object> result = Map.of("podName", this.podName);
        return ResponseEntity.ok(result);
    }

}

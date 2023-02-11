package com.example.ubuntutestserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ServiceController {

    private final ObjectMapper objectMapper;
    @Value("${env.pod-name:NONE}")
    private String podName;

    @GetMapping("/api/services/{serviceName}")
    public ResponseEntity<Map<String, Object>> fetchSelf(@PathVariable final String serviceName) throws JsonProcessingException {
        final RestTemplate restTemplate = new RestTemplate();
        final String result = restTemplate.getForObject("http://" + serviceName + "/environments/pod-name", String.class);
        final Map<String, Object> resultAsMap = this.objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
        });

        final Map<String, Object> body = new HashMap<>();
        body.put("thisPodName", this.podName);
        body.put("servicePodName", resultAsMap.getOrDefault("podName", "NONE"));
        return ResponseEntity.ok(body);
    }

}

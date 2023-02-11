package com.example.ubuntutestserver.controller;

import com.example.ubuntutestserver.properties.ConfigMapProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ConfigMapController {

    private final ConfigMapProperties configMapProperties;

    @GetMapping("/api/configmaps")
    public ResponseEntity<Map<String, Object>> fetchConfigMaps() {
        return ResponseEntity.ok(this.configMapProperties.getProps());
    }

}

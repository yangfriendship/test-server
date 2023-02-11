package com.example.ubuntutestserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class ScaleController {

    @GetMapping("/api/scales/memory")
    public ResponseEntity<Map<String, Object>> createDummyMemory(@RequestParam("repeat") int repeatCount) {
        final List<String> uuids = new ArrayList<>(repeatCount);
        for (int i = 0; i < repeatCount; i++) {
            uuids.add(UUID.randomUUID().toString());
        }
        final Map<String, Object> result = Map.of("result", "success");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/scales/cpu")
    public ResponseEntity<Map<String, Object>> createDummyCpu(@RequestParam("repeat") int repeatCount) {
        final List<String> uuids = new ArrayList<>(repeatCount);
        for (int i = 0; i < repeatCount; i++) {
            BigInteger number = BigInteger.valueOf(i);
            final BigInteger multiply = number.multiply(number);
        }
        final Map<String, Object> result = Map.of("result", "success");
        return ResponseEntity.ok(result);
    }

}

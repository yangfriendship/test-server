package com.example.ubuntutestserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@RestController
@Slf4j
public class VolumeController {

    @Value("${volume.file-path:/default-file.txt}")
    private String filePath;

    @PostConstruct
    public void post() {
        // ...
    }
    private final ReentrantLock fileLock = new ReentrantLock();

    private final ObjectMapper objectMapper;


    @GetMapping("/api/volumes/files")
    public ResponseEntity<Map<String, Object>> fetchFile() throws IOException {
        log.info("this.filePath = {}", this.filePath);
        fileLock.lock();
        File file = new File(this.filePath);
        try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
            final StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            final Map<String, Object> result = new HashMap<>();
            result.put("file", sb.toString());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IOException();
        } finally {
            fileLock.unlock();
        }
    }

    @PostMapping("/api/volumes/files")
    public ResponseEntity<Map<String, Object>> writeFile(@RequestBody Map<String, Object> body) throws IOException {
        log.info("this.filePath = {}", this.filePath);
        fileLock.lock();
        final File file = new File(this.filePath);
        try (final BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(this.objectMapper.writeValueAsString(body));
            br.write('\n');
            br.flush();
            br.close();
            final Map<String, Object> result = new HashMap<>();
            result.put("size", file.length());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IOException();
        } finally {
            fileLock.unlock();
        }
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> handleIOException(final IOException e) {
        final Map<String, Object> result = new HashMap<>();
        result.put("error", e.getMessage());
        return ResponseEntity.internalServerError().body(result);
    }

}

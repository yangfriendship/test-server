package com.example.ubuntutestserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersistentVolumeController {

    @Value("${pv.directoryPath:C:\\Users\\byulsoft\\Desktop\\temp}")
    private String directoryPath;
    @Value("${env.podName:NONE}")
    public String podName;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void post() {
        final char c = this.directoryPath.charAt(this.directoryPath.length() - 1);
        if (FileSystems.getDefault().getSeparator().charAt(0) != c) {
            this.directoryPath = this.directoryPath + FileSystems.getDefault().getSeparator();
        }
    }

    @GetMapping("/api/pv")
    public ResponseEntity<Map<String, Object>> fetchDirectory() {
        log.info("this.directoryPath: {}", this.directoryPath);
        final File file = new File(this.directoryPath);
        if (!file.exists() || file.isDirectory()) {
            return ResponseEntity.status(400).body(Map.of("message", "파일이 존재하지 않습니다."));
        }
        final File[] files = file.listFiles();
        final String fileNames = Arrays.stream(files).map(File::getName)
                .collect(Collectors.joining());
        return ResponseEntity.ok(Map.of("files", fileNames));
    }

    @PostMapping("/api/pv")
    public ResponseEntity<Map<String, Object>> createNewFile(@RequestBody final Map<String, Object> body) throws IOException {
        log.info("this.directoryPath: {}", this.directoryPath);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm_ss");
        final String fileName = this.podName + "_" + dateTimeFormatter.format(LocalDateTime.now()) + ".txt";
        final File file = new File(this.directoryPath + fileName + ".txt");
        final boolean newFile = file.createNewFile();
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(this.objectMapper.writeValueAsString(body));
        bw.write('\n');
        bw.flush();
        bw.close();
        return ResponseEntity.ok(Map.of("message", "success"));
    }

}

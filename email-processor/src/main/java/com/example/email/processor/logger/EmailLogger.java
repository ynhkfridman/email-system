package com.example.email.processor.logger;

import com.example.email.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailLogger {
    private static final Logger logger = LoggerFactory.getLogger(EmailLogger.class);
    private static final String LOG_FILE = "/data/emails-log.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final Object LOCK = new Object();

    public void logEmail(EmailMessage message) {
        Path path = Paths.get(LOG_FILE);

        synchronized (LOCK) {
            try {
                if (!Files.exists(path)) {
                    logger.info("Creating new log file at {}", LOG_FILE);
                    Files.writeString(path, "timestamp_utc;size_kb\n", StandardOpenOption.CREATE_NEW);
                }

                String timestamp = ZonedDateTime.now(ZoneOffset.UTC).format(FORMATTER);
                int sizeInKb = (int) Math.ceil(message.getBody().getBytes().length / 1024.0);

                String line = String.format("%s;%d\n", timestamp, sizeInKb);

                Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
                logger.debug("Logged email: timestamp={}, size={}KB", timestamp, sizeInKb);
            } catch (IOException e) {
                logger.error("Error writing to log file: {}", e.getMessage(), e);
            }
        }
    }
}
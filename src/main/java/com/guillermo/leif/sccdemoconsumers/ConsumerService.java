package com.guillermo.leif.sccdemoconsumers;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
public class ConsumerService {
    private final RestTemplate restTemplate;

    @Autowired
    public ConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getJsonMessage() {
        ResponseEntity<Map> response = restTemplate.getForEntity("http://localhost:8080/json", Map.class);
        return (String) Objects.requireNonNull(response.getBody()).get("message");
    }

    public String getXmlMessage() {
        ResponseEntity<Message> response = restTemplate.getForEntity("http://localhost:8080/xml", Message.class);
        return Objects.requireNonNull(response.getBody()).getFormattedMessage();
    }
}


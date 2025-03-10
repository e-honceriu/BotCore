package com.dimetro.discordbot.securityservice.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.*;
import com.dimetro.discordbot.securityservice.util.exception.UnexpectedRestClientException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestServiceClient {
    
    protected final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RestServiceClient.class);

    public RestServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected ResponseEntity<?> sendRequest(String url, HttpMethod method, Map<String, Object> body, HttpHeaders headers) {
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        try {
            logger.info("Sending {} request to URL: {}", method, url);
            return restTemplate.exchange(url, method, entity, Object.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.warn("HTTP error while making {} request to {}: {}", method, url, e.getMessage(), e);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), Map.class);
                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
            } catch (Exception ex) {
                logger.error("Error deserializing error response: {}", ex.getMessage());
                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        } catch (RestClientException e) {
            logger.error("Unexpected error while making {} request to {}: {}", method, url, e.getMessage(), e);
            throw new UnexpectedRestClientException(e);
        }
    }

    protected ResponseEntity<?> sendRequest(String url, HttpMethod method, Object body, HttpHeaders headers) {
        HttpEntity<Object> entity = new HttpEntity<>(body, headers); 
        try {
            logger.info("Sending {} request to URL: {}", method, url);
            return restTemplate.exchange(url, method, entity, Object.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.warn("HTTP error while making {} request to {}: {}", method, url, e.getMessage(), e);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), Map.class);
                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
            } catch (Exception ex) {
                logger.error("Error deserializing error response: {}", ex.getMessage());
                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        } catch (RestClientException e) {
            logger.error("Unexpected error while making {} request to {}: {}", method, url, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    protected ResponseEntity<?> sendFileRequest(String url, HttpMethod method, Object body, HttpHeaders headers) {
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        try {
            logger.info("Sending {} request to URL: {}", method, url);
            return restTemplate.exchange(url, method, entity, byte[].class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.warn("HTTP error while making {} request to {}: {}", method, url, e.getResponseBodyAsString(), e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (RestClientException e) {
            logger.error("Unexpected error while making {} request to {}: {}", method, url, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    protected ResponseEntity<?> sendFileRequest(HttpMethod method, String url) {
        return sendFileRequest(url, method, null, new HttpHeaders());
    }

    protected ResponseEntity<?> sendRequest(HttpMethod method, String url, Object body) {
        return sendRequest(url, method, body, new HttpHeaders());
    }

    protected ResponseEntity<?> sendRequest(HttpMethod method, String url) {
        return sendRequest(url, method, null, new HttpHeaders());
    }

}

package com.dimetro.discordbot.securityservice.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import com.dimetro.discordbot.securityservice.util.exception.ParamEncodingException;

public class ApiUrlBuilder {
    
    private final StringBuilder urlBuilder;
    private boolean firstParam;

    private ApiUrlBuilder(String baseUrl) {
        this.urlBuilder = new StringBuilder(baseUrl);
        this.firstParam = true;
    }

    public static ApiUrlBuilder from(String baseUrl) {
        return new ApiUrlBuilder(baseUrl);
    }

    private ApiUrlBuilder appendParam(String key, String value) {
        if (firstParam) {
            urlBuilder.append("?");
            firstParam = false;
        } else {
            urlBuilder.append("&");
        }
        urlBuilder.append(encode(key)).append("=").append(encode(value));
        return this;
    }

    public ApiUrlBuilder addParam(String key, String value) {

        if (value != null && !value.isEmpty()) {
            appendParam(key, value);
        }

        return this;
    }

    public ApiUrlBuilder addParam(String key, Integer value) {

        if (value != null) {
            appendParam(key, String.valueOf(value));
        }

        return this;
    }

    public ApiUrlBuilder addParam(String key, Long value) {

        if (value != null) {
            appendParam(key, String.valueOf(value));
        }

        return this;
    }

    public ApiUrlBuilder addParam(String key, Boolean value) {

        if (value != null) {
            appendParam(key, String.valueOf(value));
        }

        return this;
    }

    public ApiUrlBuilder addParam(String key, UUID value) {

        if (value != null) {
            appendParam(key, value.toString());
        }

        return this;
    }

    public ApiUrlBuilder addParam(String key, LocalDateTime value) {

        if (value != null) {
            appendParam(key, value.toString());
        }
        return this;
    }
    
    public String build() {
        return urlBuilder.toString();
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new ParamEncodingException();
        }
    }

}

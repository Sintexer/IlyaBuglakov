package com.ilyabuglakov.raise.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Forward {
    private String forward;
    private boolean isRedirect;
    private Map<String, String> attributes = new HashMap<>();

    public Forward() {
        forward = "";
        isRedirect = false;
    }

    public Forward(String forward) {
        this.forward = forward;
        isRedirect = false;
    }

    public Forward(String forward, boolean isRedirect) {
        this.forward = forward;
        this.isRedirect = isRedirect;
    }
}

package com.inventory.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String path;
    private String timestamp;
    private String error;
    private String details;
}
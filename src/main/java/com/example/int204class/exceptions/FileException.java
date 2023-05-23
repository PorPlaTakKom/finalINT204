package com.example.int204class.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileException {
    private String message;
    private String timeStamp;
    private Integer status;

    public FileException(String reason, String timeStamp, Integer status) {
        this.message = reason;
        this.timeStamp = timeStamp;
        this.status = status;
    }
}




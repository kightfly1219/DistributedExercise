package com.example.orderserver.framework.serviceflow.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtInfo implements Serializable {
    private String userId;
}

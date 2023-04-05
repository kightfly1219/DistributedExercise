package com.example.orderserver.framework.serviceflow.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageBean {
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("description")
    private String description;
}

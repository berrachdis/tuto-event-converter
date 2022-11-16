package com.tuto.commonlibrary.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public abstract class AbstractMessage {
    @NotBlank(message = "The Message_ID cannot be null or empty")
    @JsonProperty("Message_ID")
    private String messageId;

    @NotBlank(message = "The Event_Time cannot be null or empty")
    @JsonProperty("Event_Time")
    private String eventTime;

    @NotBlank(message = "The Record_Time cannot be null or empty")
    @JsonProperty("Record_Time")
    private String recordTime;
}

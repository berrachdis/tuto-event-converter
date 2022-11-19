package com.tuto.commonlibrary.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public abstract class AbstractMessage {
    @NotBlank(message = "The Message_ID cannot be null or empty")
    @JsonProperty("Message_ID")
    private String messageId;

    @NotBlank(message = "The Event_Time cannot be null or empty")
    @JsonProperty("Event_Time")
    @Pattern(regexp = "^([0-9]{2})(0[1-9]{1}|1[0-2]{1})(0[1-9]|[1-2][1-9]|3[0-2])(0[0-9]|1[0-9]|2[0-3])$", message = "The Event_Time must match with yyMMddHH format")
    private String eventTime;

    @NotBlank(message = "The Record_Time cannot be null or empty")
    @JsonProperty("Record_Time")
    private String recordTime;
}

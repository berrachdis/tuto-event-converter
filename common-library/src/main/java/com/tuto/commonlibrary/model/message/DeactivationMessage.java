package com.tuto.commonlibrary.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonRootName(value = "Deactivation")
public class DeactivationMessage extends AbstractMessage {
    @NotBlank(message = "The EO_ID cannot be null or empty")
    @JsonProperty("EO_ID")
    private String eoid;

    @Min(value = 1, message = "The Deact_Type must be between [1, 3]")
    @Max(value = 3, message = "The Deact_Type must be between [1, 3]")
    @JsonProperty("Deact_Type")
    private int deactType;

    @NotEmpty(message = "The Deact_aUI cannot be null or empty")
    @JsonProperty("Deact_aUI")
    private List<String> deactAUIs = new ArrayList<>();

    @NotEmpty(message = "The Deact_upUI cannot be null or empty")
    @JsonProperty("Deact_upUI")
    private List<String> deactUpUIs = new ArrayList<>();

    @JsonProperty("Deactivation_Comment")
    private String deactivationComment;
}

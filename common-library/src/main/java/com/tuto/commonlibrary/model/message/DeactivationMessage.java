package com.tuto.commonlibrary.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.tuto.commonlibrary.validation.annotation.DeactivationChecker;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@DeactivationChecker
@JsonRootName(value = "Deactivation")
public class DeactivationMessage extends AbstractMessage {
    @NotBlank(message = "The EO_ID cannot be null or empty")
    @JsonProperty("EO_ID")
    private String eoid;

    @Min(value = 1, message = "The Deact_Type must be between [1, 3]")
    @Max(value = 3, message = "The Deact_Type must be between [1, 3]")
    @JsonProperty("Deact_Type")
    private int deactType;

    @JsonProperty("Deact_aUI")
    private List<String> deactAUIs = new ArrayList<>();

    @JsonProperty("Deact_upUI")
    private List<String> deactUpUIs = new ArrayList<>();

    @JsonProperty("Deactivation_Comment")
    private String deactivationComment;
}

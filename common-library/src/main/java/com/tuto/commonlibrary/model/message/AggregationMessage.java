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
@JsonRootName(value = "Aggregation")
public class AggregationMessage extends AbstractMessage {
    @NotBlank(message = "The EO_ID cannot be null or empty")
    @JsonProperty("EO_ID")
    private String eoid;

    @NotBlank(message = "The F_ID cannot be null or empty")
    @JsonProperty("F_ID")
    private String fid;

    @Min(value = 1, message = "The Aggregation_Type must be between [1, 3]")
    @Max(value = 3, message = "The Aggregation_Type must be between [1, 3]")
    @JsonProperty("Aggregation_Type")
    private int aggregationType;

    @NotEmpty(message = "The parentAUI cannot be null or empty")
    @JsonProperty("parentAUI")
    private String parentId;

    @NotEmpty(message = "The Aggregated_UIs1 cannot be null or empty")
    @JsonProperty("Aggregated_UIs1")
    private List<String> aggregatedUis1 = new ArrayList<>();

    @NotEmpty(message = "The Aggregated_UIs2 cannot be null or empty")
    @JsonProperty("Aggregated_UIs2")
    private List<String> aggregatedUis2 = new ArrayList<>();

    @JsonProperty("Aggregation_Comment")
    private String comment;
}

package com.tuto.commonlibrary.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonRootName(value = "Commissioning")
public class CommissioningMessage extends AbstractMessage {
    @NotBlank(message = "The F_ID cannot be null or empty")
    @JsonProperty("F_ID")
    private String fid;

    @NotEmpty(message = "The upUI_1 cannot be null or empty")
    @JsonProperty("upUI_1")
    private List<String> upUI1 = new ArrayList<>();

    @NotEmpty(message = "The upUI_2 cannot be null or empty")
    @JsonProperty("upUI_2")
    private List<String> upUI2 = new ArrayList<>();

    @JsonProperty("Commissioning_Comment")
    private String commissioningComment;
}

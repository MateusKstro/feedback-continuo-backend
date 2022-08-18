package com.feedbackcontinuos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TagDTO extends TagCreateDTO{

    @Schema(description = "Id da tag")
    private Integer idTag;
}

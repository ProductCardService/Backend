package fitch.product.card.service.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CardInfoDto(
        Long id,
        String title,
        String description,
        List<String> tags,
        String image
) {
}

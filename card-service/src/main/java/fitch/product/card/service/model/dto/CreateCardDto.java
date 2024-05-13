package fitch.product.card.service.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCardDto(
        String title,
        String description,
        List<String> tags,
        String image
) {
}

package com.example.dragonsofmugloar.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Purchase(@JsonProperty("shoppingSuccess") boolean shoppingSuccess,
					   @JsonProperty("lives") int lives,
					   @JsonProperty("gold") int gold,
					   @JsonProperty("level") int level,
					   @JsonProperty("turn") int turn) {
}

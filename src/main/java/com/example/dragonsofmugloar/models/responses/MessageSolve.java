package com.example.dragonsofmugloar.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageSolve(@JsonProperty("success") boolean success,
						   @JsonProperty("lives") int lives,
						   @JsonProperty("gold") int gold,
						   @JsonProperty("score") int score,
						   @JsonProperty("highScore") int highScore,
						   @JsonProperty("turn") int turn,
						   @JsonProperty("message") String message) {
}

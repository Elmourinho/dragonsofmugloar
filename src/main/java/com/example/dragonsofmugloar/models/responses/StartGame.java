package com.example.dragonsofmugloar.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StartGame(@JsonProperty("gameId") String gameId,
						@JsonProperty("lives") int lives,
						@JsonProperty("gold") int gold,
						@JsonProperty("level") int level,
						@JsonProperty("score") int score,
						@JsonProperty("highScore") int highScore,
						@JsonProperty("turn") int turn) {
}

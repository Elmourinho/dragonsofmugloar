package com.example.dragonsofmugloar.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Reputation(@JsonProperty("people") double people,
                         @JsonProperty("state") double state,
                         @JsonProperty("underworld") double underworld) {
}

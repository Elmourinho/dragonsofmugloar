package com.example.dragonsofmugloar.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShopItem(@JsonProperty("id") String id,
					   @JsonProperty("name") String name,
					   @JsonProperty("cost") int cost) {
}

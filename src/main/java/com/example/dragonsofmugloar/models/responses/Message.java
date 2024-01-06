package com.example.dragonsofmugloar.models.responses;

import com.example.dragonsofmugloar.util.DecodingHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Message(@JsonProperty("adId") String adId,
					  @JsonProperty("message") String messageText,
					  @JsonProperty("reward") int reward,
					  @JsonProperty("expiresIn") int expiresIn,
					  @JsonProperty("encrypted") Integer encrypted,
					  @JsonProperty("probability") String probability) {

	public Message decrypt() {
		if (encrypted == null) {
			return this;
		}

		String decryptedId = DecodingHelper.decodeText(adId, encrypted);
		String decryptedMessage = DecodingHelper.decodeText(messageText, encrypted);
		String decryptedProbability = DecodingHelper.decodeText(probability, encrypted);
		return new Message(decryptedId, decryptedMessage, reward, expiresIn, encrypted, decryptedProbability);
	}
}

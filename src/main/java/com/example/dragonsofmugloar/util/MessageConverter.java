package com.example.dragonsofmugloar.util;


import com.example.dragonsofmugloar.models.CustomMessage;
import com.example.dragonsofmugloar.models.MessageType;
import com.example.dragonsofmugloar.models.Probability;
import com.example.dragonsofmugloar.models.responses.Message;

import java.util.stream.Stream;

public class MessageConverter {

    public static CustomMessage convertToCustomMessage(Message message){

        Probability probability = getProbability(message.probability());
        MessageType messageType = getMessageType(message.messageText());
        int point = probability.getValue() + messageType.getValue() + message.reward();

        CustomMessage customMessage = new CustomMessage();
        customMessage.setAdId(message.adId());
        customMessage.setProbability(probability);
        customMessage.setMessageType(messageType);
        customMessage.setPoint(point);

        return customMessage;
    }

    private static MessageType getMessageType(String messageText) {
        return Stream.of(MessageType.values())
                .filter(type -> messageText.toLowerCase().startsWith(type.getLabel().toLowerCase()))
                .findFirst()
                .orElse(MessageType.UNKNOWN);
    }

    private static Probability getProbability(String probabilityLabel) {
        return Stream.of(Probability.values())
                .filter(probability -> probability.getLabel().equalsIgnoreCase(probabilityLabel))
                .findFirst()
                .orElse(Probability.UNKNOWN);
    }
}

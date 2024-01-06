package com.example.dragonsofmugloar.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomMessage {

    private String adId;
    private Probability probability;
    private MessageType messageType;
    private int point;
}

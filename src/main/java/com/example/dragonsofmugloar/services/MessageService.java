package com.example.dragonsofmugloar.services;


import com.example.dragonsofmugloar.models.CustomMessage;
import com.example.dragonsofmugloar.models.responses.MessageSolve;

import java.util.Optional;

public interface MessageService {

    Optional<CustomMessage> getMostRelevantMessage(String gameId);

    MessageSolve solveMessage(String gameId, String adId);
}

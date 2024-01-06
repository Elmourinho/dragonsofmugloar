package com.example.dragonsofmugloar.services.impl;


import com.example.dragonsofmugloar.models.CustomMessage;
import com.example.dragonsofmugloar.models.responses.Message;
import com.example.dragonsofmugloar.models.responses.MessageSolve;
import com.example.dragonsofmugloar.services.ApiClient;
import com.example.dragonsofmugloar.services.MessageService;
import com.example.dragonsofmugloar.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final ApiClient apiClient;

    @Override
    public Optional<CustomMessage> getMostRelevantMessage(String gameId) {
        List<Message> messages = apiClient.getAllMessages(gameId);

        return messages
                .stream()
                .map(Message::decrypt)
                .map(MessageConverter::convertToCustomMessage)
                .max(Comparator.comparingInt(CustomMessage::getPoint));
    }

    @Override
    public MessageSolve solveMessage(String gameId, String adId) {
        MessageSolve messageSolve = apiClient.solveMessage(gameId, adId);
        log.info("The result was: {}. Now you have {} lives and {} golds. Score is {}",
                messageSolve.success(), messageSolve.lives(), messageSolve.gold(), messageSolve.score());
        return messageSolve;
    }

}

package com.example.dragonsofmugloar.services.impl;

import com.example.dragonsofmugloar.models.CustomMessage;
import com.example.dragonsofmugloar.models.MessageType;
import com.example.dragonsofmugloar.models.Probability;
import com.example.dragonsofmugloar.models.responses.Message;
import com.example.dragonsofmugloar.services.ApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void getMostRelevantMessageShouldFindMessageWhenMessagesIsNotEmpty() {

        //given
        String gameId = "testGameId";
        List<Message> apiMessages = buildDummyMessageList();

        //when
        when(apiClient.getAllMessages(gameId)).thenReturn(apiMessages);

        Optional<CustomMessage> customMessage = messageService.getMostRelevantMessage(gameId);

        //then
        assertTrue(customMessage.isPresent());
    }

    @Test
    void getMostRelevantMessageShouldNotFindMessageWhenMessagesIsEmpty() {

        //given
        String gameId = "testGameId";

        //when
        when(apiClient.getAllMessages(gameId)).thenReturn(new ArrayList<>());

        Optional<CustomMessage> customMessage = messageService.getMostRelevantMessage(gameId);

        //then
        assertTrue(customMessage.isEmpty());
    }

    @Test
    void getMostRelevantMessageShouldDecodeMessagesAndFindMostRelevant() {

        //given
        String gameId = "testGameId";
        List<Message> apiMessages = buildDummyMessageListWithEncryptedData();

        //when
        when(apiClient.getAllMessages(gameId)).thenReturn(apiMessages);

        Optional<CustomMessage> customMessage = messageService.getMostRelevantMessage(gameId);

        //then
        assertTrue(customMessage.isPresent());

        assertEquals(customMessage.get().getAdId(), "adId1");
        assertEquals(customMessage.get().getMessageType(), MessageType.HELP);
        assertEquals(customMessage.get().getProbability(), Probability.PIECE_OF_CAKE);
    }

    private List<Message> buildDummyMessageList() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("adId1", "Escort someone", 60, 5, null, "Risky"));
        messages.add(new Message("adId2", "Help someone for something", 100, 5, null, "Piece of cake"));
        messages.add(new Message("adId3", "Create something", 50, 2, null, "Quite likely"));
        return messages;
    }

    private List<Message> buildDummyMessageListWithEncryptedData() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("adId1", "Help someone for something", 100, 5, null, "Piece of cake"));
        messages.add(new Message("YWRJZDI=", "Q3JlYXRlIHNvbWV0aGluZw==", 50, 2, 1, "UXVpdGUgbGlrZWx5"));
        messages.add(new Message("nqVq3", "Rfpbeg fbzrbar", 60, 5, 2, "Evfxl"));
        return messages;
    }
}
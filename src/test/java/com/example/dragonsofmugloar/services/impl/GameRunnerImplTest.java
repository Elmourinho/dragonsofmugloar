package com.example.dragonsofmugloar.services.impl;


import com.example.dragonsofmugloar.models.CustomMessage;
import com.example.dragonsofmugloar.models.MessageType;
import com.example.dragonsofmugloar.models.Probability;
import com.example.dragonsofmugloar.models.responses.MessageSolve;
import com.example.dragonsofmugloar.models.responses.StartGame;
import com.example.dragonsofmugloar.services.GameService;
import com.example.dragonsofmugloar.services.MessageService;
import com.example.dragonsofmugloar.services.ShopService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameRunnerImplTest {

    @Mock
    private GameService gameService;

    @Mock
    private MessageService messageService;

    @Mock
    private ShopService shopService;

    @InjectMocks
    private GameRunnerImpl gameRunner;

    @Test
    void playGameWinScenario() {

        when(gameService.startGame()).thenReturn(
                new StartGame("gameId", 0, 0, 0, 0, 0, 0));

        CustomMessage customMessage = new CustomMessage();
        customMessage.setAdId("adId");
        customMessage.setPoint(100);
        customMessage.setProbability(Probability.PIECE_OF_CAKE);
        customMessage.setMessageType(MessageType.ESCORT);

        when(messageService.getMostRelevantMessage("gameId")).thenReturn(Optional.of(customMessage));
        when(messageService.solveMessage("gameId", "adId")).thenReturn(
                new MessageSolve(true, 3, 100, 1000, 0, 0, "Help"));

        gameRunner.playGame();

        verify(gameService, atLeastOnce()).startGame();
        verify(messageService, atLeastOnce()).getMostRelevantMessage(anyString());
        verify(messageService, atLeastOnce()).solveMessage(eq("gameId"), anyString());
        verify(shopService, never()).purchaseLive(anyString());
        verify(shopService, never()).purchaseItem(anyString());
    }

    @Test
    void playGameLostScenario() {

        when(gameService.startGame()).thenReturn(
                new StartGame("gameId", 0, 0, 0, 0, 0, 0));

        CustomMessage customMessage = new CustomMessage();
        customMessage.setAdId("adId");
        customMessage.setPoint(100);
        customMessage.setProbability(Probability.PIECE_OF_CAKE);
        customMessage.setMessageType(MessageType.ESCORT);

        when(messageService.getMostRelevantMessage("gameId")).thenReturn(Optional.of(customMessage));
        when(messageService.solveMessage("gameId", "adId")).thenReturn(
                new MessageSolve(true, 0, 100, 1000, 0, 0, "Help"));

        gameRunner.playGame();

        verify(gameService, atLeastOnce()).startGame();
        verify(messageService, atLeastOnce()).getMostRelevantMessage(anyString());
        verify(messageService, atLeastOnce()).solveMessage(eq("gameId"), anyString());
        verify(shopService, never()).purchaseLive(anyString());
        verify(shopService, never()).purchaseItem(anyString());
    }
}
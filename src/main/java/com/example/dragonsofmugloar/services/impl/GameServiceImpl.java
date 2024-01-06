package com.example.dragonsofmugloar.services.impl;



import com.example.dragonsofmugloar.models.responses.Reputation;
import com.example.dragonsofmugloar.models.responses.StartGame;
import com.example.dragonsofmugloar.services.ApiClient;
import com.example.dragonsofmugloar.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final ApiClient apiClient;

    @Override
    public StartGame startGame() {
        return apiClient.startGame();
    }

    @Override
    public Reputation investigate(String gameId) {
        return apiClient.investigate(gameId);
    }
}

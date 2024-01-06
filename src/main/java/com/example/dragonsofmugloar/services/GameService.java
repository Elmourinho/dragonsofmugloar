package com.example.dragonsofmugloar.services;


import com.example.dragonsofmugloar.models.responses.Reputation;
import com.example.dragonsofmugloar.models.responses.StartGame;

public interface GameService {

    StartGame startGame();

    Reputation investigate(String gameId);

}

package com.example.dragonsofmugloar.services;


import com.example.dragonsofmugloar.models.responses.*;

import java.util.List;

public interface ApiClient {

	StartGame startGame();

	List<Message> getAllMessages(String gameId);

	MessageSolve solveMessage(String gameId, String adId);

	List<ShopItem> getShopItems(String gameId);

	Purchase purchaseItem(String gameId, String itemId);

	Reputation investigate(String gameId);
}

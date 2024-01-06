package com.example.dragonsofmugloar.services.impl;

import com.example.dragonsofmugloar.models.responses.*;
import com.example.dragonsofmugloar.services.ApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiClientImpl implements ApiClient {

    private final RestTemplate restTemplate;

    @Value("${dragonsofmugloar.base-url}")
    private String BASE_URL;

    @Override
    public StartGame startGame() {

        String url = BASE_URL + "game/start";

        return restTemplate.postForObject(
                url,
                null,
                StartGame.class);
    }

    @Override
    public List<Message> getAllMessages(String gameId) {

        String url = BASE_URL + gameId + "/messages";

        ResponseEntity<List<Message>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                gameId
        );

        return responseEntity.getBody();
    }

    @Override
    public MessageSolve solveMessage(String gameId, String adId) {

        String url = BASE_URL + gameId + "/solve/" + adId;

        ResponseEntity<MessageSolve> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                MessageSolve.class,
                gameId,
                adId
        );
        return responseEntity.getBody();
    }

    @Override
    public List<ShopItem> getShopItems(String gameId) {

        String url = BASE_URL + gameId + "/shop";

        ResponseEntity<List<ShopItem>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                gameId
        );
        return responseEntity.getBody();
    }

    @Override
    public Purchase purchaseItem(String gameId, String itemId) {

        String url = BASE_URL + gameId + "/shop/buy/" + itemId;

        ResponseEntity<Purchase> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                Purchase.class,
                gameId,
                itemId
        );
        return responseEntity.getBody();
    }

    @Override
    public Reputation investigate(String gameId) {

        String url = BASE_URL + gameId + "/investigate/reputation";

        return restTemplate.postForObject(
                url,
                null,
                Reputation.class);
    }
}

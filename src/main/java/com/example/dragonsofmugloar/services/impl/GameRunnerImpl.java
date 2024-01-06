package com.example.dragonsofmugloar.services.impl;


import com.example.dragonsofmugloar.services.GameRunner;
import com.example.dragonsofmugloar.services.GameService;
import com.example.dragonsofmugloar.services.MessageService;
import com.example.dragonsofmugloar.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameRunnerImpl implements GameRunner {

    private final GameService gameService;
    private final MessageService messageService;
    private final ShopService shopService;

    private static final Integer WIN_SCORE = 1000;
    private static final Integer LIVE_COST = 50;
    private static final Integer ITEM_COST = 100;

    @Override
    public void playGame() {

        var startGame = gameService.startGame();
        var score = 0;

        var gameId = startGame.gameId();

        while (score <= WIN_SCORE) {
            try {
                var messageOptional = messageService.getMostRelevantMessage(gameId);
                if (messageOptional.isPresent()) {
                    var message = messageOptional.get();
                    var messageSolve = messageService.solveMessage(gameId, message.getAdId());
                    log.info("You solved a message with the probability: {}", message.getProbability());
                    score = messageSolve.score();

                    if (messageSolve.lives() == 0) {
                        log.info("Oops, it looks like you got lost!");
                        return;
                    }

                    if (score >= WIN_SCORE) {
                        log.info("You've won! Congratulations!");
                        return;
                    }

                    var reputation = gameService.investigate(gameId);
                    handleShopping(gameId, messageSolve.lives(), messageSolve.gold(), reputation.state());
                }

            } catch (Exception e) {
                // some weird API exceptions are avoided
                log.error(e.getMessage());
            }
        }

    }

    private void handleShopping(String gameId, int lives, int gold, double state) {
        if (lives < 3 && gold > LIVE_COST) {
            shopService.purchaseLive(gameId);
        } else if (state < 0 && gold - LIVE_COST > ITEM_COST) { // let's keep gold for live just in case
            shopService.purchaseItem(gameId);
        }
    }
}

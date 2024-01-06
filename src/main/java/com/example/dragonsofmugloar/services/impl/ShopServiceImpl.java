package com.example.dragonsofmugloar.services.impl;


import com.example.dragonsofmugloar.models.responses.Purchase;
import com.example.dragonsofmugloar.models.responses.ShopItem;
import com.example.dragonsofmugloar.services.ApiClient;
import com.example.dragonsofmugloar.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ApiClient apiClient;

    private final String LIVE_ITEM_ID = "hpot";

    private static int itemIndex = 0;

    @Override
    public List<ShopItem> getAllItems(String gameId) {
        return apiClient.getShopItems(gameId);
    }

    @Override
    public Purchase purchaseLive(String gameId) {
        log.info("You buy the item with the id: {}", LIVE_ITEM_ID);
        Purchase purchase = apiClient.purchaseItem(gameId, LIVE_ITEM_ID);
        log.info("The result was: {}. Now you have {} lives and {} golds",
                purchase.shoppingSuccess(), purchase.lives(), purchase.gold());
        return purchase;
    }

    @Override
    public Purchase purchaseItem(String gameId) {

        List<ShopItem> shopItems = getAllItems(gameId);
        List<String> necessaryItemIds = shopItems
                .stream()
                .filter(shopItem -> shopItem.cost() == 100)
                .map(ShopItem::id)
                .toList();

        // trying to buy all items
        if (itemIndex == necessaryItemIds.size()) {
            itemIndex = 0;
        }

        String selectedItemId = necessaryItemIds.get(itemIndex);
        log.info("You buy the item with the id: {}", selectedItemId);

        Purchase purchase = apiClient.purchaseItem(gameId, selectedItemId);
        log.info("The result was: {}. Now you have {} lives and {} golds",
                purchase.shoppingSuccess(), purchase.lives(), purchase.gold());

        itemIndex++;
        return purchase;
    }
}

package com.example.dragonsofmugloar.services;


import com.example.dragonsofmugloar.models.responses.Purchase;
import com.example.dragonsofmugloar.models.responses.ShopItem;

import java.util.List;

public interface ShopService {

    List<ShopItem> getAllItems(String gameId);

    Purchase purchaseLive(String gameId);

    Purchase purchaseItem(String gameId);
}

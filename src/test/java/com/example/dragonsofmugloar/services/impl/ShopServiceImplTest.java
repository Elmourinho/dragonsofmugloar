package com.example.dragonsofmugloar.services.impl;

import com.example.dragonsofmugloar.models.responses.Purchase;
import com.example.dragonsofmugloar.models.responses.ShopItem;
import com.example.dragonsofmugloar.services.ApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceImplTest {

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private ShopServiceImpl shopService;

    @Test
    void purchaseItem() {
        // given
        String gameId = "testGameId";
        String purchasedItemId = "purchasedItemId";
        int itemCost = 100;

        List<ShopItem> shopItems = Arrays.asList(
                new ShopItem(purchasedItemId, "Item1", itemCost),
                new ShopItem("otherItemId", "Item2", itemCost)
        );

        // when
        when(apiClient.getShopItems(gameId)).thenReturn(shopItems);
        when(apiClient.purchaseItem(gameId, purchasedItemId)).thenReturn(new Purchase(true, 3, 50, 1,10)
        );


        Purchase result = shopService.purchaseItem(gameId);

        // then
        assertTrue(result.shoppingSuccess());
        assertEquals(3, result.lives());
        assertEquals(50, result.gold());
        verify(apiClient, times(1)).purchaseItem(gameId, purchasedItemId);
    }
}
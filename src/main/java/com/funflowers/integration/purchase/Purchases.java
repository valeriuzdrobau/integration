package com.funflowers.integration.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchases {

    private IntegrationPurchase[] purchases;

    public List<Purchase> getPurchases() {
        return Arrays.asList(purchases);
    }

    public void setPurchases(IntegrationPurchase[] purchases) {
        this.purchases = purchases;
    }
}

package com.funflowers.integration.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flexionmobile.codingchallenge.integration.Purchase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegrationPurchase implements Purchase {

    private String id;

    private boolean consumed;

    private String itemId;

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean getConsumed() {
        return this.consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    @Override
    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", consumed=" + consumed +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}

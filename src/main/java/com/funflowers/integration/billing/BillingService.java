package com.funflowers.integration.billing;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.integration.utils.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BillingService implements Integration {

    @Autowired
    Consumer consumer;

    private String developerId;

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    @Override
    public Purchase buy(String itemId) {
        return consumer.buy(developerId, itemId);
    }

    @Override
    public List<Purchase> getPurchases() {
        return consumer.getPurchases(developerId);
    }

    @Override
    public void consume(Purchase purchase) {
        consumer.consume(developerId, purchase);
    }
}

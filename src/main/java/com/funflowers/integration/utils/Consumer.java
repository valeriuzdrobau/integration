package com.funflowers.integration.utils;

import com.flexionmobile.codingchallenge.integration.Purchase;
import com.funflowers.integration.purchase.IntegrationPurchase;
import com.funflowers.integration.purchase.Purchases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "application.flexionApi")
public class Consumer {

    private String baseUrl;

    private Map<String, String> paths = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Map<String, String> getPaths() {
        return paths;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setPaths(Map<String, String> paths) {
        this.paths = paths;
    }

    public List<Purchase> getPurchases(String developerId) {
        ResponseEntity<Purchases> result = restTemplate.exchange(baseUrl + developerId + paths.get("getPurchases"), HttpMethod.GET, null, new ParameterizedTypeReference<Purchases>() {
        });
        Purchases purchases = result.getBody();
        return purchases.getPurchases();
    }

    public Purchase buy(String developerId, String itemId) {
        ResponseEntity<IntegrationPurchase> result = restTemplate.exchange(baseUrl + developerId + paths.get("buy") + itemId, HttpMethod.POST, null, new ParameterizedTypeReference<IntegrationPurchase>() {
        });

        return result.getBody();
    }

    public boolean consume(String developerId, com.flexionmobile.codingchallenge.integration.Purchase purchase) {
        ResponseEntity<Void> result = restTemplate.exchange(baseUrl + developerId + paths.get("consume") + purchase.getId(), HttpMethod.POST, null, Void.class);
        return result.getStatusCode() == HttpStatus.OK;
    }
}

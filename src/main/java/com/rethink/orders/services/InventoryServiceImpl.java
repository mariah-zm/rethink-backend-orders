package com.rethink.orders.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Value("${inventory-service.url}")
    private String inventoryServiceUrl;

    @Value("${inventory-service.endpoints.low-stock}")
    private String inventoryLowStockEndpoint;

    @Value("${supply-api.auth.token}")
    private String authenticationToken;

    @Override
    public List<Integer> getLowStock() throws IOException {
        String inventoryUrl = inventoryServiceUrl + inventoryLowStockEndpoint;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(inventoryUrl);
        getRequest.addHeader("accept", "application/json");
        getRequest.addHeader("Authorization", "Token " + authenticationToken);

        HttpResponse response = httpClient.execute(getRequest);
        int responseCode = response.getStatusLine().getStatusCode();

        if (responseCode != 200 && responseCode != 202) {
            throw new RuntimeException();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Integer> productIdList = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<>() {});

        httpClient.getConnectionManager().shutdown();

        return productIdList;
    }
}

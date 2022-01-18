package com.rethink.orders.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rethink.orders.models.OrderItem;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RequestServiceImpl implements RequestService {

    @Value("${supply-api.url}")
    private String supplyApiUrlString;

    @Value("${supply-api.endpoints.order}")
    private String supplyApiOrderEndpoint;

    @Value("${supply-api.endpoints.orderline}")
    private String supplyApiOrderLineEndpoint;

    @Value("${supply-api.endpoints.send-order}")
    private String supplyApiSendOrderEndpoint;

    @Value("${supply-api.endpoints.product}")
    private String supplyApiProductEndpoint;

    @Value("${supply-api.endpoints.delivery}")
    private String supplyApiDeliveryEndpoint;

    @Value("${supply-api.auth.token}")
    private String authenticationToken;

    @Override
    public void sendOrderPostRequest(Long orderId, boolean isCreate) throws IOException {
        String supplyUrl = isCreate ? supplyApiUrlString + supplyApiOrderEndpoint + "/" + orderId
                : supplyApiUrlString + supplyApiSendOrderEndpoint + "/" + orderId;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(supplyUrl);
        postRequest.addHeader("accept", "application/json");
        postRequest.addHeader("Authorization", "Token " + authenticationToken);

        HttpResponse response = httpClient.execute(postRequest);
        int responseCode = response.getStatusLine().getStatusCode();
        httpClient.getConnectionManager().shutdown();

        if (responseCode != 200 && responseCode != 202) {
            throw new RuntimeException();
        }
    }

    @Override
    public void sendOrderLineRequest(OrderItem orderItem) throws IOException {
        String supplyUrl = supplyApiUrlString + supplyApiOrderLineEndpoint;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(supplyUrl);
        postRequest.addHeader("accept", "application/json");
        postRequest.addHeader("Authorization", "Token " + authenticationToken);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(orderItem);
        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);
        int responseCode = response.getStatusLine().getStatusCode();
        httpClient.getConnectionManager().shutdown();

        if (responseCode != 200 && responseCode != 202) {
            throw new RuntimeException("Response code is " + responseCode);
        }
    }
}

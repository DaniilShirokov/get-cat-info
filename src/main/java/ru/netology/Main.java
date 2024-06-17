package ru.netology;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = connect();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(body.toString());

            List<CatInfo> catInfos = new ArrayList<>();
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                //filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0);
                catInfos.add(new CatInfo(
                        (String) jsonObject.get("id"),
                        (String) jsonObject.get("text"),
                        (String) jsonObject.get("type"),
                        (String) jsonObject.get("user"),
                        (Long) jsonObject.get("upvotes")
                ));

            }
            catInfos.stream().filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0).forEach(System.out::println);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static CloseableHttpClient connect() {
        CloseableHttpClient newhttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        return newhttpClient;
    }

}
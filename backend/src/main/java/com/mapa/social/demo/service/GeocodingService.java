package com.mapa.social.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public double[] geocode(String address) {
        try {
            String q = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + q + "&limit=1";
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "mapa-social-app")
                    .GET()
                    .build();

            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() == 200) {
                JsonNode arr = mapper.readTree(resp.body());
                if (arr.isArray() && arr.size() > 0) {
                    JsonNode first = arr.get(0);
                    double lat = first.has("lat") ? first.get("lat").asDouble() : Double.NaN;
                    double lon = first.has("lon") ? first.get("lon").asDouble() : Double.NaN;
                    if (!Double.isNaN(lat) && !Double.isNaN(lon)) {
                        return new double[]{lat, lon};
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
        }
        return null;
    }
}

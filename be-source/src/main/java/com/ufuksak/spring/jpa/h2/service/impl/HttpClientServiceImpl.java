package com.ufuksak.spring.jpa.h2.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufuksak.spring.jpa.h2.dto.CommitResponse;
import com.ufuksak.spring.jpa.h2.service.HttpClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@Service
public class HttpClientServiceImpl implements HttpClientService {

  @Value("${github.access_token}")
  private String accessToken;

  private static final HttpClient httpClient = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  public List<CommitResponse> listCommits(int perPage, int pageNumber) throws IOException, InterruptedException {

    ObjectMapper mapper = new ObjectMapper();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://api.github.com/repos/angular/angular/commits?per_page=" + perPage + "&page=" + pageNumber))
        .setHeader("Accept", "application/vnd.github+json")
        .setHeader("Authorization", "Bearer " + accessToken)
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    HttpHeaders headers = response.headers();
    headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

    return mapper.readValue(response.body(), new TypeReference<>() {
    });
  }
}

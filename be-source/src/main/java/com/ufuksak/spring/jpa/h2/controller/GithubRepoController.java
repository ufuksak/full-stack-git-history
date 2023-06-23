package com.ufuksak.spring.jpa.h2.controller;

import java.util.List;
import java.util.Optional;

import com.ufuksak.spring.jpa.h2.dto.CommitResponse;
import com.ufuksak.spring.jpa.h2.model.GithubRepoCommit;
import com.ufuksak.spring.jpa.h2.repository.GithubRepoRepository;
import com.ufuksak.spring.jpa.h2.service.HttpClientService;
import com.ufuksak.spring.jpa.h2.service.impl.HttpClientServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/v1/")
public class GithubRepoController {

  @Autowired
  GithubRepoRepository githubRepoRepository;

  private final HttpClientService httpClientService;

  public GithubRepoController(HttpClientServiceImpl httpClientSynchronous) {
    this.httpClientService = httpClientSynchronous;
  }

  @GetMapping("/commits")
  public ResponseEntity<List<CommitResponse>> getAllCommits(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    try {

      List<CommitResponse> githubRepoCommits = httpClientService.listCommits(size, page);

      long currentTime = System.currentTimeMillis();
      for (CommitResponse commitResponse : githubRepoCommits) {
        GithubRepoCommit githubRepoCommit = new GithubRepoCommit();
        githubRepoCommit.setMessage(commitResponse.getCommit().getMessage());
        githubRepoCommit.setLogin(commitResponse.getAuthor().getLogin());
        githubRepoCommit.setDate(commitResponse.getCommit().getCommitter().getDate());
        githubRepoCommit.setSha(commitResponse.getSha());
        githubRepoCommit.setDateTs(currentTime);
        githubRepoRepository.save(githubRepoCommit);
      }
      if (githubRepoCommits.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(githubRepoCommits, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/commits/{id}")
  public ResponseEntity<GithubRepoCommit> getTutorialById(@PathVariable("id") long id) {
    Optional<GithubRepoCommit> tutorialData = githubRepoRepository.findById(id);

    return tutorialData.map(githubRepoCommit ->
        new ResponseEntity<>(githubRepoCommit, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}

package com.ufuksak.spring.jpa.h2.repository;

import java.util.List;

import com.ufuksak.spring.jpa.h2.model.GithubRepoCommit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepoRepository extends JpaRepository<GithubRepoCommit, Long> {

  List<GithubRepoCommit> findByLoginContaining(String login);

  Page<GithubRepoCommit> findByLogin(String login, Pageable pageable);
}

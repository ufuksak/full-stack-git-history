package com.ufuksak.spring.jpa.h2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Data
@Table
public class GithubRepoCommit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  @Lob
  private String message;

  @Column
  private String login;

  @Column
  private String date;

  @Column
  private String sha;

  @Column
  private long dateTs;

  public GithubRepoCommit() {

  }
}

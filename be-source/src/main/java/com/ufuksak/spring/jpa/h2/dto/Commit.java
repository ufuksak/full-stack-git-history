package com.ufuksak.spring.jpa.h2.dto;

import lombok.Data;

@Data
public class Commit {
  CommitterIdentity author;
  CommitterIdentity committer;
  private String message;
  Tree tree;
  private String url;
  private float comment_count;
  Verification verification;
}

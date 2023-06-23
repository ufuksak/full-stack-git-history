package com.ufuksak.spring.jpa.h2.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommitResponse {
  private String sha;
  private String node_id;
  Commit commit;
  private String url;
  private String html_url;
  private String comments_url;
  Author author;
  Committer committer;
  List<Tree> parents = new ArrayList<>();
}

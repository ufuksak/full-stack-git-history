package com.ufuksak.spring.jpa.h2.service;

import com.ufuksak.spring.jpa.h2.dto.CommitResponse;

import java.io.IOException;
import java.util.List;

public interface HttpClientService {

  List<CommitResponse> listCommits(int perPage, int pageNumber) throws IOException, InterruptedException;
}

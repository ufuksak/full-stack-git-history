package com.ufuksak.spring.jpa.h2.dto;

import lombok.Data;

@Data
public class CommitterIdentity {
  private String name;
  private String email;
  private String date;
}

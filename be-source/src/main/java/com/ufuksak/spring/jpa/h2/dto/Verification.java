package com.ufuksak.spring.jpa.h2.dto;

import lombok.Data;

@Data
public class Verification {
  private boolean verified;
  private String reason;
  private String signature = null;
  private String payload = null;
}

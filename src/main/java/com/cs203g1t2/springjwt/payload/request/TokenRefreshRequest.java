package com.cs203g1t2.springjwt.payload.request;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
  @NotBlank
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}

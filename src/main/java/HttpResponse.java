package com.td.HttpServer;

public class HttpResponse {

  private String strResponse;

  public HttpResponse(String response) {
   strResponse = response;
  }

  public String toString() {
    return strResponse;
  }
}

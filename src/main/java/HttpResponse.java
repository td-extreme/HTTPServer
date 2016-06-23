package com.td.HttpServer;

import java.util.*;

public class HttpResponse {

  private String responseLine;
  private byte[] body;
  private HashMap<String, String> headers;

  public HttpResponse(String responseLine, byte[] body, HashMap<String, String> headers) {
    this.responseLine = responseLine;
    this.body = body;
    this.headers = headers;
  }

  public byte[] body() {
    return body;
  }

  public String responseLine() {
    return responseLine;
  }

  public String headers() {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value)-> builder.append(key + ": " + value));
    return builder.toString();
  }
}

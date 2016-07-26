package com.td.HttpServer;

import java.util.HashMap;
import java.io.*;

public class HttpResponse {

  private int responseCode;
  private HashMap<String, String> headers;
  private byte[] body;

  public HttpResponse(int responseCode, HashMap<String, String> headers, byte[] body) {
    this.responseCode = responseCode;
    this.headers = headers;
    this.body = body;
    addHeader("Content-Length", contentLength(this.body));
  }

  public void setResponseCode(int code) {
    this.responseCode = code;
  }

  public int responseCode() {
    return responseCode;
  }

  public byte[] body() {
    return body;
  }

  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public void addHeaders(HashMap<String, String> headers) {
    this.headers.putAll(headers);
  }

  public HashMap<String, String> headers() {
    return headers;
  }

  public String getValueForHeader(String key) {
    return headers.get(key);
  }

  private String contentLength(byte[] body) {
    return Integer.toString(body.length);
  }
}

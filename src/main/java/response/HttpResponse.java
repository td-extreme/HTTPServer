package com.td.HttpServer;

import java.util.*;
import java.io.*;

public class HttpResponse {

  private int responseCode;
  private byte[] body;
  private HashMap<String, String> headers;

  public HttpResponse() {
    this.responseCode = 200;
    this.body = "".getBytes();
    this.headers = new HashMap<String, String>();
  }

  public void setResponseCode(int code) {
    this.responseCode = code;
  }

  public int responseCode() {
    return responseCode;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public void setBody(String body) {
    this.body = body.getBytes();
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
}

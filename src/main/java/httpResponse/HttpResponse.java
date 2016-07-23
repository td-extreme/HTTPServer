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

  public void setBody(byte[] body, String contentType) {
    this.body = body;
    addHeader("Content-Length", contentLength(this.body));
    addHeader("Content-Type", contentType);
  }

  public void setBody(String body, String contentType) {
    setBody(body.getBytes(), contentType);
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

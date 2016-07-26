package com.td.HttpServer;

import java.util.*;
import java.io.*;

public class HttpResponse implements ICallable {

  private int responseCode;
  private byte[] body;
  private HashMap<String, String> headers;

  public Object[] call() {
    Object[] returnArray = new Object[3];
    returnArray[0] = responseCode();
    returnArray[1] = headers();
    returnArray[2] = body();
    return returnArray;
  }

  public HttpResponse() {
    this.responseCode = 200;
    this.body = "".getBytes();
    this.headers = new HashMap<String, String>();
  }

  public void setResponseCode(int code) {
    this.responseCode = code;
  }

  private int responseCode() {
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

  private byte[] body() {
    return body;
  }

  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public void addHeaders(HashMap<String, String> headers) {
    this.headers.putAll(headers);
  }

  private HashMap<String, String> headers() {
    return headers;
  }

  public String getValueForHeader(String key) {
    return headers.get(key);
  }

  private String contentLength(byte[] body) {
    return Integer.toString(body.length);
  }
}

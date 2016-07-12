package com.td.HttpServer;
import java.util.HashMap;

public class HttpRequest implements IHttpRequest {

  String requestLine;
  HashMap<String, String> headers;
  byte[] body;

  public HttpRequest(String requestLine) {
    this.requestLine = requestLine;
  }

  public HttpRequest(String requestLine, HashMap<String, String> headers) {
    this.requestLine = requestLine;
    this.headers = headers;
    this.body = new byte[0];
  }

  public String requestLine() {
    return requestLine;
  }

  public String method() {
    return requestLine().split(" ")[0];
  }

  public String path() {
    return requestLine().split(" ")[1];
  }

  public String version() {
    return requestLine().split(" ")[2];
  }

  public String getValueForHeader(String key) {
    return headers.get(key);
  }

  public int contentLength() {
    String length = getValueForHeader("Content-Length");
    int rtnLength = 0;
    if (length != null) {
      rtnLength = Integer.parseInt(length);
    }
    return rtnLength;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public byte[] body() {
   return body;
  }
}

package com.td.HttpServer;
import java.util.HashMap;

public class HttpRequest implements IHttpRequest {

  String requestLine;
  HashMap<String, String> headers;
  byte[] body;

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

  public HashMap<String, String> headers() {
    return headers;
  }

  public int contentLength() {
    String length = headers.getOrDefault("Content-Length", "0");
    return Integer.parseInt(length);
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public byte[] body() {
   return body;
  }
}

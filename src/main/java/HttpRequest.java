package com.td.HttpServer;

public class HttpRequest implements IHttpRequest {

  String requestLine;

  public HttpRequest(String requestLine) {
    this.requestLine = requestLine;
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
}

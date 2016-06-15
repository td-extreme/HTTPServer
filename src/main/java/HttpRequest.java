package com.td.HttpServer;

public class HttpRequest implements IHttpRequest {

  private String method;
  private String path;
  private String version;

  public HttpRequest(String[] request) {
    method = request[0];
    path = request[1];
    version = request[2];

    // TODO:: add logic to store Request Header Fields and optional message body
  }

  public String requestLine() {
    StringBuilder requestLine = new StringBuilder();
    requestLine.append(method());
    requestLine.append(" ");
    requestLine.append(path());
    requestLine.append(" ");
    requestLine.append(version());
    return requestLine.toString();
  }

  public String method() {
    return method;
  }

  public String path() {
    return path;
  }
  public String version() {
    return version;
  }
}

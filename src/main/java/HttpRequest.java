package com.td.HttpServer;

public class HttpRequest implements HttpProtocal {

  private String method;
  private String path;
  private String version;

  public HttpRequest(String request) {
    String request_array[] = request.split("\\s+");
    method = request_array[0];
    path = request_array[1];
    version = request_array[2];

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

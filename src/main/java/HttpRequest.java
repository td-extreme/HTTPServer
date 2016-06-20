package com.td.HttpServer;

import java.util.*;

public class HttpRequest implements IHttpRequest {

  String requestLine;
  HashMap<String, String> headers;

  public HttpRequest(String requestLine, HashMap<String, String> headers) {
    this.requestLine = requestLine;
    this.headers = headers;
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

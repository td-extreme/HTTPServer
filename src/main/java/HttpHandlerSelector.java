package com.td.HttpServer;

import java.util.*;

public class HttpHandlerSelector {

  HttpHandleRequest getRequest;

  public HttpHandlerSelector(HttpHandleRequest getRequest) {
    this.getRequest = getRequest;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    if (request.method().equals("GET")) {
      return getRequest.generateResponse(request);
    }
    byte[] body = new byte[1];
    HashMap<String, String> headers = new HashMap<String, String>();
    return new HttpResponse("HTTP/1.1 404 Not Found", body, headers);
  }
}

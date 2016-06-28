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

    // TODO :: Remove this after adding classes to handle other types of request methods
    String responseMessage = "This server currenlty doesn't support request type " + request.method() + "\r\n";
    byte[] body = responseMessage.getBytes();
    HashMap<String, String> headers = new HashMap<String, String>();
    return new HttpResponse("HTTP/1.1 200 OK", body, headers);
  }
}

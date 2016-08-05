package com.td.HttpServer;

public class HandlerMethodNotAllowed implements IHandler {

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(405);
    rtnResponse.setBody("Method Not Allowed", ContentType.text);
    return rtnResponse;
  }
}

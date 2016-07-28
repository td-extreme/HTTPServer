package com.td.HttpServer;

public class HandlerMethodNotAllowed implements IHandler {

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(405);
    rtnResponse.setBody("Method Not Allowed", ContentType.text);
    return rtnResponse;
  }
}

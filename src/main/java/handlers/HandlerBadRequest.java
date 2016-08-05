package com.td.HttpServer;

public class HandlerBadRequest implements IHandler {

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(400);
    rtnResponse.setBody("Bad Request", ContentType.text);
    return rtnResponse;
  }
}

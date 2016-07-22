package com.td.HttpServer;

public class HandlerUnprocessableEntity implements IHandler {

  private String message;

  public HandlerUnprocessableEntity(String message) {
    this.message = message;
  }
  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(422);
    rtnResponse.setBody(message, ContentType.text);
    return rtnResponse;
  }
}

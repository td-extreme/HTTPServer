package com.td.HttpServer;

public class HandlerUnprocessableEntity implements Ihandler {

  private String message;

  public HandlerUnprocessableEntity(String message) {
    this.message = message;
  }
  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(422);
    rtnResponse.addHeader("Content-Type", "text/plain");
    rtnResponse.setBody(message);
    return rtnResponse;
  }
}
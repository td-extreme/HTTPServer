package com.td.HttpServer;

public class HandlerBadRequest implements Ihandler {

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(400);
    rtnResponse.setBody("Bad Request");
    return rtnResponse;
  }
}

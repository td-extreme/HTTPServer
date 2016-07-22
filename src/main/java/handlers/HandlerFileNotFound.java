package com.td.HttpServer;

public class HandlerFileNotFound implements IHandler {

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(404);
    rtnResponse.setBody("File not Found", ContentType.text);
    return rtnResponse;
  }
}

package com.td.HttpServer;

public class HandlerFileNotFound implements IHandler {

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(404);
    rtnResponse.setBody("File not Found", ContentType.text);
    return rtnResponse;
  }
}

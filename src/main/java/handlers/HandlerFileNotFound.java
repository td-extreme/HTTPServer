package com.td.HttpServer;

public class HandlerFileNotFound implements Ihandler {

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setCode(404);
    rtnResponse.setBody("File not Found");
    return rtnResponse;
  }
}

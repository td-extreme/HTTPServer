package com.td.HttpServer;

public class HandlerFileNotFound implements Ihandler {

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    rtnResponse.setResponseCode(404);
    rtnResponse.setBody("File not Found", "plain/text");
    return rtnResponse;
  }
}

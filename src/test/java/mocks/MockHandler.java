package com.td.Mocks;
import com.td.HttpServer.IHandler;
import com.td.HttpServer.HttpResponse;

public class MockHandler implements IHandler {

  public HttpResponse generateResponse() {
    return new HttpResponse();
  }
}

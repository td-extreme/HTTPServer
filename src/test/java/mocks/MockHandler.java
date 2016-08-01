package com.td.Mocks;
import com.td.HttpServer.IHandler;
import com.td.HttpServer.HttpResponse;
import com.td.HttpServer.HttpRequest;

public class MockHandler implements IHandler {

  public HttpResponse generateResponse(HttpRequest request) {
    return new HttpResponse();
  }
}

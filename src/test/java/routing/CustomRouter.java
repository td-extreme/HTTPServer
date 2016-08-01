package com.td.test;

import com.td.HttpServer.IHandler;
import com.td.HttpServer.IHandlerRouter;
import com.td.HttpServer.HttpRequest;
import com.td.Mocks.MockHandler;

public class CustomRouter implements IHandlerRouter {

  public IHandler selectHandler(HttpRequest request) {
    if (request.method().equals("GET") && request.path().equals("/")) {
      return new MockHandler();
    } else {
      return null;
    }
  }
}

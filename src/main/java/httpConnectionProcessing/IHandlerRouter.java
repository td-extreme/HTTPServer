package com.td.HttpServer;

public interface IHandlerRouter {
  public IHandler selectHandler(HttpRequest request);
}

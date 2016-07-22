package com.td.HttpServer;

public interface IHandlerRouter {
  public IHandler selectHandlerBadRequest();
  public IHandler selectHandler(HttpRequest request);
}

package com.td.HttpServer;

public interface IHandlerRouter {
  public Ihandler selectHandlerBadRequest();
  public Ihandler selectHandler(HttpRequest request);
}

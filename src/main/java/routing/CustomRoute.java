package com.td.HttpServer;

public class CustomRoute {
  private String method;
  private String path;
  private IHandler handler;

  public CustomRoute(String method, String path, IHandler handler) {
    this.method = method;
    this.path = path;
    this.handler = handler;
  }

  public boolean match(String method, String path) {
    return (this.method.equals(method) && this.path.equals(path));
  }

  public IHandler getHandler() {
    return handler;
  }
}

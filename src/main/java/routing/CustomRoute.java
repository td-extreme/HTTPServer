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
    return (methodMatches(method) && pathMatches(path));
  }

  private boolean methodMatches(String method) {
    return this.method.equals(method);
  }

  private boolean pathMatches(String path) {
    if (this.path.equals("*")) {
      return true;
    } else {
      return this.path.equals(path);
    }
  }

  public IHandler getHandler() {
    return handler;
  }
}

package com.td.HttpServer;

public interface IHttpRequest {
  public String requestLine();
  public String method();
  public String path();
  public String version();
}

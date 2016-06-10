package com.td.HttpServer;

public interface HttpProtocal {
  public String requestLine();
  public String method();
  public String path();
  public String version();
}

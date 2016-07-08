package com.td.HttpServer;

public interface IHttpRequestHandler {
  public HttpResponse generateResposne(HttpRequest request);
}

package com.td.HttpServer;

public interface IResponseBuilder {
  public HttpResponse generateResponse(IHandler handler);
}

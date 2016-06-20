package com.td.HttpServer;

public interface IRequestBuilder {
  public HttpRequest createRequest(String rawRequest);
}

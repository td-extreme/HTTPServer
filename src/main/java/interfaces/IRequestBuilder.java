package com.td.HttpServer;
import java.io.IOException;

public interface IRequestBuilder {
  public HttpRequest createRequest(String rawRequest) throws InvalidHttpRequestException;
}

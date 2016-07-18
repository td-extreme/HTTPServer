package com.td.HttpServer;
import java.io.IOException;

public interface IRequestBuilder {
  public HttpRequest createRequest(IMessageIO client) throws InvalidHttpRequestException, IOException;
}

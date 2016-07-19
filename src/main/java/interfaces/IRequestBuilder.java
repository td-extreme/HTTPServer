package com.td.HttpServer;
import java.io.IOException;

public interface IRequestBuilder {
  public HttpRequest getNextRequest(IClientSocketIO client) throws InvalidHttpRequestException, BadConnectionException;
}

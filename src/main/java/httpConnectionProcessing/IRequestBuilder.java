package com.td.HttpServer;
import java.io.IOException;

public interface IRequestBuilder {
  public HttpRequest getNextRequest(IClientSocketInput client) throws InvalidHttpRequestException, BadConnectionException;
}

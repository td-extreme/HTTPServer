package com.td.HttpServer;

import java.util.HashMap;
import java.io.IOException;

public class HttpRequestBuilder implements IRequestBuilder {
  private IRequestParser parser;

  public HttpRequestBuilder(IRequestParser parser ) {
    this.parser = parser;
  }

  public HttpRequest getNextRequest(IClientSocketInput client) throws InvalidHttpRequestException, BadConnectionException {
    String rawRequest;
    try {
      rawRequest = client.getRawRequestString();
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new BadConnectionException(e);
    }
    HttpRequest request = parser.parseRequest(rawRequest);
    addBody(client, request);
    return request;
  }

  private void addBody(IClientSocketInput client, HttpRequest request) throws BadConnectionException {
    byte[] body;
    if (request.contentLength() > 0) {
      try {
        body = client.getBytes(request.contentLength());
      }
      catch (IOException e) {
        e.printStackTrace();
        throw new BadConnectionException(e);
      }
      request.setBody(body);
    }
  }
}

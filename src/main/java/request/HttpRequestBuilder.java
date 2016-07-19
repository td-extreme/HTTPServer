package com.td.HttpServer;

import java.util.HashMap;
import java.io.IOException;

public class HttpRequestBuilder implements IRequestBuilder {
  IRequestParser parser;

  public HttpRequestBuilder(IRequestParser parser ) {
    this.parser = parser;
  }

  public HttpRequest getNextRequest(IClientSocketIO client) throws InvalidHttpRequestException, BadConnectionException {
    try {
      String rawRequest = client.getRawRequestString();
      HttpRequest request = parser.parseRequest(rawRequest);
      if (request.contentLength() > 0) {
        byte[] body = client.getBytes(request.contentLength());
        request.setBody(body);
      }
      return request;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new BadConnectionException();
    }
  }
}

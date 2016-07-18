package com.td.HttpServer;

import java.util.HashMap;
import java.io.IOException;

public class HttpRequestBuilder implements IRequestBuilder {
  IRequestReader reader;
  IRequestParser parser;

  public HttpRequestBuilder(IRequestReader reader, IRequestParser parser ) {
    this.reader = reader;
    this.parser = parser;
  }

  public HttpRequest createRequest(IMessageIO client) throws InvalidHttpRequestException, IOException {
    HttpRequest request;
    String rawRequest = reader.getMessage(client);
    String requestLine = parser.parseRequestLine(rawRequest);
    HashMap<String, String> headers = parser.parseHeaders(rawRequest);
    request = new HttpRequest(requestLine, headers);
    if (request.contentLength() > 0) {
      byte[] body = reader.getBytes(client, request.contentLength());
      request.setBody(body);
    }
    return request;
  }
 }

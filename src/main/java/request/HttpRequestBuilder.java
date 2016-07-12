package com.td.HttpServer;

import java.util.*;
import java.io.IOException;

public class HttpRequestBuilder implements IRequestBuilder {

  private IValidator requestVerifier;
  public HttpRequestBuilder(IValidator requestVerifier) {
    this.requestVerifier = requestVerifier;
  }

  public HttpRequest createRequest(String rawRequest) throws InvalidHttpRequestException {
    HttpRequest request;
    String requestLine = parseRequestLine(rawRequest);
    HashMap<String, String> headers = parseHeaders(rawRequest);
    request = new HttpRequest(requestLine, headers);
    return request;
  }

  private String parseRequestLine(String rawRequest) throws InvalidHttpRequestException {
    String requestLine = rawRequest.split("\r?\n")[0];
    if (!requestVerifier.isValid(requestLine)) {
      throw new InvalidHttpRequestException();
    }
    return requestLine;
  }

  private HashMap<String, String> parseHeaders(String rawRequest) throws InvalidHttpRequestException  {
    String rawHeaders = rawRequest.split("\n", 2)[1];
    HashMap<String, String> headers = new HashMap<String, String>();
    String headerLines[] = rawHeaders.split("\n");
    for (String line : headerLines ) {
      if (line.contains(":") && line.contains(" ")) {
        String key = line.split(":", 2)[0];
        String value = line.split("\\s+", 2)[1];
        headers.put(key, value);
      }
    }
    return headers;
  }
}

package com.td.HttpServer;

import java.util.*;

public class HttpRequestBuilder implements IRequestBuilder {

  private IValidator requestVerifier;
  public HttpRequestBuilder(IValidator requestVerifier) {
    this.requestVerifier = requestVerifier;
  }

  public HttpRequest createRequest(String rawRequest) throws InvalidHttpRequestException {
    String rawRequestArray[] = rawRequest.split("\n", 2);
    String requestLine = rawRequest.split("\r?\n")[0];
    if (!requestVerifier.isValid(requestLine)) {
      throw new InvalidHttpRequestException();
    }
    return new HttpRequest(requestLine);
  }
}

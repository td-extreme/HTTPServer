package com.td.HttpServer;

import java.util.*;
import java.io.IOException;

public class HttpRequestBuilder implements IRequestBuilder {

  private IValidator requestVerifier;
  public HttpRequestBuilder(IValidator requestVerifier) {
    this.requestVerifier = requestVerifier;
  }

  public HttpRequest createRequest(IMessageIO messageIO) throws IOException, InvalidHttpRequestException {
    String rawRequest = messageIO.getMessage();
    HttpRequest rtnRequest;
    String requestLine = parseRequestLine(rawRequest);
    HashMap<String, String> headers = parseHeaders(rawRequest);
    rtnRequest = new HttpRequest(requestLine, headers);
    if (rtnRequest.contentLength() > 0) {
      byte[] body = messageIO.getBytes(rtnRequest.contentLength());
      rtnRequest.setBody(body);
    }
    return rtnRequest;
  }

  public String parseRequestLine(String rawRequest) throws InvalidHttpRequestException {
    String requestLine = rawRequest.split("\r?\n")[0];
    if (!requestVerifier.isValid(requestLine)) {
      throw new InvalidHttpRequestException();
    }
    return requestLine;
  }

  public HashMap<String, String> parseHeaders(String rawRequest) throws InvalidHttpRequestException  {
    String rawHeaders = rawRequest.split("\n", 2)[1];
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    String headerLines[] = rawHeaders.split("\n");
    for (String line : headerLines ) {
      if (line.contains(":") && line.contains(" ")) {
        String key = line.split(":", 2)[0];
        String value = line.split("\\s+", 2)[1];
        rtnMap.put(key, value);
      }
    }
    return rtnMap;
  }
}

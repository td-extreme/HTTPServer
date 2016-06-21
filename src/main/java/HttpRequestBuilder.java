package com.td.HttpServer;

import java.util.*;

public class HttpRequestBuilder implements IRequestBuilder {

  private IValidator requestVerifier;
  public HttpRequestBuilder(IValidator requestVerifier) {
    this.requestVerifier = requestVerifier;
  }

  public HttpRequest createRequest(String rawRequest) throws InvalidHttpRequestException {
    String rawRequestArray[] = rawRequest.split("\n", 2);
    String requestLine = rawRequest.split("\n")[0];
    if (!requestVerifier.isValid(requestLine)) {
      throw new InvalidHttpRequestException();
    }
    HashMap<String, String> headers = buildHeaderMap(rawRequest);
    return new HttpRequest(requestLine, headers);
  }

  private HashMap<String, String> buildHeaderMap(String rawRequest) throws InvalidHttpRequestException {
    String rawHeaderLines[] = parseHeader(rawRequest);
    return createMap(rawHeaderLines);
  }

  private String[] parseHeader(String rawRequest) {
    String[] rawRequestArray = rawRequest.split("\n", 2);
    if (rawRequestArray.length < 2) {
      return new String[] {};
    }
    String rawHeader = rawRequestArray[1];
    return rawHeader.split("\n");
  }

  private HashMap<String, String> createMap(String rawHeaderLines[]) throws InvalidHttpRequestException {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    for (int i = 0; i < rawHeaderLines.length; i++) {
      String headerKeyValue[] = rawHeaderLines[i].split(":", 2);
      if (headerKeyValue.length < 2) {
        throw new InvalidHttpRequestException();
      }
      rtnMap.put(headerKeyValue[0], headerKeyValue[1]);
    }
    return rtnMap;
  }
}

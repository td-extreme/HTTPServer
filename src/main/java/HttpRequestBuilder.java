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

  private HashMap<String, String> buildHeaderMap(String rawRequest) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    try {
      String rawHeader = rawRequest.split("\n", 2)[1];
      String rawHeaderLines[] = rawHeader.split("\n");
      for (int i = 0; i < rawHeaderLines.length; i++) {
        String headerKeyValue[] = rawHeaderLines[i].split(":", 2);
        rtnMap.put(headerKeyValue[0], headerKeyValue[1]);
      }
    }
    catch (ArrayIndexOutOfBoundsException e) { }
    return rtnMap;
  }
}

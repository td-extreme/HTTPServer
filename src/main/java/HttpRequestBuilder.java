package com.td.HttpServer;

import java.util.*;

public class HttpRequestBuilder implements IRequestBuilder {

  public HttpRequest createRequest(String rawRequest) {
    String rawRequestArray[] = rawRequest.split("\n", 2);
    String rawHeader;
    String requestLine = rawRequest.split("\n")[0];
    HashMap<String, String> headers;
    if (rawRequestArray.length < 2) {
      headers = new HashMap<String, String>();
    } else {
      rawHeader = rawRequest.split("\n", 2)[1];
      headers = buildHeaderMap(rawHeader);
    }
    return new HttpRequest(requestLine, headers);
  }

  private HashMap<String, String> buildHeaderMap(String rawHeader) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    try {
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

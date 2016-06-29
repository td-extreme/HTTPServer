package com.td.HttpServer;

import java.util.*;

public class HttpHandlerSelector {

  HashMap<String, HttpHandleRequest> requestTypeMap;

  public HttpHandlerSelector(HandleGetRequest handleGetRequest) {
    requestTypeMap = new HashMap<String, HttpHandleRequest>();
    requestTypeMap.put("GET", handleGetRequest);
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpHandleRequest httpHandleRequest = requestTypeMap.get(request.method());
    return httpHandleRequest.generateResponse(request);
  }
}

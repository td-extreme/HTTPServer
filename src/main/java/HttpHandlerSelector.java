package com.td.HttpServer;

import java.util.*;

public class HttpHandlerSelector {

  HashMap<String, HttpHandleRequest> requestTypeMap;

  // Right now there is only one type of request the Server handles
  // After the handle a POST request story the constuctor will take another
  // HttpHandleRequest postRequest and will add that to the requestTypeMap
  // TODO :: Delete this comment after completing the POST request story.

  public HttpHandlerSelector(HandleGetRequest handleGetRequest) {
    requestTypeMap = new HashMap<String, HttpHandleRequest>();
    requestTypeMap.put("GET", handleGetRequest);
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpHandleRequest httpHandleRequest = requestTypeMap.get(request.method());
    return httpHandleRequest.generateResponse(request);
  }
}

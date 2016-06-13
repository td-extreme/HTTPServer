package com.td.HttpServer;

public class HttpVerifier implements IValidator {

  public boolean isValid(String[] request) {
    try {
      return isMethodValid(request[0]) &&
        isPathValid(request[1]) &&
        isVersionValid(request[2]);
    }
    catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  private boolean isMethodValid(String method) {
    return isRequestGet(method) ||
           isRequestHead(method) ||
           isRequestPost(method) ||
           isRequestPut(method) ||
           isRequestDelete(method) ||
           isRequestTrace(method) ||
           isRequestOptions(method) ||
           isRequestConnect(method) ||
           isRequestPatch(method);
  }

  private boolean isPathValid(String path) {
    return path.substring(0, 1).equals("/");
  }

  private boolean isVersionValid(String version) {
    return version.equals("HTTP/1.1");
  }

  private boolean isRequestGet(String method) {
    return method.equals("GET");
  }

  private boolean isRequestHead(String method) {
    return method.equals("HEAD");
  }

  private boolean isRequestPost(String method) {
    return method.equals("POST");
  }

  private boolean isRequestPut(String method) {
    return method.equals("PUT");
  }

  private boolean isRequestDelete(String method) {
    return method.equals("DELETE");
  }

  private boolean isRequestTrace(String method) {
    return method.equals("TRACE");
  }

  private boolean isRequestOptions(String method) {
    return method.equals("OPTIONS");
  }

  private boolean isRequestConnect(String method) {
    return method.equals("CONNECT");
  }

  private boolean isRequestPatch(String method) {
    return method.equals("PATCH");
  }
}

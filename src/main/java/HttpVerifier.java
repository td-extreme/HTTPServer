package com.td.HttpServer;
import java.util.*;

public class HttpVerifier implements IValidator {

  List<String> validMethods;

  public HttpVerifier() {
    validMethods = new ArrayList<String>();
    validMethods.add("GET");
    validMethods.add("HEAD");
    validMethods.add("POST");
    validMethods.add("PUT");
    validMethods.add("DELETE");
    validMethods.add("TRACE");
    validMethods.add("OPTIONS");
    validMethods.add("CONNECT");
    validMethods.add("PATCH");
  }

  public boolean isValid(String[] request) {
    if ( request.length < 3 ) return false;

    return isMethodValid(request[0]) &&
      isPathValid(request[1]) &&
      isVersionValid(request[2]);
  }

  private boolean isMethodValid(String method) {
    return validMethods.contains(method);
  }

  private boolean isPathValid(String path) {
    return path.substring(0, 1).equals("/");
  }

  private boolean isVersionValid(String version) {
    return version.equals("HTTP/1.1");
  }
}

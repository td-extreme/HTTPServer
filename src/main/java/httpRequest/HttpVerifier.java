package com.td.HttpServer;

public class HttpVerifier implements IValidator {

  public boolean isValid(String request) {
    String[] requestArray = request.split("\\s+");
    if ( requestArray.length < 3 ) return false;

    return isPathValid(requestArray[1]) &&
      isVersionValid(requestArray[2]);
 }

  private boolean isPathValid(String path) {
    return path.substring(0, 1).equals("/");
  }

  private boolean isVersionValid(String version) {
    return version.equals("HTTP/1.1");
  }
}

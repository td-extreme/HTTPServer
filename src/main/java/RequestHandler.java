package com.td.HttpServer;

public class RequestHandler {

  IValidator httpVerifier;

  public RequestHandler(IValidator verifier) {
    httpVerifier = verifier;
  }

  public String processRequest(String[] request) {
    if (httpVerifier.isValid(request) == true ) {
      return "200";
    } else {
      return "400";
    }
  }
}

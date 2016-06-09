package com.td.HttpServer;

public class RequestHandler {

  Validator httpVerifier;

  public RequestHandler(Validator verifier) {
    httpVerifier = verifier;
  }

  public String processRequest(String request) {
    if (httpVerifier.isValid(request) == true ) {
      return "200";
    } else {
      return "400";
    }
  }
}

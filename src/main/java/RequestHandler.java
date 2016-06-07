package com.td.HTTPServer;

public class RequestHandler {

  HTTPVerifierInterface httpVerifier;

  public RequestHandler(HTTPVerifierInterface verifier) {
    httpVerifier = verifier;
  }

  public String processRequest(String request) {
    if (httpVerifier.isValidHTTP(request) == true ) {
      return "200";
    } else {
      return "400";
    }
  }
}

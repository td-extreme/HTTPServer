package com.td.HttpServer;

public class RequestHandler {

  IValidator httpVerifier;

  public RequestHandler(IValidator verifier) {
    httpVerifier = verifier;
  }

  public String processRequest(String[] request) {
    if (httpVerifier.isValid(request) == true ) {
      return "HTTP/1.1 200 OK\r\n\r\nValid Http Request";
    } else {
      return "HTTP/1.1 400 Bad Request";
    }
  }
}

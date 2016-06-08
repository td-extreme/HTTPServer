package com.td.HTTPServer;
import java.io.IOException;

public class HTTPServerRunner {

  RequestVerifier verifier;
  RequestHandler handler;
  ServerPort server;

  public HTTPServerRunner(ServerPort serverPort, RequestHandler requestHandler) {
    server = serverPort;
    handler = requestHandler;
  }

  public void runServer() throws IOException {
    String request;
    String response;
    while(true) {
      request = server.getRequest();
      System.out.print(request);
      response = handler.processRequest(request);
      server.sendResponse(response);
    }
  }
}

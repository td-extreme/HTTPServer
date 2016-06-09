package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  RequestHandler handler;
  ClientConnection server;

  public HttpServerRunner(ClientConnection serverPort, RequestHandler requestHandler) {
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

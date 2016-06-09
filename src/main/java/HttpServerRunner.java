package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  RequestHandler handler;
  ClientConnection clientConnection;

  public HttpServerRunner(ClientConnection connection, RequestHandler requestHandler) {
    clientConnection = connection;
    handler = requestHandler;
  }

  public void runServer() throws IOException {
    String request;
    String response;
    HttpRequest httpRequest;
    while(true) {
      request = clientConnection.receiveRequest();
      httpRequest = new HttpRequest(request);
      System.out.print(request);
      response = handler.processRequest(httpRequest);
      clientConnection.sendResponse(response);
    }
  }
}

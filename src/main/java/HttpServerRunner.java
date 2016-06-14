package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  RequestHandler handler;
  ClientConnection clientConnection;
  IParser parser;

  public HttpServerRunner(ClientConnection connection, RequestHandler requestHandler, IParser parseString) {
    clientConnection = connection;
    handler = requestHandler;
    parser = parseString;
  }

  public void runServer() throws IOException {
    String request;
    String request_array[];
    String response;
    HttpRequest httpRequest;
    while(true) {
      request = clientConnection.receiveRequest();
      request_array = parser.parse(request);
      response = handler.processRequest(request_array);
      clientConnection.sendResponse(response);
    }
  }
}

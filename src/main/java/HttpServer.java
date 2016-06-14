package com.td.HttpServer;
import java.io.IOException;

public class HttpServer{

  public static void main(String[] args) throws IOException {
    System.out.println("HTTP Server running on localhost port 8080!!!");
    HttpVerifier verifier = new HttpVerifier();
    RequestHandler handler = new RequestHandler(verifier);
    ClientConnection server = new ClientConnection(8080);
    ParseString parser = new ParseString();
    HttpServerRunner runner = new HttpServerRunner(server, handler, parser);
    runner.runServer();
  }
}

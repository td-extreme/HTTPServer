package com.td.HTTPServer;
import java.io.IOException;

public class HTTPServer{

  public static void main(String[] args) throws IOException {
    System.out.println("HTTP Server!!!");
    RequestVerifier verifier = new RequestVerifier();
    RequestHandler handler = new RequestHandler(verifier);
    ServerPort server = new ServerPort(8080);
    HTTPServerRunner runner = new HTTPServerRunner(server, handler);
    runner.runServer();
  }
}

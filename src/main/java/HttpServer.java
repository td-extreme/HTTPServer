package com.td.HttpServer;
import java.io.IOException;

public class HttpServer{

  public static void main(String[] args) throws IOException {
    System.out.println("HTTP Server running on localhost port 8080!!!");
    HttpReaderWriter httpReaderWriter = new HttpReaderWriter(new SocketIO(8080), new HttpVerifier());
    HttpServerRunner httpServerRunner = new HttpServerRunner(httpReaderWriter, new HttpHandlerSelector());
    httpServerRunner.runServer();
  }
}

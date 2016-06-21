package com.td.HttpServer;
import java.io.IOException;

public class HttpServer{

  private static ArgumentParser arguments;

  public static void main(String[] args) throws IOException {
    arguments = new ArgumentParser(args);
    System.out.println("HTTP Server running on localhost port " + arguments.getPortNumber() +"!");
    HttpReaderWriter httpReaderWriter = new HttpReaderWriter(new SocketIO(arguments.getPortNumber()), new HttpRequestBuilder(new HttpVerifier()));
    HttpServerRunner httpServerRunner = new HttpServerRunner(httpReaderWriter, new HttpHandlerSelector());
    httpServerRunner.runServer();
  }
}

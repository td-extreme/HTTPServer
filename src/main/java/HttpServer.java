package com.td.HttpServer;
import java.io.IOException;

public class HttpServer{

  public static void main(String[] args) throws IOException {
    int portNumber = 8080;
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
      if (args[i].equals("-pn")) {
        portNumber = Integer.parseInt(args[++i]);
      }
    }

    System.out.println("HTTP Server running on localhost port " + portNumber +"!");
    HttpReaderWriter httpReaderWriter = new HttpReaderWriter(new SocketIO(portNumber), new HttpVerifier());
    HttpServerRunner httpServerRunner = new HttpServerRunner(httpReaderWriter, new HttpHandlerSelector());
    httpServerRunner.runServer();
  }
}

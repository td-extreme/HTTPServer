package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;
public class HttpServer{

  private static ArgumentParser arguments;

  public static void main(String[] args) throws IOException {
    arguments = new ArgumentParser(args);
    ServerSocket serverSocket = new ServerSocket(arguments.getPortNumber());
    HttpRequestParser httpRequestParser = new HttpRequestParser();
    FileIO fileIO = new FileIO(arguments.getDirectory());
    HandlerRouter handlerRouter = new HandlerRouter(fileIO);
    HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    HttpResponseWriter httpResponseWriter = new HttpResponseWriter();
    HttpServerRunner httpServerRunner = new HttpServerRunner(handlerRouter, httpRequestBuilder, httpResponseWriter);
    System.out.println("HTTP Server running on localhost port " + serverSocket.getLocalPort() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());

    while (true) {
      ClientSocketIO client = new ClientSocketIO(serverSocket);
      client.openClientConnection();
      httpServerRunner.run(client);
      client.closeClientConnection();
    }
  }
}

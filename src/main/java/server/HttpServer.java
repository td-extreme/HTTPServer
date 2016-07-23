package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;
public class HttpServer{

  private static ArgumentParser arguments;

  public static void main(String[] args) {
    arguments = new ArgumentParser(args);
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(arguments.getPortNumber());
    }
    catch (IOException e) {
      System.out.println("\nPort number " + arguments.getPortNumber() + " can not be opened. It may be in use by another application.\n");
      return;
    }
    HttpRequestParser httpRequestParser = new HttpRequestParser();
    FileIO fileIO = new FileIO(arguments.getDirectory());
    HandlerRouter handlerRouter = new HandlerRouter(fileIO);
    HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    HttpResponseWriter httpResponseWriter = new HttpResponseWriter();
    ConnectionProcessRunnerMultiThread connectionProcessRunner = new ConnectionProcessRunnerMultiThread();
    HttpServerRunner httpServerRunner = new HttpServerRunner(serverSocket, connectionProcessRunner, handlerRouter, httpRequestBuilder, httpResponseWriter);
    System.out.println("HTTP Server running on localhost port " + serverSocket.getLocalPort() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());
    httpServerRunner.run();
 }
}

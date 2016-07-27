package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer{

  private ServerSocket serverSocket;
  private HttpServerRunner httpServerRunner;
  private CustomHandlerRouter customHandlerRouter;
  private FileIO fileIO;

  public HttpServer(int portNumber, String directory) {
    try {
      serverSocket = new ServerSocket(portNumber);
    }
    catch (IOException e) {
      System.out.println("\nPort number " + portNumber + " can not be opened. It may be in use by another application.\n");
      return;
    }
    HttpRequestParser httpRequestParser = new HttpRequestParser();
    fileIO = new FileIO(directory);
    DefaultHandlerRouter defaultHandlerRouter = new DefaultHandlerRouter(fileIO);
    customHandlerRouter = new CustomHandlerRouter(defaultHandlerRouter);
    HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    HttpResponseWriter httpResponseWriter = new HttpResponseWriter();
    ConnectionProcessRunnerMultiThread connectionProcessRunner = new ConnectionProcessRunnerMultiThread();
    httpServerRunner = new HttpServerRunner(serverSocket, connectionProcessRunner, customHandlerRouter, httpRequestBuilder, httpResponseWriter);
  }

  public void run() {
    System.out.println("HTTP Server running on localhost port " + serverSocket.getLocalPort() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());
    httpServerRunner.run();
  }

  public void addRoute(String method, String path, IHandler handler) {
    customHandlerRouter.addRoute(method, path, handler);
  }
}

package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer{

  private ServerSocket serverSocket;
  private HttpServerRunner httpServerRunner;
  private CustomHandlerRouter customHandlerRouter;
  private FileIO fileIO;
  private HttpRequestBuilder httpRequestBuilder;
  private HttpResponseWriter httpResponseWriter;
  private ConnectionProcessRunnerMultiThread connectionProcessRunner;
  private int portNumber;
  private String directory;

  public HttpServer(int portNumber, String directory) {
    this.portNumber = portNumber;
    this.directory = directory;
    HttpRequestParser httpRequestParser = new HttpRequestParser();
    fileIO = new FileIO(directory);
    DefaultHandlerRouter defaultHandlerRouter = new DefaultHandlerRouter(fileIO);
    customHandlerRouter = new CustomHandlerRouter(defaultHandlerRouter);
    httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    httpResponseWriter = new HttpResponseWriter();
    connectionProcessRunner = new ConnectionProcessRunnerMultiThread();
  }

  public void run() throws UnableToOpenPortException {
    try {
      serverSocket = new ServerSocket(portNumber);
    }
    catch (IOException e) {
      throw new UnableToOpenPortException(e);
    }
    httpServerRunner = new HttpServerRunner(serverSocket, connectionProcessRunner, customHandlerRouter, httpRequestBuilder, httpResponseWriter);
    System.out.println("HTTP Server running on localhost port " + serverSocket.getLocalPort() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());
    httpServerRunner.run();
  }

  public void addRoute(String method, String path, IHandler handler) {
    customHandlerRouter.addRoute(method, path, handler);
  }
}

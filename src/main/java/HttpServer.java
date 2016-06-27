package com.td.HttpServer;
import java.io.IOException;

public class HttpServer{

  private static ArgumentParser arguments;

  public static void main(String[] args) throws IOException {
    arguments = new ArgumentParser(args);
    HttpRequestBuilder builder = new HttpRequestBuilder(new HttpVerifier());
    SocketIO socket = new SocketIO(arguments.getPortNumber());
    HttpReaderWriter httpReaderWriter = new HttpReaderWriter(socket, builder);
    FileIO fileIO = new FileIO(arguments.getDirectory());
    HttpHandleRequest getRequest = new HandleGetRequest(fileIO);
    HttpHandlerSelector handler = new HttpHandlerSelector(getRequest);
    HttpServerRunner httpServerRunner = new HttpServerRunner(httpReaderWriter, handler);
    System.out.println("HTTP Server running on localhost port " + arguments.getPortNumber() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());
    httpServerRunner.runServer();
  }
}

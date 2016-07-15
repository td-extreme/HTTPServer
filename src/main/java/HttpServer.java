package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;
public class HttpServer{

  private static ArgumentParser arguments;

  public static void main(String[] args) throws IOException {
    arguments = new ArgumentParser(args);
    HttpRequestBuilder builder = new HttpRequestBuilder(new HttpVerifier());
    ServerSocket serverSocket = new ServerSocket(arguments.getPortNumber());
    SocketIO socket = new SocketIO(serverSocket);
    HttpResponseFormatter httpResponseFormatter = new HttpResponseFormatter();
    HttpReaderWriter httpReaderWriter = new HttpReaderWriter(socket, builder, httpResponseFormatter);
    FileIO fileIO = new FileIO(arguments.getDirectory());
    HandlerRouter handlerRouter = new HandlerRouter(fileIO);
    HttpServerRunner httpServerRunner = new HttpServerRunner(httpReaderWriter, handlerRouter);
    System.out.println("HTTP Server running on localhost port " + arguments.getPortNumber() +"!");
    System.out.println("Using directory : " + fileIO.workingDirectory());
    httpServerRunner.runServer();
  }
}

package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServerRunner {
  private ServerSocket serverSocket;
  private IConnectionProcessRunner httpConnectionProcessRunner;
  private IHandlerRouter handlerRouter;
  private IRequestBuilder httpRequestBuilder;
  private IResponseWriter httpResponseWriter;

  public HttpServerRunner(ServerSocket serverSocket, IConnectionProcessRunner httpConnectionProcessRunner, IHandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, IResponseWriter httpResponseWriter) {
    this.serverSocket = serverSocket;
    this.httpConnectionProcessRunner = httpConnectionProcessRunner;
    this.handlerRouter = handlerRouter; 
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void run() {
    while (true) {
      ClientSocketIO client = new ClientSocketIO(serverSocket);
      try {
        client.openClientConnection();
      }
      catch (BadConnectionException e) {
        e.printStackTrace();
        continue;
      }
      HttpConnectionToProcess httpConnectionToProcess = new HttpConnectionToProcess(client, handlerRouter, httpRequestBuilder, httpResponseWriter);
      httpConnectionProcessRunner.execute(httpConnectionToProcess);
    }
  }
}
